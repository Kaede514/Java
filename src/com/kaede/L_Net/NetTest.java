package com.kaede.L_Net;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author kaede
 * @create 2022-10-13
 */

public class NetTest {

    @Test
    public void test() {
        try {
            InetAddress inetAddress = InetAddress.getByName("www.baidu.com");
            System.out.println("inetAddress = " + inetAddress);
            String hostAddress = inetAddress.getHostAddress();
            System.out.println("hostAddress = " + hostAddress);
            String hostName = inetAddress.getHostName();
            System.out.println("hostName = " + hostName);
            boolean reachable = inetAddress.isReachable(3000);
            System.out.println("reachable = " + reachable);
            InetAddress localHost = InetAddress.getLocalHost();
            System.out.println("localHost = " + localHost);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
