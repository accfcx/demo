package com.accfcx.vote.coder;

import java.io.*;

/**
 * @author accfcx
 * @desc binary
 * byte1 -> 010101[高6位bit], 低2位bit对应 2个bool
 * byte2 -> 00000000
 * byte3,byte4 -> int ????
 * byte5,byte6,byte7,byte8 -> long
 **/
public class BinaryCoder implements VoteMsgCoder {

    public static final int MIN_WIRE_LENGTH = 4;
    public static final int MAX_WIRE_LENGTH = 16;
    // 2个字节 01010100 00000000
    public static final int MAGIC = 0x5400;
    //        11111100 00000000
    public static final int MAGIC_MASK = 0xfc00;

    public static final int MAGIC_SHIFT = 8;

    //        00000010 00000000
    public static final int RESPONSE_FLAG = 0x0200;

    //        00000001 00000000
    public static final int INQUIRE_FLAG = 0x0100;

    @Override
    public byte[] encode(VoteMsg msg) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(byteArrayOutputStream);

        short magicAndFlags = MAGIC;
        if (msg.isInquiry()) {
            magicAndFlags |= INQUIRE_FLAG;
        }
        if (msg.isResponse()) {
            magicAndFlags |= RESPONSE_FLAG;
        }

        out.writeShort(magicAndFlags);

        out.writeShort((short) msg.getCandidateID());

        if (msg.isResponse()) {
            out.writeLong(msg.getVoteCount());
        }

        out.flush();

        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public VoteMsg decode(byte[] input) throws IOException {

        if (input.length < MIN_WIRE_LENGTH) {
            throw new IOException("最小长度为4字节");
        }

        ByteArrayInputStream inputStream = new ByteArrayInputStream(input);
        DataInputStream in = new DataInputStream(inputStream);


        short magicAndFlag = in.readShort();

        if ((magicAndFlag & MAGIC_MASK ) != MAGIC) {
            throw new IOException("magic is wrong: #" + ((magicAndFlag & MAGIC_MASK) >> MAGIC_SHIFT) );
        }

        boolean isInquiry = (magicAndFlag & INQUIRE_FLAG) != 0;
        boolean isResponse = (magicAndFlag & RESPONSE_FLAG) != 0;

        int candidateID = in.readShort();

        long voteCount = 0;
        if (isResponse) {
            voteCount = in.readLong();
        }

        return new VoteMsg(isInquiry, isResponse, candidateID, voteCount);
    }


    public static void main(String[] args) {
        System.out.println(Long.toBinaryString(0x0100));
    }
}
