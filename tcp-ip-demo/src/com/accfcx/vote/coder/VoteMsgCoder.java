package com.accfcx.vote.coder;

import java.io.IOException;

/**
 * @author accfcx
 * @desc
 * text
 * binary
 **/
public interface VoteMsgCoder {

    byte[] encode(VoteMsg msg) throws IOException;

    VoteMsg decode(byte[] input) throws IOException;
}
