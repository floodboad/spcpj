package com.ocean.pojo;

import com.ocean.annotation.MyValid;
import com.ocean.annotation.groups.AgeCheck;
import com.ocean.annotation.groups.NameCheck;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class Employee {
    @NotBlank(message = "名称不可为空！")
    @Length(message = "名称不能超过{max}个字符！",max = 50,groups = {NameCheck.class})
    @MyValid(groups = {NameCheck.class})
    private String name;
    @NotNull(message = "请输入年龄！")
    @Range(message = "年龄范围为{min}到{max}！",min = 5,max = 50,groups = {AgeCheck.class})
    private int age;
    @Valid
    private List<User> list;
    public List<User> getList() {
        return list;
    }

    public void setList(List<User> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
