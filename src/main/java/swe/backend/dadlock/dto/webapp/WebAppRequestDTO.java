package swe.backend.dadlock.dto.webapp;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import swe.backend.dadlock.entity.User;
import swe.backend.dadlock.entity.WebApp;

public class WebAppRequestDTO {

    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    public static class CreateDTO {
        private String appUrl;
        private int timeLimit;

        public WebApp toEntity(User user) {
            return WebApp.builder()
                    .user(user)
                    .appUrl(appUrl)
                    .timeLimit(timeLimit)
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
