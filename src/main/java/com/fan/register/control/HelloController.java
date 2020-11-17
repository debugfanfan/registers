package com.fan.register.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;

@Controller
@RequestMapping("/jdbc")
public class HelloController {


    @RequestMapping(value = "/hello")
    @ResponseBody
    public String hello() {
        System.out.println("hello，你好啊");
        return "hello";
    }
}
