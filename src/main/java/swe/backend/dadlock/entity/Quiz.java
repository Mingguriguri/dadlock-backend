package swe.backend.dadlock.entity;

import com.nimbusds.jwt.JWTClaimsSet;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quiz_id")
    private Long quizId;

    @Enumerated(EnumType.STRING)
    private Subject subject;

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

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuizAttempt> quizAttempts;

    /*
    퀴즈 유형
    1. 객관식 (Multiple Choice) -> 줄인 형태: MC
    2. 참거짓 (True/False) -> 줄인 형태: TF
    3. 주관식 단답형 (Short Answer) -> 줄인 형태: SA
     */
    public enum QuizType {
        MC, TF, SA
    }

    /*
    난이도
    1. 쉬움(Easy) -> EZ
    2. 보통(Medium) -> MD
    3. 어려움(Hard) -> HD
     */
    public enum QuizLevel {
        EZ, MD, HD
    }
}
