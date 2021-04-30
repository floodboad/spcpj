package com.ocean;

import com.ocean.TestConfig;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

@Configurable(preConstruction = true,autowire = Autowire.BY_TYPE)
public class TestConfigurable {
    @Autowired
    TestConfig testConfig;

    public TestConfigurable() {
        int a = 12;
        System.out.println("construction!!!=======================");
    }

    public void xx(){
        System.out.println(testConfig.id + "=================");

    }}
