package swe.backend.dadlock.dto.webapp;

import lombok.*;
import swe.backend.dadlock.entity.Subject;
import swe.backend.dadlock.entity.User;
import swe.backend.dadlock.entity.WebApp;

public class WebAppRequestDTO {

    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    @Builder
    @AllArgsConstructor
    public static class CreateDTO {
        private String appUrl;
        private int timeLimit;
        private Subject subject;

        public WebApp toEntity(User user) {
            return WebApp.builder()
                    .user(user)
                    .appUrl(appUrl)
                    .timeLimit(timeLimit)
                    .subject(subject)
                    .build();
        }
    }

    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    public static class UpdateDTO {
        private String appUrl;
        private int timeLimit;
    }
}
