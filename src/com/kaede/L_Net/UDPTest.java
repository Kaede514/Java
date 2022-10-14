package com.kaede.L_Net;

import org.junit.jupiter.api.Test;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author kaede
 * @create 2022-10-14
 */

public class UDPTest {

    @Test
    public void sender() {
        try(DatagramSocket datagramSocket = new DatagramSocket()) {
            byte[] data = "hello".getBytes();
            InetAddress inetAddress = InetAddress.getByName("127.0.0.1");
            DatagramPacket packet = new DatagramPacket(data, 0, data.length, inetAddress, 8888);
            datagramSocket.send(packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void receiver() {
        try(DatagramSocket datagramSocket = new DatagramSocket(8888)) {
            byte[] bytes = new byte[1024];
            DatagramPacket packet = new DatagramPacket(bytes, 0, bytes.length);
            datagramSocket.receive(packet);
            System.out.println(new String(packet.getData(), 0, packet.getLength()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
