package com.demo.nio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * @author xks
 * @date 2019-07-05
 */
public class socketTest {
    public static void main(String[] args) throws IOException {

            Socket socket = new Socket();
            InetAddress host = InetAddress.getByName("localhost");
            InetSocketAddress address = new InetSocketAddress(15001);
            socket.bind(address);
            socket.connect(  new InetSocketAddress("localhost",8080));
            OutputStream out = socket.getOutputStream();
            out.write("Bye".getBytes());
            out.flush();

    }
}
