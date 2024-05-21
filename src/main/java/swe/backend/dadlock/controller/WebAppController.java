package swe.backend.dadlock.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import swe.backend.dadlock.common.response.ApiResponse;
import swe.backend.dadlock.common.response.StatusEnum;
import swe.backend.dadlock.dto.oauth2.CustomOAuth2User;
import swe.backend.dadlock.dto.webapp.WebAppRequestDTO;
import swe.backend.dadlock.dto.webapp.WebAppResponseDTO;
import swe.backend.dadlock.entity.User;
import swe.backend.dadlock.service.WebAppService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/blocked-urls")
public class WebAppController {

    private final WebAppService webAppService;


    @GetMapping
    public ApiResponse<List<WebAppResponseDTO.CommonDTO>> getWebAppUrlList(@AuthenticationPrincipal CustomOAuth2User user) {
        List<WebAppResponseDTO.CommonDTO> webAppUrlList = webAppService.getUrlList(user.getGoogleId());
        return ApiResponse.responseSuccess(StatusEnum.OK, webAppUrlList, "회원의 URL 리스트 조회 성공!");
    }

    @PostMapping
    public ApiResponse<WebAppResponseDTO.CommonDTO> createBlockUrl(@AuthenticationPrincipal CustomOAuth2User user, @RequestBody WebAppRequestDTO.CreateDTO requestDTO) {
        WebAppResponseDTO.CommonDTO webAppUrl = webAppService.createWebApp(user.getGoogleId(), requestDTO);
        return ApiResponse.responseSuccess(StatusEnum.OK, webAppUrl, "URL 생성 성공!");
    }

    @PutMapping("{id}")
    public ApiResponse<WebAppResponseDTO.CommonDTO> updateBlockUrl(@PathVariable("id") Long webAppId, @AuthenticationPrincipal CustomOAuth2User user, @RequestBody WebAppRequestDTO.UpdateDTO requestDTO) {
        WebAppResponseDTO.CommonDTO webAppUrl = webAppService.updateWebAppInfo(webAppId, user.getGoogleId(), requestDTO);
        return ApiResponse.responseSuccess(StatusEnum.OK, webAppUrl, "URL 수정 성공!");
    }

    @DeleteMapping("{id}")
    public ApiResponse deleteWebApp(@PathVariable("id") Long webAppId, @AuthenticationPrincipal CustomOAuth2User user) {
        webAppService.deleteWebApp(user.getGoogleId(), webAppId);
        return ApiResponse.responseSuccessWithNoContent("URL 삭제 성공!");
    }
}
