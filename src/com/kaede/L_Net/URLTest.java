package com.kaede.L_Net;

import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author kaede
 * @create 2022-10-14
 */

public class URLTest {

    @Test
    public void test() {
        try {
            URL url = new URL("http://www.okloli.com/");
            show(url);
            URL url1 = new URL(url, "wenzhangshoucang.html");
            show(url1);
            URL url2 = new URL("http", "www.okloli.com", "/wenzhangshoucang.html");
            show(url2);
            URL url3 = new URL("http", "www.okloli.com", 80, "/wenzhangshoucang.html");
            show(url3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void show(URL url) {
        System.out.println("----------------------------");
        System.out.println("url.getProtocol() = " + url.getProtocol());
        System.out.println("url.getHost() = " + url.getHost());
        System.out.println("url.getPort() = " + url.getPort());
        System.out.println("url.getPath() = " + url.getPath());
        System.out.println("url.getFile() = " + url.getFile());
        System.out.println("url.getQuery() = " + url.getQuery());
    }

    @Test
    public void test1() {
        InputStream is = null;
        FileOutputStream fos = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL("http://127.0.0.1:8080/kaede/kq.jpg");
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            is = urlConnection.getInputStream();
            fos = new FileOutputStream("download.jpg");
            byte[] bytes = new byte[1024];
            int len;
            while((len = is.read(bytes)) != -1) {
                fos.write(bytes,0,len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
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
            }
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
    }

}
