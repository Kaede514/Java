package com.kaede.L_IO;

import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * @author kaede
 * @create 2022-10-13
 */

public class InputStreamReaderTest {

    @Test
    public void test() {
        FileInputStream fis = null;
        FileInputStream fis1 = null;
        InputStreamReader isr = null;
        try {
            fis = new FileInputStream("hello.txt");
            fis1 = new FileInputStream("hello.txt");
            //使用系统默认的字符集
            //InputStreamReader isr = new InputStreamReader(fis);
            //使用UTF-8，具体使用哪个字符集取决于文件保存时的字符集
            isr = new InputStreamReader(fis, "UTF-8");
            char[] cbuf = new char[5];
            byte[] bytes = new byte[5];
            int len;
            while((len = fis1.read(bytes)) != -1) {
                System.out.print(new String(bytes, 0, len));
            }
            System.out.println("\n------------------------------");
            while((len = isr.read(cbuf)) != -1) {
                System.out.print(new String(cbuf, 0, len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis1 != null) {
                try {
                    fis1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void test1() {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        InputStreamReader isr = null;
        OutputStreamWriter osw = null;
        try {
            fis = new FileInputStream("hello.txt");
            isr = new InputStreamReader(fis, "UTF-8");
            fos = new FileOutputStream("hello3.txt");
            osw = new OutputStreamWriter(fos, "GBK");
            char[] cbuf = new char[1024];
            int len;
            while((len = isr.read(cbuf)) != -1) {
                osw.write(cbuf, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (osw != null) {
                try {
                    osw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
