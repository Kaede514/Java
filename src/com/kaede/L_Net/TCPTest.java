package com.kaede.L_Net;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author kaede
 * @create 2022-10-13
 */

public class TCPTest {

    //客户端
    @Test
    public void client() {
        Socket socket = null;
        try {
            InetAddress inetAddress = InetAddress.getByName("10.40.62.118");
            socket = new Socket(inetAddress, 8888);
            OutputStream os = socket.getOutputStream();
            os.write("hello, I'm client".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //服务端，先启动服务端再启动客户端
    @Test
    public void server() {
        try(ServerSocket serverSocket = new ServerSocket(8888);
            Socket socket = serverSocket.accept();
            ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            InputStream is = socket.getInputStream();
            byte[] bytes = new byte[1024];
            int len;
            while((len = is.read(bytes)) != -1) {
                //不建议这样写，可能会有乱码
                //System.out.print(new String(bytes, 0, len));
                baos.write(bytes,0,len);
            }
            System.out.println(baos.toString());
            System.out.println(socket.getInetAddress().getHostAddress());
            System.out.println(socket.getInetAddress().getHostName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
