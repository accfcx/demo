package com.accfcx.vote.tcp;

import com.accfcx.vote.coder.BinaryCoder;
import com.accfcx.vote.coder.VoteMsg;
import com.accfcx.vote.coder.VoteMsgCoder;
import com.accfcx.vote.framer.ByteStuffingDelimiterFramer;
import com.accfcx.vote.framer.Framer;
import com.accfcx.vote.service.VoteService;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author accfcx
 * @desc
 **/
public class TcpServer {
    public static void main(String[] args) throws Exception {
        ServerSocket servSock = new ServerSocket(9090);

        VoteMsgCoder coder = new BinaryCoder();
        VoteService service = new VoteService();


        while (true) {
            Socket socket = servSock.accept();

            System.out.println("TCP请求来源的客户端: " + socket.getRemoteSocketAddress());

//            Framer framer = new DelimiterFramer(socket.getInputStream());
//            Framer framer = new MultiByteDelimiterFramer(socket.getInputStream());
            Framer framer = new ByteStuffingDelimiterFramer(socket.getInputStream());

            try {
                byte[] req;
                while ((req = framer.nextMsg()) != null) {
                    System.out.println("Received message (" + req.length + " bytes)");
                    VoteMsg responseMsg = service.handleRequest(coder.decode(req));
                    framer.frameMsg(coder.encode(responseMsg), socket.getOutputStream());
                }
            } catch (Exception ioe) {
                System.err.println("Error handling client: " + ioe.getMessage());
            } finally {
                System.out.println("Closing connection");
                socket.close();
            }

        }
    }
}
