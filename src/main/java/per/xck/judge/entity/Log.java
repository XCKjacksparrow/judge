package per.xck.judge.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "log")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String studentName;
    @Column
    private Integer quizId;
    @Column
    private Long submitTime;
    @Column
    private Integer score;
    @Column
    private Integer singlechoiceCount;
    @Column
    private Integer trueorfalseCount;
    @Column
    private Integer fillintheblankCount;
    @Column
    private Integer readprogramCount;
    @Column
    private Integer programfillintheblankCount;
    @Column
    private Integer correctmistakeCount;
    @Column
    private Integer programingCount;

}