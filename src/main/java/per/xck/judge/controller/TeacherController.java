package per.xck.judge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import per.xck.judge.entity.Log;
import per.xck.judge.entity.Question;
import per.xck.judge.entity.Quiz;
import per.xck.judge.entity.User;
import per.xck.judge.repository.LogRepository;
import per.xck.judge.repository.QuestionRepository;
import per.xck.judge.repository.QuizRepository;
import per.xck.judge.repository.UserRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class TeacherController {

    @Autowired
    QuizRepository quizRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    LogRepository logRepository;

    /**
     * 教师管理页面
     * @param model
     * @return
     */
    @RequestMapping("/success/teacher")
    public String successTeacher(Model model){
        List<Quiz> allQuiz = quizRepository.findAll();
        List<String> teacherNames = new ArrayList<>();
        for (Quiz quiz : allQuiz){
            String name = userRepository.getTeacherNameById(quiz.getTeacherId());

            teacherNames.add(name);
        }
        model.addAttribute("all",allQuiz);
        model.addAttribute("teacherNames",teacherNames);
        return "teacher/index";
    }

    /**
     * 发布考试页面
     * @param model
     * @return
     */
    @RequestMapping("/publish")
    public String publish(Model model){
        List<Question> allSingleChoice = questionRepository.findAllSingleChoice();
        model.addAttribute("allSingleChoice",allSingleChoice);
        List<Question> allTrueOrFalse = questionRepository.findAllTrueOrFalse();
        model.addAttribute("allTrueOrFalse",allTrueOrFalse);
        List<Question> allFillInTheBlank = questionRepository.findAllFillInTheBlank();
        model.addAttribute("allFillInTheBlank",allFillInTheBlank);
        List<Question> allReadProgram = questionRepository.findAllReadProgram();
        model.addAttribute("allReadProgram",allReadProgram);
        List<Question> allProgramFillInTheBlank = questionRepository.findAllProgramFillInTheBlank();
        model.addAttribute("allProgramFillInTheBlank",allProgramFillInTheBlank);
        List<Question> allCorrectMistake = questionRepository.findAllCorrectMistake();
        model.addAttribute("allCorrectMistake",allCorrectMistake);
        List<Question> allPrograming = questionRepository.findAllPrograming();
        model.addAttribute("allPrograming",allPrograming);
        return "teacher/publish";
    }

    @RequestMapping("/gradeTeacher")
    public String gradeTeacher(Model model){
        List<Quiz> allQuiz = quizRepository.findAll();
        List<String> teacherNames = new ArrayList<>();
        for (Quiz quiz : allQuiz){
            String name = userRepository.getTeacherNameById(quiz.getTeacherId());

            teacherNames.add(name);
        }
        model.addAttribute("all",allQuiz);
        model.addAttribute("teacherNames",teacherNames);
        return "teacher/grade";
    }
    @RequestMapping("/analysis")
    public String analysis(Model model){
        List<Quiz> allQuiz = quizRepository.findAll();
        List<String> teacherNames = new ArrayList<>();
        for (Quiz quiz : allQuiz){
            String name = userRepository.getTeacherNameById(quiz.getTeacherId());

            teacherNames.add(name);
        }
        model.addAttribute("all",allQuiz);
        model.addAttribute("teacherNames",teacherNames);
        return "teacher/analysis";
    }
    // 发布考试
    @RequestMapping("/addQuiz")
    @ResponseBody
    public String addQuiz(@RequestParam("allQuestionId") String allQuestionId,
                          @RequestParam("teacherName") String teacherName,
                          @RequestParam("date") String date,
                          @RequestParam("dateEnd") String dateEnd,
                          @RequestParam("quiztype") String quiztype,
                          @RequestParam("allStudentId") String allStudentId,
                          @RequestParam("quizName") String quizName) throws ParseException {
        Quiz quiz = new Quiz();
        quiz.setQuizType(quiztype);
        quiz.setStudentId("[" + allStudentId + "]");
        quiz.setQuestionId("[" + allQuestionId + "]");
        quiz.setQuizName(quizName);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = simpleDateFormat.parse(date);
        long ts = d.getTime();
        quiz.setStartTime(ts);
//        quiz.setEndTime(ts + 3600 * 1000);
        d = simpleDateFormat.parse(dateEnd);
        ts = d.getTime();
        quiz.setEndTime(ts);
        quiz.setTeacherId(userRepository.getIdByName(teacherName));
        quizRepository.save(quiz);
        return "";
    }
    // 删除考试
    @RequestMapping("deleteQuiz")
    @ResponseBody
    public String deleteQuiz(@RequestParam("id") String id){
        quizRepository.deleteById(Integer.parseInt(id));
        return "删除成功";
    }

    @RequestMapping("/modifyPublish")
    @ResponseBody
    public String modifyPublish(@RequestParam("id")String id,
                                @RequestParam("quizName")String quizName,
                                @RequestParam("date")String date,
    @RequestParam("dateEnd")String dateEnd) throws ParseException {
        Quiz quiz = quizRepository.getOne(Integer.parseInt(id));
        quiz.setQuizName(quizName);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = simpleDateFormat.parse(date);
        long ts = d.getTime();
        quiz.setStartTime(ts);
//        quiz.setEndTime(ts + 3600 * 1000);
        d = simpleDateFormat.parse(dateEnd);
        ts = d.getTime();
        quiz.setEndTime(ts);
        quizRepository.save(quiz);
        return "修改成功！";
    }

    @RequestMapping("/getAllStudents")
    @ResponseBody
    public List<User> getAllStudents(){
        List<User> students = userRepository.findAllStudents();
        return students;
    }

    @RequestMapping("/gradeDetail/{quizid}")
    public String gradeDetail(@PathVariable String quizid,
                        Model model){
        List<Log> logs = logRepository.findCurrentLogs(quizid);
        Collections.sort(logs, new Comparator<Log>() {
            @Override
            public int compare(Log log, Log t1) {
                return t1.getScore()-log.getScore();
            }
        });
        model.addAttribute("logs",logs);
        return "gradeDetail";
    }
    @RequestMapping("/analysisDetail/{quizid}")
    public String analysisDetail(@PathVariable String quizid,
                           Model model){
        List<Log> logs = logRepository.findCurrentLogs(quizid);
        if (logs.size() == 0){
            return "teacher/noonedoquiz";
        }
        int N = logs.size();            // 考试人数
        int underN = 0;                 // 不及格人数
        double upRate;                  // 及格率
        double max = 0,min = 100;       // 最高分 最低分
        double allSingleChoiceCount = 0;
        double allTrueOrFalseCount = 0;
        double allFillInTheBlankCount = 0;
        double allReadProgramCount = 0;
        double allProgramFillInTheBlankCount = 0;
        double allCorrectMistakeCount = 0;
        double allProgramCount = 0;
        for (Log log: logs){
            if (log.getScore() > max){
                max = log.getScore();
            }
            if (log.getScore() < min){
                min = log.getScore();
            }
            if (log.getScore() < 60){
                underN ++;
            }
            allSingleChoiceCount += log.getSinglechoiceCount();
            allTrueOrFalseCount += log.getTrueorfalseCount();
            allFillInTheBlankCount += log.getFillintheblankCount();
            allReadProgramCount += log.getReadprogramCount();
            allProgramFillInTheBlankCount += log.getProgramfillintheblankCount();
            allCorrectMistakeCount += log.getCorrectmistakeCount();
            allProgramCount += log.getProgramingCount();
        }
        double SingleChoiceRate = allSingleChoiceCount/(N*10);
        double TrueOrFalseRate = allTrueOrFalseCount/(N*10);
        double FillInTheBlankRate = allFillInTheBlankCount/(N*10);
        double ReadProgramRate = allReadProgramCount/(N*20);
        double ProgramFillInTheBlankRate = allProgramFillInTheBlankCount/(N*10);
        double CorrectMistakeRate = allCorrectMistakeCount/(N*10);
        double ProgramRate = allProgramCount/(N*30);

        upRate = (N-underN) / (double)N;

        model.addAttribute("N",N);
        model.addAttribute("max", max);
        model.addAttribute("min", min);
        model.addAttribute("underN", underN);
        model.addAttribute("upRate", upRate*100);

        model.addAttribute("SingleChoiceRate",SingleChoiceRate*100);
        model.addAttribute("TrueOrFalseRate",TrueOrFalseRate*100);
        model.addAttribute("FillInTheBlankRate",FillInTheBlankRate*100);
        model.addAttribute("ReadProgramRate",ReadProgramRate*100);
        model.addAttribute("ProgramFillInTheBlankRate",ProgramFillInTheBlankRate*100);
        model.addAttribute("CorrectMistakeRate",CorrectMistakeRate*100);
        model.addAttribute("ProgramRate",ProgramRate*100);
        return "analysisDetail";
    }


}
