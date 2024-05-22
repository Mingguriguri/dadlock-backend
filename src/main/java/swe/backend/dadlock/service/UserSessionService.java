package swe.backend.dadlock.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swe.backend.dadlock.dto.oauth2.CustomOAuth2User;
import swe.backend.dadlock.dto.usersession.UserSessionRequestDTO;
import swe.backend.dadlock.dto.usersession.UserSessionResponseDTO;
import swe.backend.dadlock.dto.webapp.WebAppRequestDTO;
import swe.backend.dadlock.dto.webapp.WebAppResponseDTO;
import swe.backend.dadlock.entity.User;
import swe.backend.dadlock.entity.UserSession;
import swe.backend.dadlock.entity.WebApp;
import swe.backend.dadlock.exception.NotOwnerException;
import swe.backend.dadlock.repository.UserRepository;
import swe.backend.dadlock.repository.UserSessionRepository;
import swe.backend.dadlock.repository.WebAppRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = false)
@RequiredArgsConstructor
public class UserSessionService {

    private final UserSessionRepository userSessionRepository;
    private final UserRepository userRepository;

    // 세션 시작
    public UserSessionResponseDTO.CommonDTO startSession(String userGoogleId, UserSessionRequestDTO.StartDTO requestDTO) {
        User user = findUserByGoogleId(userGoogleId);
        UserSession userSession = requestDTO.toEntity(user);
        UserSession savedSession = userSessionRepository.save(userSession);
        return new UserSessionResponseDTO.CommonDTO(savedSession);
    }

    // 세션 업데이트
    public UserSessionResponseDTO.CommonDTO updateSession(String userGoogleId, UserSessionRequestDTO.UpdateDTO requestDTO) {
        User user = findUserByGoogleId(userGoogleId);
        UserSession userSession = userSessionRepository.findByUserAndAppUrl(user.getGoogleId(), requestDTO.getAppUrl())
                .orElseThrow(() -> new IllegalArgumentException("세션을 찾을 수 없습니다."));
        userSession.updateSession();
        UserSession updatedSession = userSessionRepository.save(userSession);
        return new UserSessionResponseDTO.CommonDTO(updatedSession);
    }

    // 세션 종료
    public UserSessionResponseDTO.CommonDTO endSession(String userGoogleId, UserSessionRequestDTO.EndDTO requestDTO) {
        User user = findUserByGoogleId(userGoogleId);
        UserSession userSession = userSessionRepository.findByUserAndAppUrl(user.getGoogleId(), requestDTO.getAppUrl())
                .orElseThrow(() -> new IllegalArgumentException("세션을 찾을 수 없습니다."));
        userSession.endSession();
        UserSession endedSession = userSessionRepository.save(userSession);
        return new UserSessionResponseDTO.CommonDTO(endedSession);
    }

    // 유저 조회
    private User findUserByGoogleId(String userGoogleId) {
        User findUser = userRepository.findById(userGoogleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다. userGoogleId = " + userGoogleId));
        return findUser;
    }
}
