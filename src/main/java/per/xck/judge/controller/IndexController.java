package per.xck.judge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import per.xck.judge.entity.JudgeTask;
import per.xck.judge.entity.User;
import per.xck.judge.repository.UserRepository;
import per.xck.judge.service.JudgeService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    JudgeService judgeService;

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/getCode")
    @ResponseBody
    public String getCode(@RequestParam("code") String code){
        JudgeTask judgeTask = new JudgeTask(1, 1, 1, code);
        String result = judgeService.judge(judgeTask);
        return result;
    }

    /**
     * 登录判断  判断用户是什么身份
     * @param account
     * @param password
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public Object login(@RequestParam("account") String account,
                        @RequestParam("password") String password,
                        HttpSession session){
        List<User> all = userRepository.findAll();
        for (User user : all){
            if (user.getAccount().equals(account)){
                if (user.getPassword().equals(password)){
                    session.setAttribute("user",user);
                    return user;
                }
                return "The password was wrong";
            }
        }
        return "The account does not exist";
    }

    @RequestMapping("/userChangePassword")
    @ResponseBody
    public String userChangePassword(@RequestParam("oldPass") String oldPass,
                                     @RequestParam("newPass") String newPass,
                                     HttpSession session){
        User user = (User) session.getAttribute("user");
        if (!user.getPassword().equals(oldPass)){
            return "原密码有误！";
        }
        user.setPassword(newPass);
        userRepository.save(user);
        return "修改成功";
    }

    @RequestMapping("/islogin")
    @ResponseBody
    public String islogin(HttpSession session){
        if (session.getAttribute("user") == null)
            return "no";
        else{
            User user = (User)session.getAttribute("user");
            return user.getName();
        }
    }
}

