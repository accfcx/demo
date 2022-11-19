package com.accfcx.channel;

import java.io.IOException;
import java.nio.channels.SelectionKey;

/**
 * @author accfcx
 * @desc
 **/
public interface TcpProtocol {

    void handleAccept(SelectionKey key) throws IOException;
    void handleRead(SelectionKey key) throws IOException;
    void handleWrite(SelectionKey key) throws IOException;
}
