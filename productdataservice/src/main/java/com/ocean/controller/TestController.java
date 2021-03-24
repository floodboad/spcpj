package com.ocean.controller;

import com.corundumstudio.socketio.SocketIOServer;
import com.ocean.pojo.Employee;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class TestController {

    @GetMapping("/addEmployee")
    @ResponseBody
    public Object addEmployee(@Valid Employee employee){
//        throw new Exception("ffff");
        int i = 1/0;
        System.out.println(i);
        return "新增成功";
    }
}
