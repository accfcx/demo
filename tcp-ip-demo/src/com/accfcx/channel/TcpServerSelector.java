package com.accfcx.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

/**
 * @author accfcx
 * @desc
 **/
public class TcpServerSelector {
    static final int BUFSIZE = 1024;
    static final int TIMEOUT = 3000;


    public static void main(String[] args) throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        ByteBuffer buffer = ByteBuffer.allocate(BUFSIZE);


        Selector selector = Selector.open();

        serverSocketChannel.socket().bind(new InetSocketAddress(9090));
        serverSocketChannel.configureBlocking(false);

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);



        TcpProtocol protocol = new EchoSelectorProtocol(BUFSIZE);

        while (true) {
            if (selector.select(TIMEOUT) == 0) {
                System.out.println("没有通道准备好或者超时了");
                continue;
            }

            Iterator<SelectionKey> keyIter = selector.selectedKeys().iterator();
            while (keyIter.hasNext()) {
                SelectionKey key = keyIter.next();

                if (key.isAcceptable()) {
                    protocol.handleAccept(key);
                }

                if (key.isReadable()) {
                    protocol.handleRead(key);
                }

                if (key.isValid() && key.isWritable()) {
                    protocol.handleWrite(key);
                }


                keyIter.remove();
            }

        }

    }
}
