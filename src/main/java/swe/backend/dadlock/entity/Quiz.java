package swe.backend.dadlock.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Quiz {

    @Id
    @Column(name = "quiz_id")
    private Long quizId;

    private String subject;

    @Enumerated(EnumType.STRING)
    private QuizType type;

    @Enumerated(EnumType.STRING)
    private QuizLevel level;

    @Column(columnDefinition = "TEXT")
    private String question;

    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String optionE;

    private String correctAnswer;

    public enum QuizType {
        MC, TF, SA
    }

    public enum QuizLevel {
        EZ, MD, HD
    }
}
