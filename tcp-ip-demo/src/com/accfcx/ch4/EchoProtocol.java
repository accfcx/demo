package com.accfcx.ch4;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author accfcx
 * @desc
 **/
public class EchoProtocol implements Runnable{

    static final int BUFFSIZE = 32;


    Socket clntSock;

    Logger logger;

    public EchoProtocol(Socket clntSock, Logger logger) {
        this.clntSock = clntSock;
        this.logger = logger;
    }

    public static void handleEchoClient(Socket clntSock, Logger logger) {
        try{
             InputStream inputStream = clntSock.getInputStream();
            OutputStream outputStream = clntSock.getOutputStream();

            int total = 0;
            int recvMsgSize = 0;

            byte[] echoBuffer = new byte[ BUFFSIZE];

            while ((recvMsgSize = inputStream.read(echoBuffer)) != -1) {
                outputStream.write(echoBuffer, 0, recvMsgSize);

                total += recvMsgSize;
            }


            logger.info("Client " + clntSock.getRemoteSocketAddress() + ", echoed " + total + " bytes");
        } catch (Exception e0) {
            logger.log(Level.WARNING, "exception ", e0);
        } finally {
            try{
                clntSock.close();
            } catch (IOException e) {

            }
        }
    }

    @Override
    public void run() {
        handleEchoClient(clntSock, logger);
    }
}
