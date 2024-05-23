package swe.backend.dadlock.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import swe.backend.dadlock.dto.webapp.WebAppRequestDTO;


@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class WebApp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String appUrl;

    private int timeLimit;

    @Enumerated(EnumType.STRING)
    Subject subject;

    @Builder
    public WebApp(User user, String appUrl, int timeLimit, Subject subject) {
        this.user = user;
        this.appUrl = appUrl;
        this.timeLimit = timeLimit;
        this.subject = subject;
    }

    public void updateInfo(WebAppRequestDTO.UpdateDTO dto) {
        this.appUrl = dto.getAppUrl();
        this.timeLimit = dto.getTimeLimit();
    }
}
