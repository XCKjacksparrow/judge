package per.xck.judge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import per.xck.judge.entity.Question;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

    @Query(value = "select * from question where type = 'Singel Choice'",nativeQuery = true)
    List<Question> findAllSingleChoice();
    @Query(value = "select * from question where type = 'True Or False'",nativeQuery = true)
    List<Question> findAllTrueOrFalse();
    @Query(value = "select * from question where type = 'Fill In The Blank'",nativeQuery = true)
    List<Question> findAllFillInTheBlank();
    @Query(value = "select * from question where type = 'Program Fill In The Blank'",nativeQuery = true)
    List<Question> findAllProgramFillInTheBlank();
    @Query(value = "select * from question where type = 'Read Program'",nativeQuery = true)
    List<Question> findAllReadProgram();
    @Query(value = "select * from question where type = 'Programing'",nativeQuery = true)
    List<Question> findAllPrograming();
    @Query(value = "select * from question where type = 'Correct Mistake'",nativeQuery = true)
    List<Question> findAllCorrectMistake();


    @Modifying
    @Query(value = "delete from question where id = ?1",nativeQuery = true)
    void deleteById(@Param("id") Integer id);
}
