package per.xck.judge.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "question")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String content;
    @Column
    private String type;
    @Column
    private String answer;
    @Column
    private String input;
    @Column
    private String output;
    @Column
    private String input2;
    @Column
    private String output2;
    @Column
    private String input3;
    @Column
    private String output3;
    @Column
    private String choice_a;
    @Column
    private String choice_b;
    @Column
    private String choice_c;
    @Column
    private String choice_d;
    @Column
    private String questionImg;

}
