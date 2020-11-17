package com.fan.register.control;

import com.fan.register.model.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDbUtils {

    public List<Student> select_students(String sql, JdbcTemplate jdbcTemplate) {

        //查找是否有此人
        //将查找的人放到集合中
        List<Student> studentList = jdbcTemplate.query(sql, new RowMapper<Student>() {
            Student student = null;

            @Override
            public Student mapRow(ResultSet resultSet, int i) throws SQLException {

                student = new Student();
                student.setStu_id(resultSet.getInt("stu_id"));
                student.setName(resultSet.getString("name"));
                student.setAge(resultSet.getInt("age"));
                student.setEmail(resultSet.getString("email"));
                student.setPassword(resultSet.getString("password"));
                return student;
            }
        });
        return studentList;
    }
}
