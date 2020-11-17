package com.fan.register.control;

import com.fan.register.model.Student;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/registerController")
public class RegisterController {

    @Resource
    private JdbcTemplate jdbcTemplate;
    private StudentDbUtils dbUtils;

    //注冊的接口
    @RequestMapping(value = "/register", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String register(@RequestBody Student student) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        System.out.println("<------------------注册的接口-------------------------");
        // 直接将json信息打印出来
        System.out.println("请求的参数:" + student.toString());
        //查找是否有此人
        String sql = "select * from student where stu_id = " + student.getStu_id();
        //将查找的人放到集合中

        if (dbUtils == null) {
            dbUtils = new StudentDbUtils();
        }

        List<Student> studentList = dbUtils.select_students(sql, jdbcTemplate);

        if (studentList.size() == 0) {//没有此人
            //防止sql注入
            List<Object> params = new ArrayList<>();
            params.add(student.getStu_id());
            params.add(student.getName());
            params.add(student.getAge());
            params.add(student.getEmail());
            params.add(student.getPassword());
            sql = "insert into student (stu_id,name,age,email,password)values(?,?,?,?,?)";
            int status = jdbcTemplate.update(sql, params.toArray());
            jsonObject.put("status", status);
        } else {//有此人
            System.out.println("有此学号了");
            jsonObject.put("status", 0);
        }
        System.out.println("------------------注册結束了------------------------->");
        return jsonObject.toString();
    }

}


