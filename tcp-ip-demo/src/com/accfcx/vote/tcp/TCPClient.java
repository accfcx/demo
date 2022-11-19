package com.accfcx.vote.tcp;

import com.accfcx.vote.coder.BinaryCoder;
import com.accfcx.vote.coder.VoteMsg;
import com.accfcx.vote.coder.VoteMsgCoder;
import com.accfcx.vote.framer.ByteStuffingDelimiterFramer;
import com.accfcx.vote.framer.Framer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author accfcx
 * @desc
 **/
public class TCPClient {
    public static void main(String[] args) throws IOException {
        String address = "localhost";
//        String address = "255.255.255.255";
        int port = 9090;

        Socket sock = new Socket(address, port);

        OutputStream out = sock.getOutputStream();

// Change Bin to Text for a different framing strategy
        VoteMsgCoder coder = new BinaryCoder();
        // Change Length to Delim for a different encoding strategy
//        Framer framer = new DelimiterFramer(sock.getInputStream());
//        Framer framer = new MultiByteDelimiterFramer(sock.getInputStream());
        Framer framer = new ByteStuffingDelimiterFramer(sock.getInputStream());

// Create an inquiry request (2nd arg = true)

        byte candidateId = (byte)'\n';
        VoteMsg msg = new VoteMsg(true, false, candidateId, 0);
        byte[] encodedMsg = coder.encode(msg);

// Send request
        System.out.println("Sending Inquiry (" + encodedMsg.length + " bytes): ");
        System.out.println(msg);
        framer.frameMsg(encodedMsg, out);

// Now send a vote
        msg.setInquiry(false);
        encodedMsg = coder.encode(msg);
        System.out.println("Sending Vote (" + encodedMsg.length + " bytes): ");
        framer.frameMsg(encodedMsg, out);

// Receive inquiry response
        encodedMsg = framer.nextMsg();
        msg = coder.decode(encodedMsg);
        System.out.println("Received Response (" + encodedMsg.length + " bytes): ");
        System.out.println(msg);


        msg = coder.decode(framer.nextMsg());
        System.out.println("Received Response (" + encodedMsg.length + " bytes): ");
        System.out.println(msg);

        sock.close();
    }
}
