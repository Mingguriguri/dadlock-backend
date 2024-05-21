package swe.backend.dadlock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import swe.backend.dadlock.entity.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {

    // 퀴즈 랜덤설정
    @Query(value = "SELECT * FROM Quiz ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Quiz findRandomQuiz();
}
