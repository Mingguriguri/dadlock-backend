package swe.backend.dadlock.dto.usersession;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;
import swe.backend.dadlock.entity.UserSession;
import swe.backend.dadlock.entity.WebApp;

import java.time.LocalDateTime;

public class UserSessionResponseDTO {

    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    public static class CommonDTO {
        private Long id;
        private String appUrl;
        private LocalDateTime startTime;
        private LocalDateTime lastUpdateTime;
        private LocalDateTime endTime;

        public CommonDTO(UserSession userSession) {
            this.id = userSession.getId();
            this.appUrl = userSession.getAppUrl();
            this.startTime = userSession.getStartTime();
            this.lastUpdateTime = userSession.getLastUpdateTime();
            this.endTime = userSession.getEndTime();

        }
    }
}
