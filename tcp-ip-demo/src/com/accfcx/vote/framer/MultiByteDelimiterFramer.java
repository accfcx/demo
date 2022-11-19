package com.accfcx.vote.framer;

import java.io.*;

/**
 * @author accfcx
 * @desc
 *
 * 消息 + 多字节分隔符
 **/
public class MultiByteDelimiterFramer extends DelimiterFramer{

    static final byte DELIMITER_PART1 = '\r';
    static final byte DELIMITER_PART2 = '\n';

    public MultiByteDelimiterFramer(InputStream in) {
        super(in);
    }

    @Override
    public void frameMsg(byte[] message, OutputStream out) throws IOException {
        // 判断是否包含多字节的分隔符
        for (int i = 0; i < message.length; i++) {
            if (message[i] == DELIMITER_PART1) {
                if (i + 1 < message.length && message[i + 1] == DELIMITER_PART2) {
                    throw new IOException("消息中包含分割符");
                }
            }
        }

        out.write(message);
        out.write(DELIMITER_PART1);
        out.write(DELIMITER_PART2);

        out.flush();
    }

    @Override
    public byte[] nextMsg() throws IOException {
        ByteArrayOutputStream messageBuffer = new ByteArrayOutputStream();

        int nextByte;
        int nextNextByte;

        while(true) {
            nextByte = in.read();
            nextNextByte = in.read();
            if ( nextByte == DELIMITER_PART1 &&
                    nextNextByte == DELIMITER_PART2) {
                break;
            }

            if (nextByte == -1) {
                if (messageBuffer.size() == 0) {
                    return null;
                } else {
                    throw new EOFException("非空消息缺少分隔符");
                }

            }
            messageBuffer.write(nextByte);

            if (nextNextByte == -1) {
                throw new EOFException("非空消息缺少分隔符");
            }

            messageBuffer.write(nextNextByte);
        }



        return messageBuffer.toByteArray();
    }
}
