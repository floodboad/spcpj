package com.ocean.controller;

import com.corundumstudio.socketio.SocketIOServer;
import com.ocean.annotation.ResponseBodyResult;
import com.ocean.annotation.groups.EmployeeCheck;
import com.ocean.pojo.Employee;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@ResponseBodyResult
@RestController
public class TestController {

    @PostMapping("/addEmployee")
    @ResponseBody
    public Object addEmployee(@RequestBody @Validated({EmployeeCheck.class}) Employee employee){
//        throw new Exception("ffff");
        int i = 1/0;
        System.out.println(i);
        return "新增成功";
    }
}
