package com.accfcx.channel;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author accfcx
 * @desc
 **/
public class EchoSelectorProtocol implements TcpProtocol{

    int buf;

    public EchoSelectorProtocol(int buf) {

        this.buf = buf;
    }

    @Override
    public void handleAccept(SelectionKey key) throws IOException {
        SocketChannel clntChannel = ((ServerSocketChannel) key.channel()).accept();

        clntChannel.configureBlocking(false);

        clntChannel.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(buf));

    }

    @Override
    public void handleRead(SelectionKey key) throws IOException {
        SocketChannel clntChannel = (SocketChannel) key.channel();

        ByteBuffer byteBuffer = (ByteBuffer) key.attachment();

        long bytesRead = clntChannel.read(byteBuffer);

        if (bytesRead == -1) {
            clntChannel.close();
        } else {
            key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        }
    }

    @Override
    public void handleWrite(SelectionKey key) throws IOException {
        ByteBuffer buffer = (ByteBuffer) key.attachment();

        buffer.flip(); // ???

        SocketChannel clntChannel = (SocketChannel) key.channel();

        clntChannel.write(buffer);

        if (!buffer.hasRemaining()) {
            key.interestOps(SelectionKey.OP_READ);
        }

        buffer.compact();
    }
}
