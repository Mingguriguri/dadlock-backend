package swe.backend.dadlock.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swe.backend.dadlock.dto.quiz.QuizRequestDTO;
import swe.backend.dadlock.dto.quiz.QuizResponseDTO;
import swe.backend.dadlock.entity.Quiz;
import swe.backend.dadlock.entity.QuizAttempt;
import swe.backend.dadlock.entity.User;
import swe.backend.dadlock.repository.QuizAttemptRepository;
import swe.backend.dadlock.repository.QuizRepository;
import swe.backend.dadlock.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = false)
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;
    private final QuizAttemptRepository quizAttemptRepository;
    private final UserRepository userRepository;

    // 랜덤 퀴즈 (랜덤하게 정렬된 퀴즈 리스트에서 맨 앞에 있는 퀴즈를 선택)
    public QuizResponseDTO.CommonDTO getRandomQuiz() {
        List<Quiz> quizzes = quizRepository.getRandomQuiz(PageRequest.of(0, 1));
        Quiz quiz = quizzes.stream().findFirst().orElseThrow(() -> new IllegalArgumentException("퀴즈를 찾을 수 없습니다."));
        return new QuizResponseDTO.CommonDTO(quiz);
    }

    // 퀴즈에 응답한 답변의 정답유무
    public QuizResponseDTO.AttemptDTO saveQuizAttempt(String userGoogleId, Long quizId, QuizRequestDTO.AttemptDTO attemptDTO) {
        User user = findUserByGoogleId(userGoogleId);
        Quiz quiz = findQuizById(quizId);

        boolean isCorrect = quizRepository.getCorrectAnswer(quizId)
                .map(answer -> answer.equals(attemptDTO.getAnswer()))
                .orElse(false);

        QuizAttempt attempt = QuizAttempt.builder()
                .quiz(quiz)
                .user(user)
                .isCorrect(isCorrect)
                .attemptTime(LocalDateTime.now())
                .build();

        QuizAttempt savedAttempt = quizAttemptRepository.save(attempt);

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
}
