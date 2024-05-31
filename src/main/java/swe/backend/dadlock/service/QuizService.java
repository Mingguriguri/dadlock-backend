package swe.backend.dadlock.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swe.backend.dadlock.dto.quiz.QuizRequestDTO;
import swe.backend.dadlock.dto.quiz.QuizResponseDTO;
import swe.backend.dadlock.entity.Quiz;
import swe.backend.dadlock.entity.QuizAttempt;
import swe.backend.dadlock.entity.Subject;
import swe.backend.dadlock.entity.User;
import swe.backend.dadlock.repository.QuizAttemptRepository;
import swe.backend.dadlock.repository.QuizRepository;
import swe.backend.dadlock.repository.UserRepository;
import swe.backend.dadlock.repository.WebAppRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = false)
@RequiredArgsConstructor
@Slf4j
public class QuizService {

    private final QuizRepository quizRepository;
    private final QuizAttemptRepository quizAttemptRepository;
    private final UserRepository userRepository;
    private final WebAppRepository webAppRepository;

    // 특정 주제에 대한 랜덤 퀴즈를 문제 레벨별로 반환
    public QuizResponseDTO.CommonDTO getRandomQuizBySubject(String userGoogleId, String appUrl){
        Subject subject = webAppRepository.findWebAppByUserGoogleIdAndAppUrl(userGoogleId, appUrl)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 설정한 주제가 없습니다."))
                .getSubject();

        Quiz.QuizLevel level = determineQuizLevel(userGoogleId, subject);

        List<Quiz> quizzes = quizRepository.findQuizzesBySubjectAndLevel(subject, level, PageRequest.of(0, 1));
        Quiz quiz = quizzes.stream().findFirst().orElseThrow(() -> new IllegalArgumentException("해당 주제의 퀴즈를 찾을 수 없습니다."));
        return new QuizResponseDTO.CommonDTO(quiz);
    }

    private Quiz.QuizLevel determineQuizLevel(String userGoogleId, Subject subject) {
        long incorrectCount = quizAttemptRepository.countByUserGoogleIdAndSubjectAndIsCorrectFalse(userGoogleId, subject);

        if (incorrectCount >= 2) {
            return Quiz.QuizLevel.HD;
        } else if (incorrectCount == 1) {
            return Quiz.QuizLevel.MD;
        } else {
            return Quiz.QuizLevel.EZ;
        }
    }

    // 기존 랜덤 퀴즈 (랜덤하게 정렬된 퀴즈 리스트에서 맨 앞에 있는 퀴즈를 선택)
    public QuizResponseDTO.CommonDTO getRandomQuiz() {
        List<Quiz> quizzes = quizRepository.getRandomQuiz(PageRequest.of(0, 1));
        Quiz quiz = quizzes.stream().findFirst().orElseThrow(() -> new IllegalArgumentException("퀴즈를 찾을 수 없습니다."));
        return new QuizResponseDTO.CommonDTO(quiz);
    }

    // 퀴즈에 응답한 답변의 정답유무
    public QuizResponseDTO.AttemptDTO saveQuizAttempt(String userGoogleId, Long quizId, QuizRequestDTO.AttemptDTO attemptDTO) {
        User user = findUserByGoogleId(userGoogleId);
        Quiz quiz = findQuizById(quizId);

        // 퀴즈에 답변한 답이 정답인지 판단
        boolean isCorrect = quizRepository.getCorrectAnswer(quizId)
                .map(answer -> answer.equals(attemptDTO.getAnswer()))
                .orElse(false);

        // 퀴즈 Attempt에 저장
        QuizAttempt attempt = QuizAttempt.builder()
                .quiz(quiz)
                .user(user)
                .isCorrect(isCorrect)
                .attemptTime(LocalDateTime.now())
                .level(quiz.getLevel())
                .build();

        QuizAttempt savedAttempt = quizAttemptRepository.save(attempt);

        // 정답일 경우 틀린 횟수 초기화
        if (isCorrect) {
            quizAttemptRepository.resetIncorrectCount(userGoogleId, quiz.getSubject());
        }

        return new QuizResponseDTO.AttemptDTO(savedAttempt);
    }

    // 회원 조회
    private User findUserByGoogleId(String userGoogleId) {
        User findUser = userRepository.findById(userGoogleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다. userGoogleId = " + userGoogleId));
        return findUser;
    }

    // 퀴즈 조회
    private Quiz findQuizById(Long quizId) {
        return quizRepository.findById(quizId)
                .orElseThrow(() -> new IllegalArgumentException("해당 퀴즈가 존재하지 않습니다. quizId = " + quizId));
    }

    // 유저가 특정 URL을 등록했는지 검증
    public void validateUserAppUrl(String userGoogleId, String appUrl) {
        boolean exists = webAppRepository.findWebAppByUserGoogleIdAndAppUrl(userGoogleId, appUrl).isPresent();
        if (!exists) {
            throw new IllegalArgumentException("해당 URL이 등록되지 않았습니다. appUrl = " + appUrl);
        }
    }


}
