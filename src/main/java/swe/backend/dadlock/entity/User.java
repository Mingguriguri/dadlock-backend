package swe.backend.dadlock.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@ToString
@NoArgsConstructor
@Table(name = "users") // 테이블 이름을 명시적으로 설정
public class User {

    @Id
    @Column
    private String googleId;

    @Column(nullable = false, length = 50, unique = true)
    private String nickname;

    @Column(nullable = false)
    private LocalDateTime recentConnect;

    @Column(nullable = false, length = 15)
    @Enumerated(EnumType.STRING)
    private Role role;

    private LocalDate deleteAt = null;

    @Column(nullable = false)
    private LocalDate createdAt;

    @Builder
    public User(String googleId, String nickname, LocalDateTime recentConnect, Role role, LocalDate deleteAt, LocalDate createdAt) {
        this.googleId = googleId;
        this.nickname = nickname;
        this.recentConnect = recentConnect;
        this.role = role;
        this.deleteAt = deleteAt;
        this.createdAt = createdAt;
    }
}
