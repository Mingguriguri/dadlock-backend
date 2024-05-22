package swe.backend.dadlock.dto.quiz;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class QuizRequestDTO {
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    public static class AttemptDTO {
        private String answer;
    }
}
