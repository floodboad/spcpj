package com.ocean.tools;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.http.HttpUtil;

public class AccessViewService {
    public static void main(String[] args) {
        while (true){
            ThreadUtil.sleep(1000);
            access(8201);
            access(8202);
        }
    }

    public static void access(int port){
        try {
            String html = HttpUtil.get(String.format("http://127.0.0.1:%d",port));
            System.out.printf("%d 视图服务访问成功，返回大小%d%n",port,html.length());
        }catch (Exception e){
            System.err.printf("%d 视图服务无法访问%n",port);
        }
    }
}
