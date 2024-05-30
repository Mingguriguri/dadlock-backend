package swe.backend.dadlock.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swe.backend.dadlock.dto.oauth2.CustomOAuth2User;
import swe.backend.dadlock.dto.webapp.WebAppRequestDTO;
import swe.backend.dadlock.dto.webapp.WebAppResponseDTO;
import swe.backend.dadlock.entity.User;
import swe.backend.dadlock.entity.WebApp;
import swe.backend.dadlock.exception.NotOwnerException;
import swe.backend.dadlock.repository.UserRepository;
import swe.backend.dadlock.repository.WebAppRepository;

import java.util.DuplicateFormatFlagsException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = false) // true 로 되어 있으면 POST로 입력 시 에러가 나서 false로 수정하였습니다. (Connection is read-only. Queries leading to data modification are not allowed)
@RequiredArgsConstructor
public class WebAppService {

    private final UserRepository userRepository;
    private final WebAppRepository webAppRepository;

    @Transactional(readOnly = true)
    public List<WebAppResponseDTO.CommonDTO> getUrlList(String userGoogleId) {
        List<WebApp> webAppListByGoogleId = webAppRepository.findWebAppListByGoogleId(userGoogleId);
        List<WebAppResponseDTO.CommonDTO> responseDTOList = webAppListByGoogleId.stream()
                .map(webApp -> new WebAppResponseDTO.CommonDTO(webApp))
                .collect(Collectors.toList());
        return responseDTOList;
    }

    public WebAppResponseDTO.CommonDTO createWebApp(String userGoogleId, WebAppRequestDTO.CreateDTO requestDTO) {
        User findUser = findUserByGoogleId(userGoogleId);

        // 이미 존재하는 URL이라면 저장하지 않음
        webAppRepository.findWebAppByUserGoogleIdAndAppUrl(userGoogleId, requestDTO.getAppUrl())
                .ifPresent(webApp -> {
                    throw new DuplicateFormatFlagsException("이미 존재하는 URL입니다: " + requestDTO.getAppUrl());
                });

        WebApp savedWebApp = webAppRepository.save(requestDTO.toEntity(findUser));
        return new WebAppResponseDTO.CommonDTO(savedWebApp);
    }

    public WebAppResponseDTO.CommonDTO updateWebAppInfo(Long webAppId, String userGoogleId, WebAppRequestDTO.UpdateDTO requestDTO) {
        WebApp findWebApp = findWebAppById(webAppId);
        validWebAppUrlOwner(userGoogleId, findWebApp);

        findWebApp.updateInfo(requestDTO);
        WebApp updatedWebApp = findWebAppById(webAppId);
        return new WebAppResponseDTO.CommonDTO(updatedWebApp);
    }

    public void deleteWebApp(String userGoogleId, Long webAppId) {
        WebApp findWebApp = findWebAppById(webAppId);
        validWebAppUrlOwner(userGoogleId, findWebApp);
        webAppRepository.delete(findWebApp);
    }

    private User findUserByGoogleId(String userGoogleId) {
        User findUser = userRepository.findById(userGoogleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다. userGoogleId = " + userGoogleId));
        return findUser;
    }

    private WebApp findWebAppById(Long webAppId) {
        WebApp findWebApp = webAppRepository.findById(webAppId)
                .orElseThrow(() -> new IllegalArgumentException("해당 WebApp 엔티티가 존재하지 않습니다. webAppId = " + webAppId));
        return findWebApp;
    }

    /**
     * URL의 변경(수정,삭제) 요청을 보낸 유저가 해당 URL의 주인이 맞는지 검증하는 메서드
     * @param requestUserId : 요청을 보낸 유저의 구글 id
     * @param webApp : 검증하고자하는 webApp URL
     */
    private static void validWebAppUrlOwner(String requestUserId, WebApp webApp) {
        if (!webApp.getUser().getGoogleId().equals(requestUserId)) {
            throw new NotOwnerException("다른 회원의 WebAppURL입니다. URL = " + webApp.getAppUrl());
        }
    }
}
