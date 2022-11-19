package com.accfcx.vote.framer;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author accfcx
 * @desc
 **/
public interface Framer {

    /**
     * 添加Framing消息，并输出消息到给定的Stream
     * @param message
     * @param out
     * @throws IOException
     */
    void frameMsg(byte[] message, OutputStream out) throws IOException;

    /**
     * 扫描给定Stream, 提取下一个消息
     * @return
     * @throws IOException
     */
    byte[] nextMsg() throws IOException;
}
