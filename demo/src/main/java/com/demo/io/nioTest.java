package com.demo.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author xks
 * @date 2019-05-10
 */
public class nioTest {
    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\Administrator\\Desktop\\a.txt");
        FileInputStream  in= new FileInputStream(file);
        FileChannel inChannel = in.getChannel();
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        int read = inChannel.read(buffer);
        if(read!=-1){

        }

    }
}
