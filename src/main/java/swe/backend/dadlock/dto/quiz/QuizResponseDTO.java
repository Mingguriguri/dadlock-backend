package swe.backend.dadlock.dto.quiz;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import swe.backend.dadlock.entity.Quiz;
import swe.backend.dadlock.entity.QuizAttempt;
import swe.backend.dadlock.entity.UserSession;

import java.time.LocalDateTime;

public class QuizResponseDTO {

    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    public static class CommonDTO {
        private Long id;
        private String subject;
        private Quiz.QuizType type;
        private Quiz.QuizLevel level;
        private String question;
        private String optionA;
        private String optionB;
        private String optionC;
        private String optionD;
        private String optionE;

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

        }
    }

    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    public static class AttemptDTO {
        private Long attemptId;
        private Long quizId;
        private Boolean isCorrect;
        private LocalDateTime attemptTime;

        public AttemptDTO(QuizAttempt attempt) {
            this.attemptId = attempt.getAttemptId();
            this.quizId = attempt.getQuiz().getQuizId();
            this.isCorrect = attempt.getIsCorrect();
            this.attemptTime = attempt.getAttemptTime();
        }
    }
}
