package per.xck.judge;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.util.ArrayUtils;
import per.xck.judge.entity.*;
import per.xck.judge.repository.LogRepository;
import per.xck.judge.repository.QuestionRepository;
import per.xck.judge.repository.QuizRepository;
import per.xck.judge.repository.UserRepository;
import per.xck.judge.service.JudgeService;
import sun.security.util.ArrayUtil;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest
@Transactional
class JudgeApplicationTests {

    @Autowired
    UserRepository userRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    QuizRepository quizRepository;
    @Autowired
    LogRepository logRepository;

    @Autowired
    JudgeService judgeService;

    @Test
    void contextLoads() throws ParseException {

//        JudgeTask judgeTask = new JudgeTask(30, 3, 2, "public class Main {\n" +
//                "    public static void main(String[] args) {\n" +
//                "int[] height = new int[args.length];\n" +
//                "for(int i = 0; i < args.length; i++){height[i] = Integer.parseInt(args[i]);}\n" +
//                "        int max = 0;\n" +
//                "        int i = 0, j = height.length - 1;\n" +
//                "        while(i != j){\n" +
//                "            int tempArea = (height[i]<height[j]?height[i]:height[j])*(j-i);\n" +
//                "            if(tempArea > max)\n" +
//                "                max = tempArea;\n" +
//                "            if(height[i] > height[j])\n" +
//                "                j--;\n" +
//                "            else\n" +
//                "                i++;\n" +
//                "        }\n" +
//                "        System.out.println(max);\n" +
//                "    }\n" +
//                "}");
//
//        String result = judgeService.judge(judgeTask);
//        System.out.println(result);


//        List<User> all = userRepository.findAll();
//        System.out.println(Arrays.toString(all.toArray()));

//        List<Question> all = questionRepository.findAll();
//        System.out.println(all.get(1).getChoice_b());

//        Quiz quiz = new Quiz();
//        quiz.setId(2);
//        quiz.setTeacherId(2);
//        quiz.setStartTime(System.currentTimeMillis());
//        quiz.setEndTime(System.currentTimeMillis() + 3600 * 1000);
//        quiz.setQuizName("第一场");
//        int[] arr = new int[]{1, 3, 4, 7};
//        String str = Arrays.toString(arr);
//        quiz.setQuestionId(str);
//        quizRepository.save(quiz);

//        String[] newArr = str.substring(1,str.length()-1).split(",");
//        List Arr = new ArrayList<Integer>();
//        for (String s:newArr){
//            Arr.add(Integer.parseInt(s));
//        }



        /*
         * 将时间转换为时间戳
         */
//        String res;
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date = simpleDateFormat.parse("2020-04-20 12:30:01");
//        long ts = date.getTime();
//        res = String.valueOf(ts);
//        System.out.println(res);        //1587357001000


        Log log = new Log();
        log.setQuizId(14);
        log.setStudentName("夏崇康");
        log.setScore(100);
        log.setSubmitTime(System.currentTimeMillis());
        log.setSinglechoiceCount(15);
        log.setTrueorfalseCount(15);
        log.setFillintheblankCount(10);
        log.setReadprogramCount(20);
        log.setProgramfillintheblankCount(10);
        log.setProgramingCount(30);
        logRepository.save(log);

    }

}
