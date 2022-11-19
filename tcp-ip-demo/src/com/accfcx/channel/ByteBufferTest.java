package com.accfcx.channel;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * @author accfcx
 * @desc
 **/
public class ByteBufferTest {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(4);

        buffer.putShort((short) 1);

        buffer.order(ByteOrder.LITTLE_ENDIAN);

        buffer.putShort((short)2);

//        System.out.println(buffer.array());

        for (int i = buffer.arrayOffset(); i < buffer.array().length; i++) {
            System.out.println(buffer.get(i));
        }

    }
}
