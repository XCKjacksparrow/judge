package per.xck.judge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import per.xck.judge.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "select * from user where type = 'student'",nativeQuery = true)
    List<User> findAllStudents();
    @Query(value = "select * from user where type = 'teacher'",nativeQuery = true)
    List<User> findAllTeachers();

    @Modifying
    @Query(value = "delete from user where id = ?1",nativeQuery = true)
    void deleteById(@Param("id") Integer id);

    @Query(value = "select name from user where id = ?1",nativeQuery = true)
    String getTeacherNameById(@Param("id") Integer teacherId);

    @Query(value = "select id from user where name = ?1", nativeQuery = true)
    Integer getIdByName(@Param("teacherName") String teacherName);
}
