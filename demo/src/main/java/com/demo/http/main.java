package com.demo.http;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.*;

/**
 * @author xks
 * @date 2019-04-19
 */
public class main {
    public static void main(String[] args) throws IOException {
        File file = new File("E:\\LEARN\\JAVA_SE\\src\\main\\resources\\a.txt");
        BufferedReader reader = null;
        try {
            System.out.println("以行为单位读取文件内容，一次读一行");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
                //一次读一行，读入null时文件结束
            while ((tempString = reader.readLine()) != null) {
                    //把当前行号显示出来
                System.out.println(tempString);
                HttpClientBuilder builder = HttpClientBuilder.create();
                CloseableHttpClient build = builder.build();
                HttpGet get = new HttpGet(tempString);
                try {
                    CloseableHttpResponse response = build.execute(get);
                    System.out.println( EntityUtils.toString(response.getEntity()));
                } catch (IOException e) {
                    System.err.println(tempString);
                    e.printStackTrace();
                }
                if (tempString.equals("")){

                }
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }


    }
}
