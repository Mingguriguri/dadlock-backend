package swe.backend.dadlock.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@Slf4j
public class WebAppController {

    private final WebAppService webAppService;
    private static final Logger logger = LoggerFactory.getLogger(WebAppController.class);

    @GetMapping
    public ApiResponse<List<WebAppResponseDTO.CommonDTO>> getWebAppUrlList(@AuthenticationPrincipal CustomOAuth2User user) {
        logger.info("User: {}", user);
        if (user == null) {
            return ApiResponse.responseSuccess(StatusEnum.FORBIDDEN, null, "인증되지 않은 사용자입니다");
        }
        try {
            List<WebAppResponseDTO.CommonDTO> webAppUrlList = webAppService.getUrlList(user.getName());
            return ApiResponse.responseSuccess(StatusEnum.OK, webAppUrlList, "회원의 URL 리스트 조회 성공!");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ApiResponse.responseSuccess(StatusEnum.INTERNAL_SERVER_ERROR, null, "회원 리스트 조회 실패 : " + e.getMessage());
        }

    }

    @PostMapping
    public ApiResponse<WebAppResponseDTO.CommonDTO> createBlockUrl(@AuthenticationPrincipal CustomOAuth2User user, @RequestBody WebAppRequestDTO.CreateDTO requestDTO) {
        if (user == null) {
            return ApiResponse.responseSuccess(StatusEnum.FORBIDDEN, null, "인증되지 않은 사용자입니다");
        }
        try {
            WebAppResponseDTO.CommonDTO webAppUrl = webAppService.createWebApp(user.getGoogleId(), requestDTO);
            return ApiResponse.responseSuccess(StatusEnum.OK, webAppUrl, "URL 생성 성공!");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ApiResponse.responseSuccess(StatusEnum.INTERNAL_SERVER_ERROR, null, "URL 생성 실패 : " + e.getMessage());
        }

    }

    @PutMapping("{id}")
    public ApiResponse<WebAppResponseDTO.CommonDTO> updateBlockUrl(@PathVariable("id") Long webAppId, @AuthenticationPrincipal CustomOAuth2User user, @RequestBody WebAppRequestDTO.UpdateDTO requestDTO) {
        if (user == null) {
            return ApiResponse.responseSuccess(StatusEnum.FORBIDDEN, null, "인증되지 않은 사용자입니다");
        }
        try {
            WebAppResponseDTO.CommonDTO webAppUrl = webAppService.updateWebAppInfo(webAppId, user.getGoogleId(), requestDTO);
            return ApiResponse.responseSuccess(StatusEnum.OK, webAppUrl, "URL 수정 성공!");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ApiResponse.responseSuccess(StatusEnum.INTERNAL_SERVER_ERROR, null, "URL 수정 실패 : " + e.getMessage());
        }

    }

    @DeleteMapping("{id}")
    public ApiResponse deleteWebApp(@PathVariable("id") Long webAppId, @AuthenticationPrincipal CustomOAuth2User user) {
        if (user == null) {
            return ApiResponse.responseSuccess(StatusEnum.FORBIDDEN, null, "인증되지 않은 사용자입니다");
        }
        try {
            webAppService.deleteWebApp(user, webAppId);
            return ApiResponse.responseSuccessWithNoContent("URL 삭제 성공!");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ApiResponse.responseSuccess(StatusEnum.INTERNAL_SERVER_ERROR, null, "URL 삭제 실패 : " + e.getMessage());
        }
    }
}
