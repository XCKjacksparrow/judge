package per.xck.judge.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JudgeTask {

    private int id;

    private int compilerId;         // 判断是 java c python

    private int problemId;          // 判断是哪到题目

    private String source;          // 代码
}
