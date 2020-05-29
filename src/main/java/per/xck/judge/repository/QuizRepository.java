package per.xck.judge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import per.xck.judge.entity.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {
    @Query(value = "select quiz_name from quiz where id = ?1",nativeQuery = true)
    String getNameById(@Param("id") Integer id);
}
