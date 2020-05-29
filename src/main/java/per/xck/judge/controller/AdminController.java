package per.xck.judge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import per.xck.judge.entity.Question;
import per.xck.judge.entity.User;
import per.xck.judge.repository.QuestionRepository;
import per.xck.judge.repository.UserRepository;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    QuestionRepository questionRepository;

    /**
     * 首页       选择题页面
     * @param model
     * @return
     */
    @RequestMapping("/success/admin")
    public String successAdmin(Model model){
        List<Question> allSingleChoice = questionRepository.findAllSingleChoice();
        model.addAttribute("allSingleChoice",allSingleChoice);
        return "admin/index";
    }

    /**
     * 判断题页面
     * @param model
     * @return
     */
    @RequestMapping("/questionManageTrueOrFalse")
    private String questionManageTrueOrFalse(Model model){
        List<Question> allTrueOrFalse = questionRepository.findAllTrueOrFalse();
        model.addAttribute("allTrueOrFalse",allTrueOrFalse);
        return "admin/questionManageTrueOrFalse";
    }

    /**
     * 填空题页面
     * @param model
     * @return
     */
    @RequestMapping("/questionManageFillInTheBlank")
    private String questionManageFillInTheBlank(Model model){
        List<Question> allFillInTheBlank = questionRepository.findAllFillInTheBlank();
        model.addAttribute("allFillInTheBlank",allFillInTheBlank);
        return "admin/questionManageFillInTheBlank";
    }
    /**
     * 程序阅读题页面
     * @param model
     * @return
     */
    @RequestMapping("/questionManageReadProgram")
    private String questionManageReadProgram(Model model){
        List<Question> allReadProgram = questionRepository.findAllReadProgram();
        model.addAttribute("allReadProgram",allReadProgram);
        return "admin/questionManageReadProgram";
    }
    /**
     * 程序填空题页面
     * @param model
     * @return
     */
    @RequestMapping("/questionManageProgramFillInTheBlank")
    private String questionManageProgramFillInTheBlank(Model model){
        List<Question> allProgramFillInTheBlank = questionRepository.findAllProgramFillInTheBlank();
        model.addAttribute("allProgramFillInTheBlank",allProgramFillInTheBlank);
        return "admin/questionManageProgramFillInTheBlank";
    }

    /**
     * 程序改错题页面
     * @param model
     * @return
     */
    @RequestMapping("/questionManageCorrectMistake")
    private String questionManageCorrectMistake(Model model){
        List<Question> allCorrectMistake = questionRepository.findAllCorrectMistake();
        model.addAttribute("allCorrectMistake",allCorrectMistake);
        return "admin/questionManageCorrectMistake";
    }
    /**
     * 程序编写页面
     * @param model
     * @return
     */
    @RequestMapping("/questionManagePrograming")
    private String questionManagePrograming(Model model){
        List<Question> allPrograming = questionRepository.findAllPrograming();
        model.addAttribute("allPrograming",allPrograming);
        return "admin/questionManagePrograming";
    }

    /**
     * 根据id删除题目
     * @param id
     */
    @RequestMapping("/deleteQuestionById")
    @ResponseBody
    public void deleteQuestionById(@RequestParam("id") String id){
        questionRepository.deleteById(Integer.parseInt(id));
    }

    /**
     * 添加单选题
     * @param neirong
     * @param daan
     * @param Aanswer
     * @param Banswer
     * @param Canswer
     * @param Danswer
     */
    @RequestMapping("/addSingleChoice")
    @ResponseBody
    public void addSingleChoice(@RequestParam("neirong") String neirong,
                                @RequestParam("daan") String daan,
                                @RequestParam("Aanswer") String Aanswer,
                                @RequestParam("Banswer") String Banswer,
                                @RequestParam("Canswer") String Canswer,
                                @RequestParam("Danswer") String Danswer
    ){
        Question question = new Question();
        question.setContent(neirong.replace("\n","<br />"));
        question.setAnswer(daan);
        question.setChoice_a(Aanswer);
        question.setChoice_b(Banswer);
        question.setChoice_c(Canswer);
        question.setChoice_d(Danswer);
        question.setType("Singel Choice");
        questionRepository.save(question);
    }

    /**
     * 修改单选题
     * @param id
     * @param neirong
     * @param daan
     * @param Aanswer
     * @param Banswer
     * @param Canswer
     * @param Danswer
     * @return
     */
    @RequestMapping("/modifySingleChoice")
    @ResponseBody
    public String modifySingleChoice(@RequestParam("id") String id,
                                @RequestParam("neirong") String neirong,
                                @RequestParam("daan") String daan,
                                @RequestParam("Aanswer") String Aanswer,
                                @RequestParam("Banswer") String Banswer,
                                @RequestParam("Canswer") String Canswer,
                                @RequestParam("Danswer") String Danswer
    ){
        Question question = questionRepository.getOne(Integer.parseInt(id));
        question.setContent(neirong.replace("\n","<br />"));
        question.setAnswer(daan);
        question.setChoice_a(Aanswer);
        question.setChoice_b(Banswer);
        question.setChoice_c(Canswer);
        question.setChoice_d(Danswer);
        questionRepository.save(question);
        return "修改成功";
    }

    /**
     * 添加判断题
     * @param neirong
     * @param daan
     */
    @RequestMapping("/addTrueOrFalse")
    @ResponseBody
    public void addTrueOrFalse(@RequestParam("neirong") String neirong,
                               @RequestParam("daan") String daan){
        Question question = new Question();
        question.setContent(neirong.replace("\n","<br />"));
        question.setAnswer(daan);
        question.setType("True Or False");
        questionRepository.save(question);
    }

    /**
     * 修改判断题
     * @param id
     * @param neirong
     * @param daan
     * @return
     */
    @RequestMapping("/modifyTrueOrFalse")
    @ResponseBody
    public String modifyTrueOrFalse(@RequestParam("id") String id,
                               @RequestParam("neirong") String neirong,
                               @RequestParam("daan") String daan){
        Question question = questionRepository.getOne(Integer.parseInt(id));
        question.setContent(neirong.replace("\n","<br />"));
        question.setAnswer(daan);
        questionRepository.save(question);
        return "修改成功";
    }

    /**
     * 添加填空题
     * @param neirong
     * @param daan
     */
    @RequestMapping("/addFillInTheBlank")
    @ResponseBody
    public void addFillInTheBlank(@RequestParam("neirong") String neirong,
                               @RequestParam("daan") String daan){
        Question question = new Question();
        question.setContent(neirong.replace("\n","<br />"));
        question.setAnswer(daan);
        question.setType("Fill In The Blank");
        questionRepository.save(question);
    }

    /**
     * 修改填空题
     * @param id
     * @param neirong
     * @param daan
     */
    @RequestMapping("/modifyFillInTheBlank")
    @ResponseBody
    public void modifyFillInTheBlank(@RequestParam("id") String id,
                               @RequestParam("neirong") String neirong,
                               @RequestParam("daan") String daan){
        Question question = questionRepository.getOne(Integer.parseInt(id));
        question.setContent(neirong.replace("\n","<br />"));
        question.setAnswer(daan);
        questionRepository.save(question);
    }
    /**
     * 添加程序阅读题
     * @param neirong
     * @param daan
     */
    @RequestMapping("/addReadProgram")
    @ResponseBody
    public void addReadProgram(@RequestParam("neirong") String neirong,
                               @RequestParam("daan") String daan){
        Question question = new Question();
        question.setContent(neirong.replace("\n","<br />"));
        question.setAnswer(daan);
        question.setType("Read Program");
        questionRepository.save(question);
    }

    /**
     * 修改程序阅读题
     * @param id
     * @param neirong
     * @param daan
     */
    @RequestMapping("/modifyReadProgram")
    @ResponseBody
    public void modifyReadProgram(@RequestParam("id") String id,
                               @RequestParam("neirong") String neirong,
                               @RequestParam("daan") String daan){
        Question question = questionRepository.getOne(Integer.parseInt(id));
        question.setContent(neirong.replace("\n","<br />"));
        question.setAnswer(daan);
        questionRepository.save(question);
    }
    /**
     * 添加程序填空题
     * @param neirong
     * @param daan
     */
    @RequestMapping("/addProgramFillInTheBlank")
    @ResponseBody
    public void addProgramFillInTheBlank(@RequestParam("neirong") String neirong,
                               @RequestParam("daan") String daan){
        Question question = new Question();
        question.setContent(neirong.replace("\n","<br />"));
        question.setAnswer(daan);
        question.setType("Program Fill In The Blank");
        questionRepository.save(question);
    }

    /**
     * 修改程序填空题
     * @param id
     * @param neirong
     * @param daan
     * @return
     */
    @RequestMapping("/modifyProgramFillInTheBlank")
    @ResponseBody
    public String modifyProgramFillInTheBlank(@RequestParam("id") String id,
                                              @RequestParam("neirong") String neirong,
                                              @RequestParam("daan") String daan){
        Question question = questionRepository.getOne(Integer.parseInt(id));
        question.setContent(neirong.replace("\n","<br />"));
        question.setAnswer(daan);
        questionRepository.save(question);
        return "修改成功";
    }
    /**
     * 添加程序改错题
     * @param neirong
     * @param daan
     * @param input1
     * @param input2
     * @param input3
     * @param output1
     * @param output2
     * @param output3
     * @return
     */
    @RequestMapping("/addCorrectMistake")
    @ResponseBody
    public String addCorrectMistake(@RequestParam("neirong") String neirong,
                                    @RequestParam("daan") String daan,
                                    @RequestParam("input1") String input1,
                                    @RequestParam("input2") String input2,
                                    @RequestParam("input3") String input3,
                                    @RequestParam("output1") String output1,
                                    @RequestParam("output2") String output2,
                                    @RequestParam("output3") String output3){
        Question question = new Question();
        question.setContent(neirong.replace("\n","<br />"));
        question.setAnswer(daan.replace("\n","<br />"));
        question.setInput(input1);question.setOutput(output1);
        question.setInput2(input2);question.setOutput2(output2);
        question.setInput3(input3);question.setOutput3(output3);
        question.setType("Correct Mistake");
        questionRepository.save(question);
        return "添加成功！";
    }

    /**
     * 修改程序改错题
     * @param id
     * @param neirong
     * @param daan
     * @param input1
     * @param input2
     * @param input3
     * @param output1
     * @param output2
     * @param output3
     * @return
     */
    @RequestMapping("/modifyCorrectMistake")
    @ResponseBody
    public String modifyCorrectMistake(@RequestParam("id") String id,
                                       @RequestParam("neirong") String neirong,
                                    @RequestParam("daan") String daan,
                                    @RequestParam("input1") String input1,
                                    @RequestParam("input2") String input2,
                                    @RequestParam("input3") String input3,
                                    @RequestParam("output1") String output1,
                                    @RequestParam("output2") String output2,
                                    @RequestParam("output3") String output3){
        Question question = questionRepository.getOne(Integer.parseInt(id));
        question.setContent(neirong.replace("\n","<br />"));
        question.setAnswer(daan.replace("\n","<br />"));
        question.setInput(input1);question.setOutput(output1);
        question.setInput2(input2);question.setOutput2(output2);
        question.setInput3(input3);question.setOutput3(output3);
        questionRepository.save(question);
        return "修改成功！";
    }
    /**
     * 添加编程题
     * @param neirong
     * @param imgAdd
     * @param input1
     * @param input2
     * @param input3
     * @param output1
     * @param output2
     * @param output3
     */
    @RequestMapping("/addPrograming")
    @ResponseBody
    public void addPrograming(@RequestParam("neirong") String neirong,
                              @RequestParam("imgAdd") String imgAdd,
                              @RequestParam("input1") String input1,
                              @RequestParam("input2") String input2,
                              @RequestParam("input3") String input3,
                              @RequestParam("output1") String output1,
                              @RequestParam("output2") String output2,
                              @RequestParam("output3") String output3
                              ){
        Question question = new Question();
        question.setContent(neirong.replace("\n","<br />"));
        question.setQuestionImg(imgAdd);
        question.setInput(input1);question.setOutput(output1);
        question.setInput2(input2);question.setOutput2(output2);
        question.setInput3(input3);question.setOutput3(output3);
        question.setType("Programing");
        questionRepository.save(question);
    }
    @RequestMapping("/modifyPrograming")
    @ResponseBody
    public void modifyPrograming(@RequestParam("id") String id,
                                 @RequestParam("neirong") String neirong,
                              @RequestParam("imgAdd") String imgAdd,
                              @RequestParam("input1") String input1,
                              @RequestParam("input2") String input2,
                              @RequestParam("input3") String input3,
                              @RequestParam("output1") String output1,
                              @RequestParam("output2") String output2,
                              @RequestParam("output3") String output3
                              ){
        Question question = questionRepository.getOne(Integer.parseInt(id));
        question.setContent(neirong.replace("\n","<br />"));
        question.setQuestionImg(imgAdd);
        question.setInput(input1);question.setOutput(output1);
        question.setInput2(input2);question.setOutput2(output2);
        question.setInput3(input3);question.setOutput3(output3);
        questionRepository.save(question);
    }
    /**
     * 教师管理页面
     * @param model
     * @return
     */
    @RequestMapping("/teacherManage")
    public String teacherManage(Model model){
        List<User> allTeachers = userRepository.findAllTeachers();
        model.addAttribute("allTeachers", allTeachers);
        return "admin/teacherManage";
    }

    /**
     * 添加老师
     * @param account
     * @param name
     * @return
     */
    @RequestMapping("/addTeacher")
    @ResponseBody
    public String addTeacher(@RequestParam("account") String account,
                             @RequestParam("name") String name){
        User user = new User();
        user.setType("teacher");
        user.setName(name);
        user.setGrade("1000");
        user.setAccount(account);
        user.setPassword(account);
        userRepository.save(user);
        return "添加老师成功！";
    }

    /**
     * 修改老师信息
     * @param id
     * @param account
     * @param name
     * @return
     */
    @RequestMapping("modifyTeacher")
    @ResponseBody
    public String modifyTeacher(@RequestParam("id") String id,
                                @RequestParam("account") String account,
                                @RequestParam("name") String name
    ){
      User user = userRepository.getOne(Integer.parseInt(id));
      user.setAccount(account);
      user.setName(name);
      userRepository.save(user);
        return "修改老师信息成功！";
    }
    /**
     * 学生管理页面
     * @param model
     * @return
     */
    @RequestMapping("/studentManage")
    public String studentManage(Model model){
        List<User> allStudents = userRepository.findAllStudents();
        model.addAttribute("allStudents", allStudents);
        return "admin/studentManage";
    }

    /**
     * 添加学生
     * @param account
     * @param grade
     * @param className
     * @param name
     * @return
     */
    @RequestMapping("/addStudent")
    @ResponseBody
    public String addStudent(@RequestParam("account") String account,
                             @RequestParam("grade") String grade,
                             @RequestParam("className") String className,
                             @RequestParam("name") String name
                             ){
        User user = new User();
        user.setAccount(account);
        user.setType("student");
        user.setPassword(account);
        user.setGrade(grade);
        user.setClassName(className);
        user.setName(name);
        userRepository.save(user);
        return "添加学生成功！";
    }

    /**
     * 修改学生信息
     * @param id
     * @param account
     * @param grade
     * @param className
     * @param name
     * @return
     */
    @RequestMapping("/modifyStudent")
    @ResponseBody
    public String modifyStudent(@RequestParam("id") String id,
                             @RequestParam("account") String account,
                             @RequestParam("grade") String grade,
                             @RequestParam("className") String className,
                             @RequestParam("name") String name
                             ){
        User user = userRepository.getOne(Integer.parseInt(id));
        user.setAccount(account);
        user.setGrade(grade);
        user.setClassName(className);
        user.setName(name);
        userRepository.save(user);
        return "修改学生信息成功！";
    }
    
    /**
     * 根据id删除学生或老师
     * @param id
     */
    @RequestMapping("/deleteUserById")
    @ResponseBody
    public void deleteUserById(@RequestParam("id") String id){
        userRepository.deleteById(Integer.parseInt(id));
    }
}

