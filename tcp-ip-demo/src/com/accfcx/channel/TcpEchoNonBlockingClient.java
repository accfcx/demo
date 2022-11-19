package com.accfcx.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author accfcx
 * @desc
 * NIO
 * TCP
 * Client
 **/
public class TcpEchoNonBlockingClient {
    public static void main(String[] args) throws IOException {

        SocketChannel clntChan = SocketChannel.open();
        clntChan.configureBlocking(false);

        byte[] array = "hello NIO".getBytes();

        ByteBuffer writeBuff = ByteBuffer.wrap(array);

        System.out.println(writeBuff.order());
        ByteBuffer readBuff = ByteBuffer.allocate(array.length);

        if (!clntChan.connect(new InetSocketAddress("localhost", 9090))) {
            while(!clntChan.finishConnect()) {
                System.out.println("等待连接完成");
            }
        }

        int totalRecv = 0;
        int byteRead = 0;

        while (totalRecv < array.length) {
            if (writeBuff.hasRemaining()) {
                clntChan.write(writeBuff);
            }

            if ((byteRead = clntChan.read(readBuff)) == -1) {
                throw new SocketException("连接过早关闭");
            }

            totalRecv += byteRead;
            System.out.println("发送中");
        }

        System.out.println("接收:"  + new String(readBuff.array(), 0, totalRecv));
        clntChan.close();

    }
}
