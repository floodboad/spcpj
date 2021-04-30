package com.ocean;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable(autowire = Autowire.BY_TYPE,preConstruction = true)
public class Account {
    @Autowired
    Dog dog;

    public Account() {
        System.out.println(dog.getName());
    }

    public void output(){
        System.out.println(dog);
    }
}
