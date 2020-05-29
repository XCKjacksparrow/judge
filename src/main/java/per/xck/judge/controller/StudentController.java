package per.xck.judge.controller;

import org.omg.PortableServer.POA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import per.xck.judge.entity.*;
import per.xck.judge.repository.LogRepository;
import per.xck.judge.repository.QuestionRepository;
import per.xck.judge.repository.QuizRepository;
import per.xck.judge.repository.UserRepository;
import per.xck.judge.service.JudgeService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class StudentController {

    @Autowired
    QuizRepository quizRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    LogRepository logRepository;

    @Autowired
    JudgeService judgeService;

    // 进入学生页面
    @RequestMapping("/success/student")
    public String successStudent(Model model) {
        List<Quiz> allQuiz = quizRepository.findAll();
        model.addAttribute("allQuiz", allQuiz);
        return "student/index";
    }

    // 进入学生成绩查询页面
    @RequestMapping("/grade")
    public String grade(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        List<Log> all = logRepository.findAll();
        List<Log> allLogs = new ArrayList<>();
        List<String> quizNames = new ArrayList<>();
        for (Log log : all) {
            if (log.getStudentName().equals(user.getName())) {
                Integer id = log.getQuizId();
                quizNames.add(quizRepository.getNameById(id));
                allLogs.add(log);
            }
        }
        model.addAttribute("allLogs", allLogs);
        model.addAttribute("quizNames", quizNames);
        return "student/grade";
    }

    /**
     * 考试页面
     *
     * @param session
     * @return
     */
    @RequestMapping("/gotoQuiz/{quizid}")
    public String gotoQuiz(@PathVariable String quizid, HttpSession session,
                           Model model) {
        User user = (User) session.getAttribute("user");

        Quiz one = quizRepository.getOne(Integer.parseInt(quizid));
        long currentTime = System.currentTimeMillis();
        // 判断时间
        if (currentTime < one.getStartTime() || currentTime > one.getEndTime()) {
            return "student/noquiz";
        } else {
            //    判断是否考过
            List<Log> allLogs = logRepository.findCurrentLogs(quizid);    // 考试记录
            for (Log log : allLogs) {
                if (log.getStudentName().equals(user.getName())) {
                    return "student/done";      // 已经考过
                }
            }
            // 判断是否是private  包不包含此人
            if (one.getQuizType().equals("private")) {

                String studentId = one.getStudentId();
                String[] newArr = studentId.substring(1, studentId.length() - 1).split(",");
                List<Integer> Arr = new ArrayList<Integer>();
                for (String s : newArr) {
                    Arr.add(Integer.parseInt(s));
                }
                if (!Arr.contains(user.getId()))        //      不包含
                    return "student/notbelongtoyou";
            }      // 说明此人正常参加考试
            model.addAttribute("quizId", quizid);
            String questionId = one.getQuestionId();
            String[] newArr = questionId.substring(1, questionId.length() - 1).split(",");
            List<Integer> Arr = new ArrayList<Integer>();
            for (String s : newArr) {
                Arr.add(Integer.parseInt(s));
            }
            List<Question> allSingleChoice = new ArrayList<>();
            List<Question> allTrueOrFalse = new ArrayList<>();
            List<Question> allFillInTheBlank = new ArrayList<>();
            List<Question> allReadProgram = new ArrayList<>();
            List<Question> allProgramFillInTheBlank = new ArrayList<>();
            List<Question> allCorrectMistake = new ArrayList<>();
            List<Question> allPrograming = new ArrayList<>();
            for (Integer id : Arr) {
                Question question = questionRepository.getOne(id);
                if ("Singel Choice".equals(question.getType())) {
                    allSingleChoice.add(question);
                } else if ("True Or False".equals(question.getType())) {
                    allTrueOrFalse.add(question);
                } else if ("Fill In The Blank".equals(question.getType())) {
                    allFillInTheBlank.add(question);
                } else if ("Read Program".equals(question.getType())) {
                    allReadProgram.add(question);
                } else if ("Program Fill In The Blank".equals(question.getType())) {
                    allProgramFillInTheBlank.add(question);
                } else if ("Correct Mistake".equals(question.getType())) {
                    allCorrectMistake.add(question);
                } else if ("Programing".equals(question.getType())) {
                    allPrograming.add(question);
                }
                model.addAttribute("allSingleChoice", allSingleChoice)
                        .addAttribute("allTrueOrFalse", allTrueOrFalse)
                        .addAttribute("allFillInTheBlank", allFillInTheBlank)
                        .addAttribute("allProgramFillInTheBlank", allProgramFillInTheBlank)
                        .addAttribute("allReadProgram", allReadProgram)
                        .addAttribute("allCorrectMistake", allCorrectMistake)
                        .addAttribute("allPrograming", allPrograming);
            }
            return "student/doQuiz";
        }
    }


    @RequestMapping("/submitQuiz")
    @ResponseBody
    public String submitQuiz(@RequestParam("studentName") String studentName,
                             @RequestParam("singleChoiceAnswer") String singleChoiceAnswer,
                             @RequestParam("trueOrFalseAnswer") String trueOrFalseAnswer,
                             @RequestParam("fillInTheBlankAnswer") String fillInTheBlankAnswer,
                             @RequestParam("readProgramAnswer") String readProgramAnswer,
                             @RequestParam("programFillInTheBlankAnswer") String programFillInTheBlankAnswer,
                             @RequestParam("correctMistakeAnswer") String correctMistakeAnswer,
                             @RequestParam("programingAnswer") String programingAnswer,
                             @RequestParam("quizId") String quizId
    ) {
        System.out.println(studentName);
        String[] SingleChoice = singleChoiceAnswer.split(",");
        String[] TrueOrFalse = trueOrFalseAnswer.split(",");
        String[] FillInTheBlank = fillInTheBlankAnswer.split(",");
        String[] ReadProgram = readProgramAnswer.split(",");
        String[] ProgramFillInTheBlank = programFillInTheBlankAnswer.split(",");
        String[] CorrectMistake = correctMistakeAnswer.split(",");
        String[] Programing = programingAnswer.split(",");

        int total = 0;                      // 总分100分
        Quiz quiz = quizRepository.getOne(Integer.parseInt(quizId));
        String questionId = quiz.getQuestionId();
        String[] newArr = questionId.substring(1, questionId.length() - 1).split(",");
        List<Integer> Arr = new ArrayList<>();
        for (String s : newArr) {
            Arr.add(Integer.parseInt(s));
        }
//        System.out.println(Arr);          [1, 5, 10, 11, 12, 3, 4, 14, 15, 16, 8, 20, 21, 22, 23, 9, 24, 6, 27, 30, 35]
        for (int i = 0; i < 5; i++) {           //选择题
            Question question = questionRepository.getOne(Arr.get(i));
            if (SingleChoice[i].trim().equals(question.getAnswer().trim())) {
                total += 2;
            }
            ;
        }
        int SingleChoiceCount = total;
        System.out.println("选择题分数" + SingleChoiceCount);

        int TrueOrFalseCount = 0;
        for (int i = 0; i < 5; i++) {            //判断题
            Question question = questionRepository.getOne(Arr.get(i + 5));
            if (TrueOrFalse[i].trim().equals(question.getAnswer().trim())) {
                total += 2;
                TrueOrFalseCount += 2;
            }
        }
        System.out.println("判断题" + TrueOrFalseCount);

        int FillInTheBlankCount = 0;
        for (int i = 0; i < 5; i++) {             //填空题
            Question question = questionRepository.getOne(Arr.get(i + 10));
            if (FillInTheBlank[i].trim().equals(question.getAnswer().trim())) {
                total += 2;
                FillInTheBlankCount += 2;
            }
        }
        System.out.println("填空题" + FillInTheBlankCount);

        int ReadProgramCount = 0;
        for (int i = 0; i < 2; i++) {                //程序阅读题目
            Question question = questionRepository.getOne(Arr.get(i + 15));
            if (ReadProgram[i].trim().equals(question.getAnswer().trim())) {
                total += 10;
                ReadProgramCount += 10;
            }
        }
        System.out.println("程序阅读题目" + ReadProgramCount);

        int ProgramFillInTheBlankCount = 0;
        for (int i = 0; i < 2; i++) {                //程序填空题
            Question question = questionRepository.getOne(Arr.get(i + 17));
            if (ProgramFillInTheBlank[i].trim().equals(question.getAnswer().trim())) {
                total += 5;
                ProgramFillInTheBlankCount += 5;
            }
        }
        System.out.println("程序填空题" + ProgramFillInTheBlankCount);

        int CorrectMistakeCount = 0;
        JudgeTask judgeTask0 = new JudgeTask(Arr.get(19), 3, 2, CorrectMistake[0]);
        String result0 = judgeService.judge(judgeTask0);
        if (result0.equals("Create failed")) {
            System.out.println("文件创建失败");
        } else {
            if (result0.equals("Compile failed")) {
                System.out.println("编译失败");
            } else {
                if (result0.equals("failed")) {
                    System.out.println("运行出错");
                }
                total += 10;
                CorrectMistakeCount += 10;
            }
        }
        System.out.println("程序改错题" + CorrectMistakeCount);

        int ProgramingCount = 0;
        for (int i = 0; i < 2; i++) {                   //编程题
            JudgeTask judgeTask = new JudgeTask(Arr.get(i + 20), 3, 2, Programing[i]);
            String result = judgeService.judge(judgeTask);
            if (result.equals("Create failed")) {
                System.out.println("文件创建失败");
            } else {
                if (result.equals("Compile failed")) {
                    System.out.println("编译失败");
                } else {
                    if (result.equals("failed")) {
                        System.out.println("运行出错");
                    }
                    total += 5 * Integer.parseInt(result);
                    ProgramingCount += 5 * Integer.parseInt(result);
                }
            }
        }
        System.out.println("编程题" + ProgramingCount);


        Long time = System.currentTimeMillis();


        Log log = new Log();
        log.setQuizId(Integer.parseInt(quizId));
        log.setStudentName(studentName);
        log.setScore(total);
        log.setSubmitTime(time);
        log.setSinglechoiceCount(SingleChoiceCount);
        log.setTrueorfalseCount(TrueOrFalseCount);
        log.setFillintheblankCount(FillInTheBlankCount);
        log.setReadprogramCount(ReadProgramCount);
        log.setProgramfillintheblankCount(ProgramFillInTheBlankCount);
        log.setCorrectmistakeCount(CorrectMistakeCount);
        log.setProgramingCount(ProgramingCount);
        logRepository.save(log);


        System.out.println("总分：" + total);
        return "success";
    }
}


//public class Main {
//    public static void main(String[] args) {
//        int[] height = new int[args.length];
//        for(int i = 0; i < args.length; i++){height[i] = Integer.parseInt(args[i]);}
//        int max = 0;
//        int i = 0;int j = height.length - 1;
//        while(i != j){
//            int tempArea = (height[i]<height[j]?height[i]:height[j])*(j-i);
//            if(tempArea > max)
//                max = tempArea;
//            if(height[i] > height[j])
//                j--;
//            else
//                i++;
//        }
//        System.out.println(max);
//    }
//}