package swe.backend.dadlock.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
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

    @Column(nullable = false, updatable = false)
    private LocalDateTime attemptTime = LocalDateTime.now();

    @Builder
    public QuizAttempt(Quiz quiz, User user, Boolean isCorrect, LocalDateTime attemptTime) {
        this.quiz = quiz;
        this.user = user;
        this.isCorrect = isCorrect;
        this.attemptTime = attemptTime;
    }
}
