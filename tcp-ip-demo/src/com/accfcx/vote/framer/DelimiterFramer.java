package com.accfcx.vote.framer;

import java.io.*;

/**
 * @author accfcx
 * @desc
 *
 * 单字节 delimiter: \n
 *
 **/
public class DelimiterFramer implements Framer{
    InputStream in;
    static final byte DELIIMITER = '\n';


    public DelimiterFramer(InputStream in) {
        this.in = in;
    }

    /**
     * 不支持报文内部包含分隔符. 发送字节数组到 [输出流]
     * @param message
     * @param out
     * @throws IOException
     */
    @Override
    public void frameMsg(byte[] message, OutputStream out) throws IOException {
        for (byte b : message) {
            if (b == DELIIMITER) {
                throw new IOException("消息中包含分隔符");
            }
        }

        out.write(message);
        out.write(DELIIMITER);
        out.flush();
    }

    /**
     * 读取 [输入流]，取出消息
     * @return
     * @throws IOException
     */
    @Override
    public byte[] nextMsg() throws IOException {
        ByteArrayOutputStream messageBuffer = new ByteArrayOutputStream();

        int nextByte;
        // 循环读取字节
        while ((nextByte = in.read()) != DELIIMITER) {
            if (nextByte == -1) {
                if (messageBuffer.size() == 0) {
                    return null;
                } else {
                    throw new EOFException("非空消息缺少分隔符");
                }
            }
            messageBuffer.write(nextByte);
        }

        return messageBuffer.toByteArray();
    }
}
