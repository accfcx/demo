package com.accfcx.vote.framer;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author accfcx
 * @desc
 **/
public class LengthFramer implements Framer{
    static final int MAXLEN = 65535;
    static final int BYTEMASK = 0xff;
    static final int SHORTMASK = 0xffff;
    static final int BYTESHIFT = 8;

    DataInputStream in;

    public LengthFramer(InputStream in) {
        this.in = new DataInputStream(in);
    }

    @Override
    public void frameMsg(byte[] message, OutputStream out) throws IOException {
        if (message.length > MAXLEN) {
            throw new IOException("too loog");
        }

        // 最大长度 65535 [11111111 11111111] 需要2个字节存储
        out.write((message.length >> BYTESHIFT) & BYTEMASK );
        out.write(message.length & BYTEMASK);

        out.write(message);

        out.flush();
    }

    @Override
    public byte[] nextMsg() throws IOException {
        int len;
        try{
            // 大端 ，读取无符号 short
            len = in.readUnsignedShort();
        } catch (Exception e) {
            return null;
        }

        byte[] msg = new byte[len];
        in.readFully(msg);
        return msg;
    }
}
