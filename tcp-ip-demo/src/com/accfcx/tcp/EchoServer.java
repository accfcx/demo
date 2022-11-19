package com.accfcx.tcp;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * @author accfcx
 * @desc
 **/
public class EchoServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        int port = 20881;

        ServerSocket serverSocket = new ServerSocket(port);

        int recvSize;

        byte[] recvBuf = new byte[32];

        while (true) {

            // 阻塞
            Socket socket = serverSocket.accept();

            SocketAddress clientAddress = socket.getRemoteSocketAddress();
            System.out.println("client address: "+  clientAddress);

            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            while ((recvSize = inputStream.read(recvBuf)) != -1) {

//                Thread.sleep(100 * 1000);
                for (int i = 0; i < recvBuf.length; i++) {
                    System.out.println(recvBuf[i]);
                }

//                for (int i = 0; i < recvSize; i++) {
//                    Thread.sleep(1000);
//                    recvBuf[i] += 10;
//                    System.out.println("睡眠1s发送:" + (recvBuf[i]));
//                    outputStream.write(recvBuf, i, 1);
//                }

                for (int i = 0; i < 1; i++) {
                    Thread.sleep(1000);
                    recvBuf[i] += 10;
                    System.out.println("睡眠1s发送:" + (recvBuf[i]));
                    outputStream.write(recvBuf, i, 1);

                    socket.close();
                }
//                outputStream.write(recvBuf, 0, recvSize);
            }

            socket.close();
        }
    }
}
