package swe.backend.dadlock.repository;

import swe.backend.dadlock.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    List<User> findByDeleteAt(LocalDate deleteAt);
    List<User> findAllByGoogleIdIn(List<String> googleIds);
}
