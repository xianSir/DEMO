package com.demo.nio;

/**
 * @author xks
 * @date 2019-07-05
 */
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.Selector;
import java.nio.channels.SelectionKey;
import java.nio.ByteBuffer;
import java.io.IOException;
import java.util.Set;
import java.util.Iterator;
import java.net.InetSocketAddress;

public class SelectorExample {
    public static void main(String[] args) throws IOException {
        // Get the selector
        Selector selector = Selector.open();
       // System.out.println("开启选择器 " + selector.isOpen());
        // Get the server socket channel and register using selector
        ServerSocketChannel SS = ServerSocketChannel.open();
        InetSocketAddress hostAddress = new InetSocketAddress("localhost", 8080);
        SS.bind(hostAddress);
        SS.configureBlocking(false);
        int ops = SS.validOps();
        System.out.println("开始监听");
        SelectionKey selectKy = SS.register(selector, ops, null);
        for (;;) {
            System.out.println("等待操作");
            Set selectedKeys = selector.selectedKeys();
            Iterator itr = selectedKeys.iterator();
            int noOfKeys = selector.select();
            System.out.println("选择器通道数量**********************************************************************" + noOfKeys);
            while (itr.hasNext()) {
                SelectionKey ky = (SelectionKey) itr.next();
                if (ky.isAcceptable()) {
                    // The new client connection is accepted
                    SocketChannel client = SS.accept();
                    client.configureBlocking(false);
                    // The new connection is added to a selector
                    client.register(selector, SelectionKey.OP_READ);
                    client.write(ByteBuffer.allocate(2000).put("Bye Bye".getBytes()));
                    System.out.println("客户端新注册选择器:-------------- ---------------------------------------------------" + client);
                } else if (ky.isReadable()) {
                    // Data is read from the client
                    SocketChannel client = (SocketChannel) ky.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(256);
                    client.read(buffer);
                    String output = new String(buffer.array()).trim();
                    System.out.println("消息:  " + output);
                    if (output.equals("Bye")) {
                        client.close();
                        System.out.println("关闭客户端");
                    }
                }
                itr.remove();
            } // end of while loop
        } // end of for loop
    }
}

