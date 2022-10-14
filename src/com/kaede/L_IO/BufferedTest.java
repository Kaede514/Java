package com.kaede.L_IO;

import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * @author kaede
 * @create 2022-10-13
 */

public class BufferedTest {

    @Test
    public void test() {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            //节点流
            fis = new FileInputStream(new File("kq.jpg"));
            fos = new FileOutputStream(new File("kq2.jpg"));
            //缓冲流
            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);
            //读取和写入
            byte[] bytes = new byte[1024];
            int len;
            while((len = bis.read(bytes)) != -1) {
                bos.write(bytes,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //资源关闭，要先关闭外层流，再关闭内层流，即先关闭缓冲流，再关闭节点流
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //关闭外层流的同时，内存流也会自动关闭，故关于内层流的关闭可以省略
            /*if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }*/
        }
    }

    public static void copyFileWithBuffered(String srcPath, String destPath) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            fis = new FileInputStream(new File(srcPath));
            fos = new FileOutputStream(new File(destPath));
            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);
            byte[] bytes = new byte[1024];
            int len;
            while((len = bis.read(bytes)) != -1) {
                bos.write(bytes,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testSpeed() {
        long startTime = System.currentTimeMillis();
        IOTest.copyFile("D:\\动态壁纸\\朔夜观星.mp4","D:\\动态壁纸\\朔夜观星1.mp4");
        long endTime = System.currentTimeMillis();
        System.out.println("----- cost time: " + (endTime - startTime) + " ms -----");
        long startTime1 = System.currentTimeMillis();
        copyFileWithBuffered("D:\\动态壁纸\\朔夜观星.mp4","D:\\动态壁纸\\朔夜观星2.mp4");
        long endTime1 = System.currentTimeMillis();
        System.out.println("----- cost time: " + (endTime1 - startTime1) + " ms -----");
    }

    @Test
    public void test1() {
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            //br = new BufferedReader(new FileReader(new File("Java.iml")));
            //也可以直接传入文件名，内部会根据文件名新建File对象，然后调用含File的构造器
            br = new BufferedReader(new FileReader("Java.iml"));
            bw = new BufferedWriter(new FileWriter(new File("Java1.iml")));
            //方式一：使用char[]数组
            /*char[] cbuf = new char[1024];
            int len;
            while((len = br.read(cbuf)) != -1) {
                bw.write(cbuf,0,len);
            }*/
            //方式二：使用String
            String data;
            while((data = br.readLine()) != null) {
                //方式一
                //data中不包含换行符
                //bw.write(data + '\n');
                //方式二
                bw.write(data);
                //提供换行的操作
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
