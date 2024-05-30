package swe.backend.dadlock.dto.webapp;

import lombok.*;
import swe.backend.dadlock.entity.Subject;
import swe.backend.dadlock.entity.WebApp;

public class WebAppResponseDTO {

    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    @AllArgsConstructor
    @Builder
    public static class CommonDTO {
        private Long id;
        private String appUrl;
        private int timeLimit;
        private Subject subject;

        public CommonDTO(WebApp webApp) {
            this.id = webApp.getId();
            this.appUrl = webApp.getAppUrl();
            this.timeLimit = webApp.getTimeLimit();
            this.subject = webApp.getSubject();
        }
    }
}
