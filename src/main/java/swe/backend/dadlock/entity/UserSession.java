package swe.backend.dadlock.entity;

import jakarta.persistence.*;
import lombok.*;
import swe.backend.dadlock.dto.usersession.UserSessionRequestDTO;

import java.time.LocalDateTime;


@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class UserSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String appUrl;

    private int timeLimit;

    private LocalDateTime startTime;

    private LocalDateTime lastUpdateTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @Builder
    public UserSession(User user, String appUrl, int timeLimit, LocalDateTime startTime, LocalDateTime lastUpdateTime, LocalDateTime endTime) {
        this.user = user;
        this.appUrl = appUrl;
        this.timeLimit = timeLimit;
        this.startTime = startTime;
        this.lastUpdateTime = lastUpdateTime;
        this.endTime = endTime;
    }

    public void updateSession() {
        this.lastUpdateTime = LocalDateTime.now();
    }

    public void endSession() {
        this.endTime = LocalDateTime.now();
    }

}
