package com.accfcx.ch4;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

/**
 * @author accfcx
 * @desc
 **/
public class TcpEchoServerThread {
    public static void main(String[] args) throws Exception{

        ServerSocket serverSocket = new ServerSocket(9090);


        Logger logger = Logger.getLogger("test");

        while (true) {
            Socket socket = serverSocket.accept();

//           Thread thread = new Thread(new EchoProtocol(socket, logger));
           Thread thread = new Thread(new CompressServer(socket, logger));

           thread.start();

           logger.info(" create thread : " + thread.getName());

        }
    }
}
