package com.kaede.L_Collection;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author kaede
 * @create 2022-10-12
 */

public class PropertiesTest {

    @Test
    public void test() {
        FileInputStream fis = null;
        try {
            Properties pros = new Properties();
            fis = new FileInputStream("src/com/kaede/L_Collection/jdbc.properties");
            //加载流对应的文件
            pros.load(fis);
            String name = pros.getProperty("name");
            String password = pros.getProperty("password");
            String age = pros.getProperty("age");
            System.out.println("name = " + name);
            System.out.println("password = " + password);
            System.out.println("age = " + age);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
