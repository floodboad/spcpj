package com.ocean.controller;

import cc.crrc.core.utils.JsonUtils;
import cc.crrc.core.vo.UserInfo;
import com.ocean.pojo.Product;
import com.ocean.pojo.Tttt;
import com.ocean.pojo.Ttttt;
import com.ocean.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.LinkedHashMap;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

//    @RequestMapping("/products")
//    public Object products() {
//        List<Product> ps = productService.listProducts();
//        return ps;
////        return 3;
//    }

    @RequestMapping("/test")
    public Object test(String groupName,String remoteFileName) {
        System.out.println(groupName);
        System.out.println(remoteFileName);
        return 0;
//        return 3;
    }

    @RequestMapping("/ttttbefore")
    public Object testJson(){
        RestTemplate restTemplate = new RestTemplate();
        Tttt tttt = restTemplate.getForObject("http://192.168.10.48:8002/ttttafter", Tttt.class);
        String aaa = restTemplate.getForObject("http://192.168.10.48:8002/ttttafter", String.class);
        Tttt xxx = JsonUtils.parse(aaa,Tttt.class);
        return "success";
    }

    @RequestMapping("/ttttafter")
    public Object testJsonAfter(){
        Tttt tttt = new Tttt();
        tttt.setName("test");
        Ttttt ttttt = new Ttttt();
        ttttt.setCode(404);
        tttt.setTtttt(ttttt);
        return tttt;
    }

    @RequestMapping("/products")
    public int products(@RequestParam("groupName") String groupName, @RequestParam("remoteGroupName") String remoteGroupName) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        String jarPath = "D:\\tools\\jdk\\jdk1.8\\jre\\lib\\ext\\TestApplication-1.0-SNAPSHOT.jar";
//        loadJar("C:\\Users\\crrcd\\.m2\\repository\\cc\\crrc\\iiot\\iiot-core\\1.0\\iiot-core-1.0.jar");
        List<Product> ps = productService.listProducts();
        System.out.println(groupName);
        System.out.println(remoteGroupName);
        String className = "cc.example.ttt0521.HelloUtil";
        if(isPresent(className)){
            System.out.println("已加载");
        }else{
            System.out.println("未加载");
            loadJar(jarPath);
        }
        System.out.println(1111);
        Class c = Class.forName(className);
        System.out.println(2222);
        UserInfo o = null;
        System.out.println(3333);
        o = (UserInfo)c.newInstance();
        System.out.println(4444);
        o.getTenantId();
        return 3;
//        return 3;
    }

    //    图片上传
//    @PostMapping(value = "/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public Object upload(@RequestPart("file") MultipartFile file){
//        System.out.println(file);
//        return 222;
//    }
//
//    @PostMapping(value = "/upload1",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public Object upload1(MultipartFile file, HttpServletRequest request){
//        System.out.println(request);
//        System.out.println(file);
//        System.out.println(file.getName());
//        return 222;
//    }

    @PostMapping("upload2")
    public Object upload(@RequestPart("file") MultipartFile file,@RequestPart("imageInfo") Product product) {
        System.out.println(product);
//        MultipartFile multipartFile = new MultipartFile() {
//            @Override
//            public String getName() {
//                return null;
//            }
//
//            @Override
//            public String getOriginalFilename() {
//                return null;
//            }
//
//            @Override
//            public String getContentType() {
//                return null;
//            }
//
//            @Override
//            public boolean isEmpty() {
//                return false;
//            }
//
//            @Override
//            public long getSize() {
//                return 0;
//            }
//
//            @Override
//            public byte[] getBytes() throws IOException {
//                return new byte[0];
//            }
//
//            @Override
//            public InputStream getInputStream() throws IOException {
//                return null;
//            }
//
//            @Override
//            public void transferTo(File file) throws IOException, IllegalStateException {
//
//            }
//        };
//        try {
//            BufferedImage bufferedImage = ImageIO.read(multipartFile.getInputStream());
//            bufferedImage.get
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return product;
    }

    //动态加载Jar
    public void loadJar(String jarPath) {
        File jarFile = new File(jarPath);
        //文件存在
        if (jarFile.exists() == false) {
            System.out.println("jar file not found.");
            return;
        }
        //从URLClassLoader类加载器中获取类的addURL方法
        Method method = null;
        try {
            method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
        } catch (NoSuchMethodException | SecurityException e1) {
            e1.printStackTrace();
        }
        // 获取方法的访问权限
        boolean accessible = method.isAccessible();
        try {
            //修改访问权限为可写
            if (accessible == false) {
                method.setAccessible(true);
            }
            System.out.println(ProductController.class.getClassLoader());
            System.out.println(ClassLoader.getSystemClassLoader());
            System.out.println(Thread.currentThread().getContextClassLoader());
            // 获取系统类加载器
            URLClassLoader classLoader = (URLClassLoader) ProductController.class.getClassLoader();
//            URLClassLoader classLoader = (URLClassLoader) Thread.currentThread().getContextClassLoader();
//            保持相同的父加载器，避免重复加载公共类==双亲委派机制
//            URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
            //获取jar文件的url路径
            URL url = jarFile.toURI().toURL();
            //jar路径加入到系统url路径里
            method.invoke(classLoader, url);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            method.setAccessible(accessible);
        }
    }

//    判断类是否已经被加载
    public boolean isPresent(String name) {
        try {
            Thread.currentThread().getContextClassLoader().loadClass(name);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        } catch (NoClassDefFoundError e){
            return false;
        } catch (Exception e){
            return false;
        }
    }
}
