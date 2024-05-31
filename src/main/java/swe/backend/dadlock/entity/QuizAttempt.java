package swe.backend.dadlock.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Builder
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@AllArgsConstructor
public class QuizAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attemptId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private Boolean isCorrect;

    @Builder.Default
    @Column(nullable = false, updatable = false)
    private LocalDateTime attemptTime = LocalDateTime.now();

    // 퀴즈 풀 때 난이도별로 주기 위해서 퀴즈 레벨 컬럼 추가
    @Enumerated(EnumType.STRING)
    private Quiz.QuizLevel level;

    @Builder
    public QuizAttempt(Quiz quiz, User user, Boolean isCorrect, LocalDateTime attemptTime, Quiz.QuizLevel level) {
        this.quiz = quiz;
        this.user = user;
        this.isCorrect = isCorrect;
        this.attemptTime = attemptTime;
        this.level = level;
    }
}
