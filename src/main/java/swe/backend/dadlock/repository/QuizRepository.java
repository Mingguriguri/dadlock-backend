package swe.backend.dadlock.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import swe.backend.dadlock.entity.Quiz;
import swe.backend.dadlock.entity.Subject;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {

    // quizId로 퀴즈의 정답 조회
    @Query("SELECT q.correctAnswer " +
            "FROM Quiz q " +
            "WHERE q.quizId = :quizId")
    Optional<String> getCorrectAnswer(Long quizId);

    // 퀴즈 랜덤설정
    // 무작위로 정렬 후, 첫번째 결과만 반환
    @Query("SELECT q " +
            "FROM Quiz q " +
            "ORDER BY function('RAND')")
    List<Quiz> getRandomQuiz(Pageable pageable);

    // 퀴즈 주제와 난이도별로 랜덤 설정
    @Query("SELECT q " +
            "FROM Quiz q " +
            "WHERE q.subject = :subject AND q.level = :level " +
            "ORDER BY function('RAND')")
    List<Quiz> findQuizzesBySubjectAndLevel(@Param("subject") Subject subject, @Param("level") Quiz.QuizLevel level, PageRequest pageRequest);

}
