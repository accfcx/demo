package com.accfcx.vote;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * @author accfcx
 * @desc
 **/
public class CharsetTest {
    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println(EncodingV2.byteArrayToDecimalString("accfcx".getBytes()));
        System.out.println(EncodingV2.byteArrayToDecimalString("accfcx".getBytes(StandardCharsets.UTF_8)));
        System.out.println(EncodingV2.byteArrayToDecimalString("accfcx".getBytes("UTF-8")));

        System.out.println(1<<7);
        System.out.println(0x80);

        int BIT5 = 1 << 5;
        int BIT7 = 1 << 7;
        int BIT3And2 = 1 << 2 | 1 << 3;
        int bitMap = 1234567;

        System.out.println(Long.toBinaryString(bitMap));
        System.out.println(Long.toBinaryString(bitMap | BIT5));
        System.out.println(Long.toBinaryString(bitMap & ~BIT7));
    }
}
