package com.ocean.file;

import java.io.*;

public class FileSaveAs {
    public static void main(String[] args) {
        copyFile("d:/a.fbx","d:/a.glb");
    }

    public static void copyFile(String strOldpath,String strNewPath)
    {
        try {
            File fOldFile = new File(strOldpath);
            if (fOldFile.exists())
            {
                int bytesum = 0;
                int byteread = 0;
                InputStream inputStream = new FileInputStream(fOldFile);
                FileOutputStream fileOutputStream = new FileOutputStream(strNewPath);
                byte[] buffer = new byte[1444];
                while ( (byteread = inputStream.read(buffer)) != -1)
                {
                    bytesum += byteread; //这一行是记录文件大小的，可以删去
                    fileOutputStream.write(buffer, 0, byteread);//三个参数，第一个参数是写的内容，
                    //第二个参数是从什么地方开始写，第三个参数是需要写的大小
                }
                inputStream.close();
                fileOutputStream.close();
            }
        }catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("复制单个文件出错");
            e.printStackTrace();
        }
    }
}
