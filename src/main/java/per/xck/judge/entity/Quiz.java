package per.xck.judge.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "quiz")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String questionId;

    @Column
    private String studentId;

    @Column
    private String quizType;

    @Column
    private Long startTime;

    @Column
    private Long endTime;

    @Column
    private String quizName;

    @Column
    private Integer teacherId;
}
