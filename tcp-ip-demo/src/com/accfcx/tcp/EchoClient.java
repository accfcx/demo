package com.accfcx.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

/**
 * @author accfcx
 * @desc
 **/
public class EchoClient {
    public static void main(String[] args) throws IOException {
        String data = "Hello, TCP";

        String server = "127.0.0.1";
        int port = 20881;

//        byte[] datas = data.getBytes();
        byte[] datas = new byte[8];
        byte[] dest = new byte[8];
        for (int i = 0; i < datas.length; i++) {
            datas[i] = (byte) (20+i);
        }
        Socket socket = new Socket(server, port);
//        Socket socket2 = new Socket();

        System.out.println("server connected");


        InputStream inputStream = socket.getInputStream();

        OutputStream outputStream = socket.getOutputStream();

        outputStream.write(datas);


        int totalBytesRecv = 0;

        int bytesRecv;

//        while (totalBytesRecv < datas.length ) {
//
//            // Read 阻塞
//            if ((bytesRecv = inputStream.read(datas, totalBytesRecv, datas.length - totalBytesRecv)) == -1) {
//                throw new SocketException("connection closed prematurely");
//            }
//
//            totalBytesRecv += bytesRecv;
//        }

        while(totalBytesRecv < dest.length) {
            if ((bytesRecv = inputStream.read(dest, totalBytesRecv, dest.length - totalBytesRecv)) == -1) {
                throw new SocketException("connection closed prematurely");
            }
            System.out.println("接收到" + bytesRecv);
            System.out.println("数据:" + dest[totalBytesRecv]);
            totalBytesRecv += bytesRecv;
        }


        for (int i =0; i < dest.length; i++) {
            System.out.println(dest[i]);
        }
        System.out.println("接收到数据: " + new String(dest));

        socket.close();
    }
}
