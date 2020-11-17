package com.fan.register.control;

import com.fan.register.model.Student;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/loginController")
public class LoginController {

    @Resource
    private JdbcTemplate jdbcTemplate;
    private StudentDbUtils dbUtils;

    @RequestMapping(value = "/login", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String login(@RequestBody Student student) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        System.out.println("<------------------登录的接口-------------------------");
        // 直接将json信息打印出来
        System.out.println("请求的参数:" + student.toString());
        //将查找的人放到集合中
        String sql = "select * from student where stu_id = " + student.getStu_id();
        if (dbUtils == null) {
            dbUtils = new StudentDbUtils();
        }
        List<Student> studentList = dbUtils.select_students(sql, jdbcTemplate);

        if (studentList.size() == 0) {
            System.out.println("没有此人，登录失败");
            jsonObject.put("status", 0);

        } else {
            for (int i = 0; i < studentList.size(); i++) {
                Student s = (Student) studentList.get(i);
                System.out.println(s.toString());
                System.out.println(s.getPassword());
                System.out.println(student.getPassword());

                if (s.getPassword().equals(student.getPassword())) {
                    System.out.println("有此人，登录成功");
                    jsonObject.put("status", 1);

                } else {
                    System.out.println("密码错误");
                    jsonObject.put("status", -1);
                }
            }
        }

        System.out.println("------------------登录結束了------------------------->");
        return jsonObject.toString();
    }
}
