package com.ocean.pojo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class Employee {
    @NotBlank(message = "名称不可为空！")
    @Length(message = "名称不能超过{max}个字符！",max = 5)
    private String name;
    @NotNull(message = "请输入年龄！")
    @Range(message = "年龄范围为{min}到{max}！",min = 5,max = 50)
    private int age;
    @NotNull
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
