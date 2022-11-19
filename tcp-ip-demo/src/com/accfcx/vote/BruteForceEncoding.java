package com.accfcx.vote;

/**
 * @author accfcx
 * @desc
 **/
public class BruteForceEncoding {
    private static byte byteVal = 101;
    private static short shortVal = 10001;
    private static int intVal = 100000001;
    private static long longVal = 1000000000001L;

    private final static int BSIZE = Byte.SIZE;
    private final static int SSIZE = Short.SIZE;
    private final static int ISIZE = Integer.SIZE;
    private final static int LSIZE = Long.SIZE;

    private final static int BYTEMASK = 0xFF;

    public static void main(String[] args) {

        byte[] message = new byte[BSIZE + SSIZE + ISIZE + LSIZE];
//        byte[] message = new byte[BSIZE * 4];


        int offset = encodeIntBigEndian(message, byteVal, 0, BSIZE);
        offset = encodeIntBigEndian(message, shortVal, offset, SSIZE);
        offset = encodeIntBigEndian(message, intVal, offset, ISIZE);

        encodeIntBigEndian(message, longVal, offset, LSIZE);

        System.out.println("encoded message:" + byteArrayToDecimalString(message));


//        long value = decodeIntBigEndian(message, 0, BSIZE);
//        System.out.println("byte:" + (byte) value);
//
//        value = decodeIntBigEndian(message, BSIZE * 1, BSIZE);
//        System.out.println("short:" + value);
//
//        value = decodeIntBigEndian(message, BSIZE * 2, BSIZE);
//        System.out.println("int:" + value);
//
//        value = decodeIntBigEndian(message, BSIZE * 3, BSIZE);
//        System.out.println("long:" + value);

//        long value = decodeIntBigEndian(message, BSIZE, BSIZE);
//        System.out.println("short = " + value);
//        value = decodeIntBigEndian(message, BSIZE  + SSIZE + ISIZE, BSIZE);
//        System.out.println("long = " + value);
//
//
        offset = 4;
        long value = decodeIntBigEndian(message, offset, BSIZE);
        System.out.println("value (offset " + offset + ", size " + BSIZE + ") =" + value);

        byte bVal = (byte) decodeIntBigEndian(message, offset, BSIZE);
        System.out.println("same value as byte = " + bVal);

    }

    public static String byteArrayToDecimalString(byte[] bArray) {
        StringBuilder rtn = new StringBuilder();

        for (byte b : bArray) {
            rtn.append(b & BYTEMASK).append(" ");
        }

        return rtn.toString();
    }

    public static int encodeIntBigEndian(byte[] dst, long val, int offset, int size) {
        for (int i = 0; i < size; i++ ) {
            // 基础类型的高位字节 放置在 字节数组的低位
            byte tmpVal = (byte) (val >> ((size - i - 1) * Byte.SIZE));
            dst[offset++] = tmpVal;
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


}
