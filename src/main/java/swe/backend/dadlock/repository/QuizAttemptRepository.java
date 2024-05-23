package swe.backend.dadlock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import swe.backend.dadlock.entity.QuizAttempt;
import swe.backend.dadlock.entity.Subject;

@Repository
public interface QuizAttemptRepository extends JpaRepository<QuizAttempt, Long> {

    @Query("SELECT COUNT(qa) " +
            "FROM QuizAttempt qa " +
            "WHERE qa.user.googleId = :userGoogleId " +
            "AND qa.quiz.subject = :subject " +
            "AND qa.isCorrect = false")
    long countByUserGoogleIdAndSubjectAndIsCorrectFalse(@Param("userGoogleId") String userGoogleId, @Param("subject") Subject subject);

    // 사용자가 문제를 맞추면 해당 주제에 대해 틀린 기록이 초기화됨
    @Modifying
    @Query("DELETE FROM QuizAttempt qa " +
            "WHERE qa.user.googleId = :userGoogleId " +
            "AND qa.quiz.subject = :subject " +
            "AND qa.isCorrect = false")
    void resetIncorrectCount(@Param("userGoogleId") String userGoogleId, @Param("subject") Subject subject);
}
