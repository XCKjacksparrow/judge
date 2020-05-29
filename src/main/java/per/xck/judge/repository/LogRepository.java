package per.xck.judge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import per.xck.judge.entity.Log;

import java.util.List;

public interface LogRepository extends JpaRepository<Log,Integer> {
    @Query(value = "select * from log where quiz_id = ?1", nativeQuery = true)
    List<Log> findCurrentLogs(@Param("quizid") String quizid);
}
