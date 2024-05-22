package swe.backend.dadlock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import swe.backend.dadlock.entity.UserSession;
import swe.backend.dadlock.entity.WebApp;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserSessionRepository extends JpaRepository<UserSession, Long> {

    @Query("select s " +
            "from UserSession s " +
            "where s.user.googleId = :googleId " +
            "and s.appUrl = :appUrl "+
            "order by s.id asc")
    Optional<UserSession> findByUserAndAppUrl(@Param("googleId") String googleId, @Param("appUrl") String appUrl);
}
