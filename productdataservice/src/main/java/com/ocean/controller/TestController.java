package com.ocean.controller;

import com.corundumstudio.socketio.SocketIOServer;
import com.ocean.annotation.ResponseBodyResult;
import com.ocean.annotation.groups.EmployeeCheck;
import com.ocean.pojo.Employee;
import com.ocean.pojo.G;
import com.ocean.pojo.TopicDTO;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.validation.Valid;
import java.io.*;
import java.util.Properties;

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

    @RequestMapping("/tttt")
    @ResponseBody
    public Object test(){
//        throw new Exception("ffff");
        G g = new G();
        g.setCode(2);
        return g;
    }

    @RequestMapping("/ttt")
    @ResponseBody
    public Object test1() throws InterruptedException {
//        throw new Exception("ffff");
        Thread.sleep(100000000);
        G g = new G();
        g.setCode(2);
        return g;
    }

    @PostMapping("/device/publish")
    public Object test(@RequestBody @Valid TopicDTO topicDTO){
        System.out.println(topicDTO);
        return true;
    }

    @PostMapping("/transfer")
    public Object fbxTransfer(@RequestPart("file") MultipartFile file){
        MultipartFile multipartFile = this.trans(file);
        return true;
    }


    /**
     * 上传文件
     * 转为file并存储在本地
     * 本地进行转换
     * 读取新文件
     * 将文件转为Multifile
     * @return
     */
    private MultipartFile trans(MultipartFile file){
//        上传流读取到新文件中
        File toFile = null;
        String oldFile = "";
        String newFile = "";
        if (file.equals("") || file.getSize() <= 0) {
            file = null;
        } else {
            InputStream ins = null;
            try {
                ins = file.getInputStream();
                oldFile = this.getClass().getResource("/tools").getPath() + "/" + file.getOriginalFilename();//使用userId+时间戳命名
                newFile = this.getClass().getResource("/tools").getPath() + "/" + "new.glb";//使用userId+时间戳命名
                toFile = new File(oldFile);
                inputStreamToFile(ins, toFile);
                ins.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        进行文件格式转化，并存储新文件
        transferModel(oldFile,newFile);
//        读取文件转为multifilePart
        MultipartFile multipartFile = fileToMultipart(newFile);
        return null;
    }

    //获取流文件
    private void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[10240];
            while ((bytesRead = ins.read(buffer, 0, 10240)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void transferModel(String oldPath,String newPath){
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
        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        if(os != null && os.toLowerCase().indexOf("windows") > -1){
            path = path.substring(1);
        }
        String cmd = path + "tools/FBX2glTF.exe -i " + oldPath.substring(1) + " -o " + newPath.substring(1);
        try {
            Process p = Runtime.getRuntime().exec(cmd);
        } catch (Exception e) {
            System.out.println("Error exec!");
        }
    }

    private MultipartFile fileToMultipart(String fileName){
//        先进行本地流文件读取
        File file = new File(fileName);
//        将file文件转为fileItem再重新转为multipartFile
        FileItem fileItem = createFileItem(file);
        MultipartFile multipartFile = new CommonsMultipartFile(fileItem);
        return multipartFile;
    }

    private FileItem createFileItem(File file) {
        FileItemFactory factory = new DiskFileItemFactory(16, null);
        FileItem item = factory.createItem("textField", "text/plain", true, file.getName());
        int bytesRead = 0;
        byte[] buffer = new byte[10240];
        try {
            FileInputStream fis = new FileInputStream(file);
            OutputStream os = item.getOutputStream();
            while ((bytesRead = fis.read(buffer, 0, 10240)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return item;
    }
}
