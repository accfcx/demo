package com.accfcx.ch4;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.logging.Logger;
import java.util.zip.GZIPOutputStream;

/**
 * @author accfcx
 * @desc
 **/
public class CompressServer implements Runnable {
    public static final int BUFSIZE = 1024;
    private Socket clntSock;
    private Logger logger;


    public CompressServer(Socket clntSock, Logger logger) {
        this.clntSock = clntSock;
        this.logger = logger;
    }


    public static void handleCompressClient(Socket clntSock, Logger logger) {

        try {
            InputStream in = clntSock.getInputStream();
            GZIPOutputStream out = new GZIPOutputStream(clntSock.getOutputStream());

            byte[] buffer = new byte[BUFSIZE];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }

            out.finish(); // Flush bytes from GZIPOutputStream

            logger.info("Client " + clntSock.getRemoteSocketAddress() + " finished");
        } catch (IOException ex) {

            ex.printStackTrace();
        }
    }


    @Override
    public void run() {
        handleCompressClient(this.clntSock, this.logger);
    }
}
