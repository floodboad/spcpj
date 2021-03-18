package com.ocean.test;

import cc.crrc.core.utils.JsonUtils;

public class Test01 {
    public static void main(String[] args) {
        String msg =  "{\"key\":\"12\",\"value\":22}";
        TestPojo testPojo = JsonUtils.parse(msg,TestPojo.class);
        System.out.println(testPojo);
    }
}
