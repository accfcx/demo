package com.accfcx.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;

/**
 * @author accfcx
 * @desc
 **/
public class UpdServer {
    public static void main(String[] args) throws IOException {
        DatagramSocket serverSocket = new DatagramSocket(8090);

        DatagramPacket packet = new DatagramPacket(new byte[255], 255);

        byte[] bytes = new byte[65599];
        for (int i = 0; i < bytes.length; i++)  {
            bytes[i] = (byte) i;
        }


        while (true) {
            packet.setData(bytes, 0, bytes.length);
            System.out.println(packet.getOffset());
            System.out.println(packet.getLength());
            // 阻塞
            serverSocket.receive(packet);

//            serverSocket.send(packet);

            System.out.println(packet.getOffset());
            System.out.println(packet.getLength());

            byte[] dest = Arrays.copyOfRange(packet.getData(),
                    packet.getOffset(),
                    packet.getOffset() + packet.getLength());

            for (int i = 0; i < dest.length; i++) {
                System.out.println(dest[i]);
            }

//            serverSocket.send(packet);
//            packet.setLength(255);

        }
    }
}
