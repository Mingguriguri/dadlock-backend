package swe.backend.dadlock.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import swe.backend.dadlock.common.response.ApiResponse;
import swe.backend.dadlock.common.response.StatusEnum;
import swe.backend.dadlock.dto.oauth2.CustomOAuth2User;
import swe.backend.dadlock.dto.usersession.UserSessionRequestDTO;
import swe.backend.dadlock.dto.usersession.UserSessionResponseDTO;
import swe.backend.dadlock.dto.webapp.WebAppRequestDTO;
import swe.backend.dadlock.dto.webapp.WebAppResponseDTO;
import swe.backend.dadlock.service.UserSessionService;
import swe.backend.dadlock.service.WebAppService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/session")
@Slf4j
public class UserSessionController {

    private final UserSessionService userSessionService;
    private static final Logger logger = LoggerFactory.getLogger(UserSessionController.class);

    @PostMapping("/start")
    public ApiResponse<UserSessionResponseDTO.CommonDTO> startSession(@AuthenticationPrincipal CustomOAuth2User user, @RequestBody UserSessionRequestDTO.StartDTO requestDTO) {
        try {
            UserSessionResponseDTO.CommonDTO userSession = userSessionService.startSession(user.getGoogleId(), requestDTO);
            return ApiResponse.responseSuccess(StatusEnum.OK, userSession, "성공적으로 세션 시작");
        } catch (Exception e) {
            logger.error("Error starting session", e);
            return ApiResponse.responseSuccess(StatusEnum.INTERNAL_SERVER_ERROR, null, "세션 시작 실패");
        }

    }

    @PostMapping("/update")
    public ApiResponse<UserSessionResponseDTO.CommonDTO> updateSession(@AuthenticationPrincipal CustomOAuth2User user, @RequestBody UserSessionRequestDTO.UpdateDTO requestDTO){
        try {
            UserSessionResponseDTO.CommonDTO userSession = userSessionService.updateSession(user.getGoogleId(), requestDTO);
            return ApiResponse.responseSuccess(StatusEnum.OK, userSession, "성공적으로 세션 업데이트");
        } catch (Exception e){
            logger.error("Error updating session", e);
            return ApiResponse.responseSuccess(StatusEnum.INTERNAL_SERVER_ERROR, null, "세션 업데이트 실패");
        }
    }

    @PostMapping("/end")
    public ApiResponse<UserSessionResponseDTO.CommonDTO> endSession(@AuthenticationPrincipal CustomOAuth2User user, @RequestBody UserSessionRequestDTO.EndDTO requestDTO){
        try {
            UserSessionResponseDTO.CommonDTO userSession = userSessionService.endSession(user.getGoogleId(), requestDTO);
            return ApiResponse.responseSuccess(StatusEnum.OK, userSession, "성공적으로 세션 종료");
        } catch (Exception e){
            logger.error("Error ending session", e);
            return ApiResponse.responseSuccess(StatusEnum.INTERNAL_SERVER_ERROR, null, "세션 종료 실패");
        }
    }

}
