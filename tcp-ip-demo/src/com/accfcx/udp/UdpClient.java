package com.accfcx.udp;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author accfcx
 * @desc
 **/
public class UdpClient {
    public static void main(String[] args) throws Exception{

        InetAddress serverAddr = InetAddress.getByName("localhost");

//        byte[] bytes = "UDP test".getBytes();
        byte[] bytes = new byte[65508];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (20+i);
        }

        int port = 8090;

        DatagramSocket socket = new DatagramSocket();

        socket.setSoTimeout(5*1000);

        DatagramPacket packet = new DatagramPacket(bytes, bytes.length, serverAddr, port);

        DatagramPacket result = new DatagramPacket(new byte[bytes.length], bytes.length);


        int tries = 0;
        boolean recvResp = false;

        while (!recvResp && tries < 5) {
            socket.send(packet);

            try{
                // 阻塞
                socket.receive(result);

                if (!result.getAddress().equals(serverAddr)) {
                    throw new IOException("unknown address: " + result.getAddress());
                }

                System.out.println("client 接收到" + new String(result.getData()));
            } catch (InterruptedIOException e) {
                tries ++;
                System.out.println("Timeout: try " + (tries) + " times");
            }
        }

        if (recvResp) {
            System.out.println("接收数据:" + new String(result.getData()));
        } else {
            System.out.println("无数据:");
        }

        socket.close();
    }
}
