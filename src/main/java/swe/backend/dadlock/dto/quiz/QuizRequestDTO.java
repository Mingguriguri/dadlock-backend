package swe.backend.dadlock.dto.quiz;

import lombok.*;

public class QuizRequestDTO {
    @NoArgsConstructor
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    public static class AttemptDTO {
        private String answer;

//        public static AttemptDTO of(String answer) {
//            AttemptDTO attemptDTO = new AttemptDTO();
//            attemptDTO.answer = answer;
//            return attemptDTO;
//        }
    }


}
