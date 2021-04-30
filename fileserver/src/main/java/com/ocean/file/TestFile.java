package com.ocean.file;

import java.io.*;

public class TestFile {
    public static void main(String[] args) {
        File file1 = new File("d:/work");
        System.out.println(file1.getAbsolutePath());
        File file2 = new File("a.txt");
        System.out.println(file2.getAbsolutePath());
        File file3 = new File(file1,"a.txt");
        System.out.println(file3);

//        // 以字符串数组的形式，返回当前文件夹下的所有文件（不包含子文件及子文件夹）
//        f.list();
//
//        // 以文件数组的形式，返回当前文件夹下的所有文件（不包含子文件及子文件夹）
//        File[]fs= f.listFiles();
//
//        // 以字符串形式返回获取所在文件夹
//        f.getParent();
//
//        // 以文件形式返回获取所在文件夹
//        f.getParentFile();
//        // 创建文件夹，如果父文件夹skin不存在，创建就无效
//        f.mkdir();
//
//        // 创建文件夹，如果父文件夹skin不存在，就会创建父文件夹
//        f.mkdirs();
//
//        // 创建一个空文件,如果父文件夹skin不存在，就会抛出异常
//        f.createNewFile();
//        // 所以创建一个空文件之前，通常都会创建父目录
//        f.getParentFile().mkdirs();
//
//        // 列出所有的盘符c: d: e: 等等
//        f.listRoots();
//
//        // 刪除文件
//        f.delete();
//
//        // JVM结束的时候，刪除文件，常用于临时文件的删除
//        f.deleteOnExit();
//        字节流读取文件
        File f = new File("D:\\a.txt");
        // 创建基于文件的输入流
        try {
            FileInputStream fis = new FileInputStream(f);
            System.out.println(fis);
            //创建字节数组，其长度就是文件的长度
            byte[] all =new byte[(int) f.length()];
            //以字节流的形式读取文件所有内容
            fis.read(all);
            for (byte b : all) {
                //打印出来是65 66
                System.out.println(b);
            }

            //每次使用完流，都应该进行关闭
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 通过这个输入流，就可以把数据从硬盘，读取到Java的虚拟机中来，也就是读取到内存中

//        字节流写入文件
        File fin = new File("d:\\lol2.txt");
        // 准备长度是2的字节数组，用88,89初始化，其对应的字符分别是X,Y
        byte data[] = { 88, 89 };
        // 创建基于文件的输出流
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(fin);
            // 把数据写入到输出流
            fos.write(data);
            // 关闭输出流
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("reader===================");
        // 准备文件lol.txt其中的内容是AB
        File freader = new File("d:/lol2.txt");
        // 创建基于文件的Reader
        try {
            FileReader fr = null;
            fr = new FileReader(freader);
            // 创建字符数组，其长度就是文件的长度
            char[] all = new char[(int) f.length()];
            // 以字符流的形式读取文件所有内容
            fr.read(all);
            for (char b : all) {
                // 打印出来是A B
                System.out.println(b);
            }
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("writer==========");
        // 准备文件lol2.txt
        File fwriter = new File("d:/lol2.txt");
        // 创建基于文件的Writer
        try (FileWriter fr = new FileWriter(fwriter)) {
            // 以字符流的形式把数据写入到文件中
            String datawriter="abcdefg1234567890";
            char[] cs = datawriter.toCharArray();
            fr.write(cs);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // 文件拆分
    public static void chaifen(String path, int l) throws IOException {
        int i = 1;
        InputStream ips = new FileInputStream(path);
        byte[] bts = new byte[1024 * 1024 * l];
        int len = 0;
        while ((len = ips.read(bts)) != -1) {
            OutputStream ots = new FileOutputStream(path + "-" + i);
            ots.write(bts, 0, len);
            ots.close();  // 关闭输入流
            i++;
        }
        ips.close(); // 关闭输出流
    }

//    文件合并
    public static void hebing(String path) throws IOException {
        int index = 1;
        File f;
        byte[] data = new byte[1024 * 100];
        int len = 0;
        FileOutputStream fos = new FileOutputStream(path);
        while ((f = new File(path + "-" + index)).exists()) {
            FileInputStream fis = new FileInputStream(f);
            while ((len = fis.read(data)) != -1) {
                fos.write(data, 0, len);
            }
            fis.close();  // 关闭输入流
            index++;
        }
        fos.close();  // 关闭输出流
    }

//    所有的流，无论是输入流还是输出流，使用完毕之后，都应该关闭。 如果不关闭，会产生对资源占用的浪费。 当量比较大的时候，会影响到业务的正常开展。
//    可以在finally中统一关闭
}
