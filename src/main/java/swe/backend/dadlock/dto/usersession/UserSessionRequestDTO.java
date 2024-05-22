package swe.backend.dadlock.dto.usersession;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import swe.backend.dadlock.entity.User;
import swe.backend.dadlock.entity.UserSession;
import swe.backend.dadlock.entity.WebApp;

import java.time.LocalDateTime;

public class UserSessionRequestDTO {

    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    public static class StartDTO {
        private String appUrl;
        private int timeLimit;

        public UserSession toEntity(User user){
            return UserSession.builder()
                    .user(user)
                    .appUrl(appUrl)
                    .startTime(LocalDateTime.now())
                    .lastUpdateTime(LocalDateTime.now())
                    .build();
        }
    }

    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    public static class UpdateDTO {
        private String appUrl;

    }

    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    public static class EndDTO {
        private String appUrl;
    }
}
