package com.accfcx.ch4;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Logger;

/**
 * @author accfcx
 * @desc
 **/
public class TimeLimitEchoProtocol implements Runnable{

    static final int BUFFSIZE = 32;
    static final String TIMELIMIT = "10000";
    static final String TIMELIMITPROP = "Timelimit";

    static int timelimit;
    Socket clntSock;
    Logger logger;

    public TimeLimitEchoProtocol(Socket clntSock, Logger logger) {
        this.clntSock = clntSock;
        this.logger = logger;

        timelimit = Integer.parseInt(System.getProperty(TIMELIMITPROP, TIMELIMIT));
    }

    public static void handleClient(Socket socket, Logger logger) {
        try{
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            int recvMsgSize;
            int totalBytes = 0;
            byte[] echoBuffer = new byte[BUFFSIZE];

            long endTime = System.currentTimeMillis() + timelimit;
            int timeBoundMills = timelimit;

            socket.setSoTimeout(timeBoundMills);

            while ((timeBoundMills > 0) &&
                    ((recvMsgSize = inputStream.read(echoBuffer)) != -1)) {
                outputStream.write(echoBuffer, 0, recvMsgSize);

                totalBytes += recvMsgSize;

                timeBoundMills =(int) (endTime - System.currentTimeMillis());
                socket.setSoTimeout(timeBoundMills);

            }

            logger.info("client:" + socket.getRemoteSocketAddress() + " echo " + totalBytes + " bytes");
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    @Override
    public void run() {
        handleClient(this.clntSock, logger);
    }
}
