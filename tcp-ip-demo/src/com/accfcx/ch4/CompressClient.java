package com.accfcx.ch4;

import java.io.*;
import java.net.Socket;

/**
 * @author accfcx
 * @desc
 **/
public class CompressClient {
    static final int BUFSIZE = 256;

    public static void main(String[] args) throws IOException {
        String server = "localhost";
        int port = 9090;

        String fileName = "input";

        FileInputStream fileInputStream = new FileInputStream(fileName);
        FileOutputStream fileOutputStream = new FileOutputStream(fileName + ".gz");


        Socket socket = new Socket(server, port);

        sendBytes(socket, fileInputStream);

        InputStream inputStream = socket.getInputStream();
        int byteRead;
        byte[] buffer = new byte[BUFSIZE];
        while ((byteRead = inputStream.read(buffer)) != -1) {
            fileOutputStream.write(buffer, 0, byteRead);
            System.out.println("R");
        }

        System.out.println();

        fileOutputStream.flush();

        socket.close();

        fileInputStream.close();
        fileOutputStream.close();

    }

    public static void sendBytes(Socket socket, InputStream inputStream) throws IOException {
        OutputStream outputStream = socket.getOutputStream();

        int byteRead;

        byte[] byteBuffer = new byte[BUFSIZE];
        while ((byteRead = inputStream.read(byteBuffer)) != -1) {
            outputStream.write(byteBuffer, 0, byteRead);

            System.out.println("W");
        }
        socket.shutdownOutput();

    }
}
