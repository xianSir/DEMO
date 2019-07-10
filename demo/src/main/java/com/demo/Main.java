package com.demo;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Map;
import java.util.Properties;

/**
 * @author xks
 * @date 2019-05-27
 */
public class Main {

    public static void main(String[] args) throws Exception {
        String relativelyPath = System.getProperty("user.dir");
        FileInputStream input = new FileInputStream(relativelyPath + "/a.txt");
        ReadableByteChannel source = input.getChannel();
        FileOutputStream output = new FileOutputStream(relativelyPath + "/b.txt");
        WritableByteChannel destination = output.getChannel();
        copyData(source, destination);
        source.close();
        destination.close();
        System.out.println("Copy Data finished.");
    }

    private static void copyData(ReadableByteChannel src, WritableByteChannel dest) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocateDirect(20 * 1024);
        while (src.read(buffer) != -1) {
            // The buffer is used to drained
            buffer.flip();
            // keep sure that buffer was fully drained
            while (buffer.hasRemaining()) {
                dest.write(buffer);
            }
            buffer.clear(); // Now the buffer is empty, ready for the filling
        }
    }

    private static void get() throws IOException {
        //文件通道获取方式
        FileInputStream in = new FileInputStream("C:\\Users\\Administrator\\Desktop\\20190702161248.png");
        FileChannel channel = in.getChannel();
        //datagram通道获取方式
        DatagramChannel open = DatagramChannel.open();
        //socker通道获取方式
        SocketChannel socket = SocketChannel.open();
        ServerSocketChannel socketChannel = ServerSocketChannel.open();

        Properties properties = System.getProperties();
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {

            System.out.println(entry.getKey()+"----"+entry.getValue());
        }
    }
}

