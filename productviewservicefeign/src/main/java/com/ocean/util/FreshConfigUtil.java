package com.ocean.util;

import cn.hutool.http.HttpUtil;

import java.util.HashMap;

public class FreshConfigUtil {
    public static void main(String[] args) {
        HashMap<String,String> headers = new HashMap<>();
        headers.put("Content-Type","application/json;charset=utf-8");
        System.out.println("因为到git获取，刷新config-server，需要几秒完成请耐心等待");

        String result = HttpUtil.createPost("http://localhost:8201/actuator/bus-refresh").addHeaders(headers).execute().body();
        System.out.println("result:" + result);
        System.out.println("refresh complete");
    }
}
