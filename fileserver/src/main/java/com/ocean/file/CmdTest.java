package com.ocean.file;

import org.springframework.util.ResourceUtils;

import java.util.Properties;

public class CmdTest {
    public static void main(String[] args) {
//        判断系统
//        根据不同系统区分执行的文件
//        todo 本地先用docker测试
        Properties prop = System.getProperties();
        String os = prop.getProperty("os.name");
        System.out.println(os);
        if (os != null && os.toLowerCase().indexOf("windows") > -1) {
            System.out.println("windows");
        } else {
            System.out.println("linux");
        }
//        todo 不同系统路径问题
//        todo 先将fbx存到本地，然后转为glb，然后再转为multifile做微服务调用
//      exe文件执行
//        String cmd = "cmd /c /D:/fbx2gltf -i /D:/a.fbx -o /D:/a.glb";
        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        if(os != null && os.toLowerCase().indexOf("windows") > -1){
            path = path.substring(1);
        }
        String cmd = path + "tools/FBX2glTF.exe -i " + path + "tools/a.fbx -o " + path + "tools/a.glb";
        try {
            Process p = Runtime.getRuntime().exec(cmd);
        } catch (Exception e) {
            System.out.println("Error exec!");
        }
    }
}
