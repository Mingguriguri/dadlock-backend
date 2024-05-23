package swe.backend.dadlock.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import swe.backend.dadlock.common.response.ApiResponse;
import swe.backend.dadlock.common.response.StatusEnum;
import swe.backend.dadlock.dto.oauth2.CustomOAuth2User;
import swe.backend.dadlock.dto.quiz.QuizRequestDTO;
import swe.backend.dadlock.dto.quiz.QuizResponseDTO;
import swe.backend.dadlock.dto.usersession.UserSessionRequestDTO;
import swe.backend.dadlock.dto.usersession.UserSessionResponseDTO;
import swe.backend.dadlock.service.QuizService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/quizzes")
@Slf4j
public class QuizController {

    private final QuizService quizService;
    private static final Logger logger = LoggerFactory.getLogger(QuizController.class);

    @GetMapping
    public ApiResponse<QuizResponseDTO.CommonDTO> getRandomQuizByUrl(@AuthenticationPrincipal CustomOAuth2User user, @RequestParam String appUrl) {
        if (user == null) {
            return ApiResponse.responseSuccess(StatusEnum.FORBIDDEN, null, "인증되지 않은 사용자입니다");
        }
        try {
            QuizResponseDTO.CommonDTO quiz = quizService.getRandomQuizBySubject(user.getGoogleId(), appUrl); // 유저가 설정한 Url에 따른 퀴즈 주제를 기준으로 랜덤 문제
            return ApiResponse.responseSuccess(StatusEnum.OK, quiz, "퀴즈 조회 성공");
        } catch (Exception e) {
            logger.error("Error getting quiz", e.getMessage(), e);
            return ApiResponse.responseSuccess(StatusEnum.INTERNAL_SERVER_ERROR, null, "퀴즈 조회 실패 : " + e.getMessage());
        }
    }

    // 전체 문제 랜덤
    @GetMapping("/all")
    public ApiResponse<QuizResponseDTO.CommonDTO> getRandomQuiz(@AuthenticationPrincipal CustomOAuth2User user) {
        if (user == null) {
            return ApiResponse.responseSuccess(StatusEnum.FORBIDDEN, null, "인증되지 않은 사용자입니다");
        }
        try {
            QuizResponseDTO.CommonDTO quiz = quizService.getRandomQuiz(); // 랜덤 문제 가져오기
            return ApiResponse.responseSuccess(StatusEnum.OK, quiz, "퀴즈 조회 성공");
        } catch (Exception e) {
            logger.error("Error getting quiz", e.getMessage(), e);
            return ApiResponse.responseSuccess(StatusEnum.INTERNAL_SERVER_ERROR, null, "퀴즈 조회 실패 : " + e.getMessage());
        }
    }

    @PostMapping("/{quiz_id}/attempt")
    public ApiResponse<QuizResponseDTO.AttemptDTO> attemptQuiz(@AuthenticationPrincipal CustomOAuth2User user, @PathVariable("quiz_id") Long quizId, @RequestBody QuizRequestDTO.AttemptDTO attemptDTO){
        if (user == null) {
            return ApiResponse.responseSuccess(StatusEnum.FORBIDDEN, null, "인증되지 않은 사용자입니다");
        }
        try {
            QuizResponseDTO.AttemptDTO attempt = quizService.saveQuizAttempt(user.getGoogleId(), quizId, attemptDTO);
            return ApiResponse.responseSuccess(StatusEnum.OK, attempt, "퀴즈 답변 성공");
        } catch (Exception e){
            logger.error("Error attempting quiz", e);
            return ApiResponse.responseSuccess(StatusEnum.INTERNAL_SERVER_ERROR, null, "퀴즈 답변 업로드 실패 : " + e.getMessage());
        }
    }

}
