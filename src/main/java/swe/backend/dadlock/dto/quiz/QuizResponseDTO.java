package swe.backend.dadlock.dto.quiz;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import swe.backend.dadlock.entity.Quiz;
import swe.backend.dadlock.entity.QuizAttempt;
import swe.backend.dadlock.entity.Subject;
import swe.backend.dadlock.entity.UserSession;

import java.time.LocalDateTime;

public class QuizResponseDTO {

    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    public static class CommonDTO {
        // 퀴즈 답변을 할 때는 정답(correctAnswer)을 볼 수 없어야 하기 대문에 correctAnswer는 포함하지 않음
        private Long id;
        private Subject subject;
        private Quiz.QuizType type;
        private Quiz.QuizLevel level;
        private String question;
        private String optionA;
        private String optionB;
        private String optionC;
        private String optionD;
        private String optionE;
        private String correctAnswer;

        public CommonDTO(Quiz quiz) {
            this.id = quiz.getQuizId();
            this.subject = quiz.getSubject();
            this.type = quiz.getType();
            this.level = quiz.getLevel();
            this.question = quiz.getQuestion();
            this.optionA = quiz.getOptionA();
            this.optionB = quiz.getOptionB();
            this.optionC = quiz.getOptionC();
            this.optionD = quiz.getOptionD();
            this.optionE = quiz.getOptionE();
            this.correctAnswer = quiz.getCorrectAnswer();

        }

    }

    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    public static class AttemptDTO {
        private Long attemptId;
        private Long quizId;
        private Boolean isCorrect;
        private LocalDateTime attemptTime;
        private Quiz.QuizLevel level;

        public AttemptDTO(QuizAttempt attempt) {
            this.attemptId = attempt.getAttemptId();
            this.quizId = attempt.getQuiz().getQuizId();
            this.isCorrect = attempt.getIsCorrect();
            this.attemptTime = attempt.getAttemptTime();
            this.level = attempt.getLevel();
        }
    }
}

