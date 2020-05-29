package per.xck.judge.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.xck.judge.entity.*;
import com.alibaba.fastjson.JSON;
import per.xck.judge.repository.QuestionRepository;

import java.io.*;
import java.util.Arrays;

@Service
public class JudgeService {
    @Autowired
    QuestionRepository questionRepository;

//    Thread thread = new Thread();

    public String judge(JudgeTask judgeTask){
        String path = ".";
//        File file = new File(path);
//        file.mkdirs();

        try{
            createFile(judgeTask.getCompilerId(), path, judgeTask.getSource());
        }catch (Exception e){
            e.printStackTrace();

            ExecutorUtil.exec("rm -rf " + path);
            return "Create failed";
        }
        // 编译
        String message = compile(judgeTask.getCompilerId(), path);
        if (message != null){
//            ExecutorUtil.exec("rm -rf " + path);
            return "Compile failed";
        }
        //chmod -R 755 path
        ExecutorUtil.exec("chmod -R 755 " + path);
        // 判题
        String process = process(judgeTask.getCompilerId(), path);
        Question question = questionRepository.getOne(judgeTask.getId());
        String str = question.getInput();
        String str2 = question.getInput2();
        String str3 = question.getInput3();
        String[] newArr = str.substring(1,str.length()-1).split(",");
        String[] newArr2 = str2.substring(1,str2.length()-1).split(",");
        String[] newArr3 = str3.substring(1,str3.length()-1).split(",");
        int[] judge_data = new int[newArr.length];
        int[] judge_data2 = new int[newArr2.length];
        int[] judge_data3 = new int[newArr3.length];
        for (int i = 0; i < newArr.length; i++){
            judge_data[i] = Integer.parseInt(newArr[i]);
        }
        for (int i = 0; i < newArr2.length; i++){
            judge_data2[i] = Integer.parseInt(newArr2[i]);
        }
        for (int i = 0; i < newArr3.length; i++){
            judge_data3[i] = Integer.parseInt(newArr3[i]);
        }

//        String cmd = "python " + "judge.py" + " " + process + " " + judge_data + " " + path + " ";
//        String result = null;
//        parseToResult(cmd, result);
//        thread.start();
        String executer = "java Main";
        for (int _ : judge_data) executer += " " + _;
        String executer2 = "java Main";
        for (int _ : judge_data2) executer2 += " " + _;
        String executer3 = "java Main";
        for (int _ : judge_data3) executer3 += " " + _;

//        System.out.println(executer);  java Main 2012
        ExecMessage exec = ExecutorUtil.exec(executer);
        String stdout = exec.getStdout();
        ExecMessage exec2 = ExecutorUtil.exec(executer2);
        String stdout2 = exec2.getStdout();
        ExecMessage exec3 = ExecutorUtil.exec(executer3);
        String stdout3 = exec3.getStdout();


        int count = 0;
        String output = question.getOutput();
        if (output.equals(stdout)) count++;
        String output2 = question.getOutput2();
        if (output2.equals(stdout2)) count++;
        String output3 = question.getOutput3();
        if (output3.equals(stdout3)) count++;

        if (exec.getError() != null){
            ExecutorUtil.exec("rm " + "Main.class");
            ExecutorUtil.exec("rm " + "Main.java");
            return "failed";
        }
        ExecutorUtil.exec("rm " + "Main.class");
        ExecutorUtil.exec("rm " + "Main.java");
        return String.valueOf(count);
    }

    private String parseToResult(String cmd, String result){
        ExecMessage exec = ExecutorUtil.exec(cmd);
        if (exec.getError() != null) {
            result = exec.getError();
            return result;
        } else {
            Stdout out = JSON.parseObject(exec.getStdout(), Stdout.class);
            System.out.println(out);
            return "";
        }
    }

    private String process(int compilerId, String path) {
        switch (compilerId) {
            case 1:
                return path + "/main";
            case 2:
                return path + "/main";
            case 3:
                return "java " + path + "Main.java";
            case 4:
                return "python3 " + path + "main.py";
        }
        return null;
    }

    public void createFile(int compilerId, String path, String source) throws IOException {
        String filename = "";
        switch (compilerId) {
            case 1:
                filename = "main.c";
                break;
            case 2:
                filename = "main.cpp";
                break;
            case 3:
                filename = "Main.java";
                break;
            case 4:
                filename = "main.py";
                break;
        }
        File file = new File(path + "/" + filename);
        file.createNewFile();
        OutputStream output = new FileOutputStream(file);
        PrintWriter writer = new PrintWriter(output);
        writer.print(source);
        writer.close();
        output.close();
    }

    public String compile(int compilerId, String path){
        /**
         *  '1': 'gcc','g++', '3': 'java', '4': 'python',
         */
        String cmd = "";
        switch (compilerId) {
            case 1:
                cmd = "gcc " + path + "/main.c -o " + path + "/main " + "-w -lm";
                break;
            case 2:
                cmd = "g++ " + path + "/main.cpp -o " + path + "/main " + "-w -lm";
                break;
            case 3:
                cmd = "javac " + path + "/Main.java";
                break;
            case 4:
                cmd = "python3 -m py_compile " + path + "/main.py";
                break;
        }
        return ExecutorUtil.exec(cmd).getError();
    }

}
