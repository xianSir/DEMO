package com.demo.nio;


import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Stream;

/**
 * @author xks
 * @date 2019-05-27
 */
public class demo2 {

    public static void main(String[] args) throws Exception {
        String data = "Scattering and Gathering example shown in yiibai.com";
        //gatherBytes(data);
        //scatterBytes();
        String relativelyPath = System.getProperty("user.dir")+"/a.txt";
        //String relativelyPath = "C:\\Users\\Administrator\\Desktop\\a.txt";
        BufferedReader reader = Files.newBufferedReader(Paths.get(relativelyPath), Charset.forName("utf-8"));
        List<String> list = Files.readAllLines(Paths.get(relativelyPath), Charset.forName("utf-8"));
        Stream<String> lines = reader.lines();
        lines.forEach(e->
           System.out.println(e)
        );
        for (String s : list) {
            System.out.println(s);
        }


    }

    /*
     * gatherBytes() is used for reading the bytes from the buffers and write it
     * to a file channel.
     */
    public static void gatherBytes(String data) {
        String relativelyPath = System.getProperty("user.dir");
        // The First Buffer is used for holding a random number
        ByteBuffer buffer1 = ByteBuffer.allocateDirect(200);
        // The Second Buffer is used for holding a data that we want to write
        ByteBuffer buffer2 = ByteBuffer.allocateDirect(400);
       // buffer1.asIntBuffer().put(4200);
        buffer1.asCharBuffer().put("");
        buffer2.asCharBuffer().put("buffer2.asCharBuffer().put(\"\"data);\n"+data);
        GatheringByteChannel gatherer = createChannelInstance(relativelyPath + "/a.txt", true);
        // Write the data into file
        try {
            gatherer.write(new ByteBuffer[]{buffer1, buffer2});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * scatterBytes() is used for reading the bytes from a file channel into a
     * set of buffers.
     */
    public static void scatterBytes() {
        String relativelyPath = System.getProperty("user.dir");
        // The First Buffer is used for holding a random number
        ByteBuffer buffer1 = ByteBuffer.allocate(4000);
        // The Second Buffer is used for holding a data that we want to write
        ByteBuffer buffer2 = ByteBuffer.allocate(400);
        ScatteringByteChannel scatter = createChannelInstance(relativelyPath + "/a.txt", false);
        // Reading a data from the channel
        try {
            scatter.read(new ByteBuffer[]{buffer1, buffer2});
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Read the two buffers seperately
        buffer1.rewind();
        buffer2.rewind();

        String s = buffer1.asCharBuffer().toString();
        String bufferTwo = buffer2.asCharBuffer().toString();
        // Verification of content
        System.out.println(s+"66666666666");
        System.out.println(bufferTwo+"------------");
    }

    public static FileChannel createChannelInstance(String file, boolean isOutput) {
        FileChannel FChannel = null;
        try {
            if(isOutput) {
                FChannel = new FileOutputStream(file).getChannel();
            } else {
                FChannel = new FileInputStream(file).getChannel();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return FChannel;
    }


}

