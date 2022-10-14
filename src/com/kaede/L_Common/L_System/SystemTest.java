package com.kaede.L_Common.L_System;

import org.junit.jupiter.api.Test;

/**
 * @author kaede
 * @create 2022-10-14
 */

public class SystemTest {

    @Test
    public void test() {
        long timeMillis = System.currentTimeMillis();
        System.out.println("timeMillis = " + timeMillis);
        System.gc();
        String javaVersion = System.getProperty("java.version");
        System.out.println("javaVersion = " + javaVersion);
        String javaHome = System.getProperty("java.home");
        System.out.println("javaHome = " + javaHome);
        String osName = System.getProperty("os.name");
        System.out.println("osName = " + osName);
        String osVersion = System.getProperty("os.version");
        System.out.println("osVersion = " + osVersion);
        String userName = System.getProperty("user.name");
        System.out.println("userName = " + userName);
        String userHome = System.getProperty("user.home");
        System.out.println("userHome = " + userHome);
        String userDir = System.getProperty("user.dir");
        System.out.println("userDir = " + userDir);
        try {
            System.exit(0);
        } finally {
            System.out.println("exit...");
        }
        System.out.println("exit...");
    }

}
