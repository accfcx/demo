package com.accfcx.vote.framer;

import java.io.*;

/**
 * @author accfcx
 * @desc
 * 消息中的分隔符，转换处理(发送填充，接收移除)
 * 假定：分隔符 = '\n'
 * 前缀填充字节 = '\r'
 **/
public class ByteStuffingDelimiterFramer extends DelimiterFramer  {
    static final byte DELI_PREFIX = '\r';

    public ByteStuffingDelimiterFramer(InputStream in) {
        super(in);
    }

    @Override
    public void frameMsg(byte[] message, OutputStream out) throws IOException {
        // 消息中出现分隔符，进行填充

        // 最大长度=2 * len
        byte[] stuffingMessage = new byte[2 * message.length];
        int i = 0;
        for (byte b : message) {
            if (b == DELIIMITER) {
                System.out.println("添加了填充");
                stuffingMessage[i++] = DELI_PREFIX;

                stuffingMessage[i++] = b;
            } else {
                stuffingMessage[i++] = b;
            }
        }

        out.write(stuffingMessage);
        out.write(DELIIMITER);

        out.flush();
    }

    @Override
    public byte[] nextMsg() throws IOException {
        ByteArrayOutputStream messageBuffer = new ByteArrayOutputStream();
        int nextByte;

        while (true) {
            nextByte = in.read();

            if (nextByte == DELIIMITER) {
                break;
            }

            // 填充字节，继续读取一个字节
            if (nextByte == DELI_PREFIX) {
                System.out.println("移除了填充");
                nextByte = in.read();
            }

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
