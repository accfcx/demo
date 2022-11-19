package com.accfcx.vote;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

/**
 * @author accfcx
 * @desc
 **/
public class EncodingV2 {
    private static byte byteVal = 101; // one hundred and one
    private static short shortVal = 10001; // ten thousand and one
    private static int intVal = 100000001; // one hundred million and one
    private static long longVal = 1000000000001L;// one trillion and one

    private final static int BSIZE = Byte.SIZE / Byte.SIZE;
    private final static int SSIZE = Short.SIZE / Byte.SIZE;
    private final static int ISIZE = Integer.SIZE / Byte.SIZE;
    private final static int LSIZE = Long.SIZE / Byte.SIZE;

    private final static int BYTEMASK = 0xFF; // 8 bits

    public static String byteArrayToDecimalString(byte[] bArray) {
        StringBuilder rtn = new StringBuilder();
        for (byte b : bArray) {
            rtn.append(b & BYTEMASK).append(" ");
        }
        return rtn.toString();
    }

    public static int encodeIntBigEndian(byte[] dst, long val, int offset, int size) {
        // 101 39 17 5 245 225 1 0 0 0 232 212 165 16 1
        // 101 39 17 5 245 225 1 0 0 0 232 212 165 16 1
        for (int i = 0; i < size; i++) {
            dst[offset++] =  (byte) (((byte) (val >> ((size - i - 1) * Byte.SIZE))) );
        }
        return offset;
    }

    public static long decodeIntBigEndian(byte[] val, int offset, int size) {
        long rtn = 0;
        for (int i = 0; i < size; i++) {
            rtn = (rtn << Byte.SIZE) | ((long) val[offset + i] & BYTEMASK);
        }
        return rtn;

    }

    public static void main(String[] args) throws IOException {


        long value1 = 2147483650L;

        System.out.println(Long.toBinaryString(value1));

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        tranLongToStream(value1, byteArrayOutputStream);

        System.out.println(Arrays.toString(byteArrayOutputStream.toByteArray()));

        byte[] message = new byte[BSIZE + SSIZE + ISIZE + LSIZE];
        int offset = encodeIntBigEndian(message, byteVal, 0, BSIZE);
        offset = encodeIntBigEndian(message, shortVal, offset, SSIZE);
        offset = encodeIntBigEndian(message, intVal, offset, ISIZE);
        encodeIntBigEndian(message, longVal, offset, LSIZE);
        System.out.println("Encoded message: " + byteArrayToDecimalString(message));


        long value = decodeIntBigEndian(message, BSIZE, SSIZE);
        System.out.println("Decoded short = " + value);
        value = decodeIntBigEndian(message, BSIZE + SSIZE + ISIZE, LSIZE);
        System.out.println("Decoded long = " + value);

        offset = 4;

        value = decodeIntBigEndian(message, offset, BSIZE);
        System.out.println("Decoded value (offset " + offset + ", size " + BSIZE + ") = " + value);
        byte bVal = (byte) decodeIntBigEndian(message, offset, BSIZE);
        System.out.println("Same value as byte = " + bVal);


        // 内置DataOuputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);


        dataOutputStream.writeByte(byteVal);
        dataOutputStream.writeShort(shortVal);
        dataOutputStream.writeInt(intVal);
        dataOutputStream.writeLong(longVal);

        dataOutputStream.flush();
        byte[] msg = outputStream.toByteArray();

        System.out.println("Encoded message v2: " + byteArrayToDecimalString(msg));

    }

    // [-128, 0, 0, 2]
    // 10000000 00000000 00000000 00000010
    public static void tranLongToStream(Long value, OutputStream out) throws IOException {
        byte[] valueArray = new byte[4];

        for (int i = 0; i < 4; i++) {
            valueArray[i] = (byte) ((value >> (4 - i - 1) * Byte.SIZE) & 0xff);
        }

        out.write(valueArray);

        out.flush();
    }

}
