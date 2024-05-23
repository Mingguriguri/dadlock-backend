package swe.backend.dadlock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import swe.backend.dadlock.entity.WebApp;

import java.util.List;

@Repository
public interface WebAppRepository extends JpaRepository<WebApp, Long> {

    @Query("select w " +
            "from WebApp w " +
            "where w.user.googleId = :googleId " +
            "order by w.id asc")
    List<WebApp> findWebAppListByGoogleId(@Param("googleId") String googleId);
}
