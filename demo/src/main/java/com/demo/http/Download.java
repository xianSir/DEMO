package com.demo.http;

import com.demo.io.FileUtil;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xks
 * @date 2019-12-24
 */
public class Download {
    private static void createio() throws IOException {
        URL url = new URL("http://60.212.191.103:8900/file/contracts/2F7B7977A8894D5E8B4A5D8E2013FFAC_刘冬方反.jpg");
        URLConnection connection = url.openConnection();
        InputStream in = connection.getInputStream();
        File des = new File("G:\\demo.png");
        des.createNewFile();
        FileOutputStream out = new FileOutputStream(des);
        byte[] arr=new byte[1024*3];
        while ((in.read(arr)>0)){
            out.write(arr, 0, arr.length);
        }
        in.close();
        out.close();
    }

    private static void readFileHttp() {
        File file = new File("F:\\A_IDEA_WORK\\study\\demo\\b.txt");
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

    /**
     * 更具网络地址下载文件
     * @throws Exception
     */
    public static void downloadFile() throws Exception {
        String decode = URLEncoder.encode("刘冬方反.jpg", "UTF-8");
        URL url = new URL("http://60.212.191.103:8900/file/contracts/2F7B7977A8894D5E8B4A5D8E2013FFAC_"+decode);
        URLConnection connection = url.openConnection();
        connection.setConnectTimeout(5*1000);
        InputStream in = connection.getInputStream();
        File des = new File("G:/刘冬方反.jpg");
        des.createNewFile();
        FileOutputStream out = new FileOutputStream(des);
        int len=-1;
        int i=0;
        //注意数组长度 否则文件不正确
        byte[] arr=new byte[1024];
        while ((len = in.read(arr)) != -1){
            out.write(arr,0,len);
            i+=arr.length;
        }
        System.out.println(i);
        in.close();
        out.close();
    }

    public void HttpReadJsp() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 建立HttpPost对象
        HttpGet httppost = new HttpGet("http://qq.ip138.com/idsearch/index.asp?userid=34252119570111701X&action=idcard");
        httppost.addHeader("Content-type", "text/html");
        httppost.addHeader("Connection", "keep-alive");
        httppost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.2; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        // 建立一个NameValuePair数组，用于存储欲传送的参数
        params.add(new BasicNameValuePair("q","370625197005062936" ));
        String result = null;
        // httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        CloseableHttpResponse response = httpClient.execute(httppost);
        System.out.println();

        String s = EntityUtils.toString(response.getEntity(), Charset.forName("utf-8"));
        String[] split = s.split("\r\n");
        for (int i=0;i<split.length;i++){
            System.out.println(i+split[i]);
        }
    }


    public static byte[] openFile(String filePath) {
        try {
            String url = filePath.substring(0, filePath.lastIndexOf('/') + 1);
            String filename = filePath.substring(filePath.lastIndexOf('/') + 1);
            filename = URLEncoder.encode(filename, "UTF-8");
            filename = filename.replaceAll("\\+", "%20");
            URL httpUrl = new URL(url + filename);
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5 * 1000);
            InputStream inStream = conn.getInputStream();//通过输入流获取图片数据
            byte[] btImg = readInstream(inStream);//得到图片的二进制数据 return btImg;
            return btImg;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    //读取流文件的内容
    public static byte[] readInstream(InputStream inputStream) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(); //创建ByteArrayOutputStream 对象
        byte[] buffer = new byte[1024];//声明缓冲区
        int length = -1;//定义读取默认长度
        while ((length = inputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, length);
            //把缓存区中输出到内存中
        }

        byteArrayOutputStream.close(); //关闭输出流
        inputStream.close(); //关闭输入流
        return byteArrayOutputStream.toByteArray();
    }

    public static void downloadFile(HttpServletResponse response, HttpServletRequest request, String filePath) throws IOException {

        OutputStream os = null;
        try {
            String fileName = filePath.substring(filePath.lastIndexOf('/') + 1);
            os = response.getOutputStream();
            response.setContentType("application/octet-stream; charset=utf-8");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" +new BASE64Encoder().encode(fileName.getBytes("utf-8")));
            response.setCharacterEncoding("utf-8");
            os.write(FileUtil.openFile(filePath));
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
