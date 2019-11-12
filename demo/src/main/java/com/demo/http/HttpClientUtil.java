package com.demo.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.omg.CORBA.SystemException;
import org.xml.sax.InputSource;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;


public class HttpClientUtil {

    private static final String APPLICATION_JSON = "application/json";
    private static final String CONTENT_TYPE_TEXT_JSON = "text/json";

    public static void main(String[] args) {

    }

    // static HttpClient httpclient = new DefaultHttpClient();

    @SuppressWarnings({"deprecation", "resource"})
    public static String httpGet(String url) {

        HttpClient httpclient = new DefaultHttpClient();

        HttpGet httpget = new HttpGet(url);

        HttpResponse response;
        String rs = null;
        try {
            response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            rs = EntityUtils.toString(entity);
            System.out.println(rs);
            // JSONArray job = JSONML.toJSONArray(rs);

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rs;
    }

    @SuppressWarnings({"resource", "deprecation", "rawtypes"})
    public static String httpPost(String url, Map paraMap) {
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(url);
        // 建立HttpPost对象

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        // 建立一个NameValuePair数组，用于存储欲传送的参数
        Iterator ite = paraMap.keySet().iterator();
        while (ite.hasNext()) {
            String key = (String) ite.next();
            params.add(new BasicNameValuePair(key,
                    paraMap.get(key) == null ? "" : paraMap.get(key).toString()));
        }

        String result = null;
        try {
            httppost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            HttpResponse response = httpclient.execute(httppost);
            result = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
            // 得到返回的字符串
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @SuppressWarnings({"deprecation", "resource"})
    public static String httpPostWithJSON(String url, String json) {
        // 将JSON进行UTF-8编码,以便传输中文
        String encoderJson = json;
        String result = null;
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);

        try {
            StringEntity se = new StringEntity(encoderJson);
            se.setContentType(CONTENT_TYPE_TEXT_JSON);
            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
                    APPLICATION_JSON));
            httpPost.setEntity(se);
            HttpResponse response = httpClient.execute(httpPost);
            result = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 得到返回的字符串
        // System.out.println("----------------------------");
        // System.out.println(result);
        return result;

    }

    @SuppressWarnings({"unchecked", "deprecation"})
    public static Map<String, String> parseXmlToMap(String xml) {
        Map<String, String> resultMap = new HashMap<String, String>();
        try {
            StringReader read = new StringReader(xml);
            // 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
            InputSource source = new InputSource(read);
            // 创建一个新的SAXBuilder
            SAXBuilder sb = new SAXBuilder();
            // 通过输入源构造一个Document
            Document doc = (Document) sb.build(source);
            Element root = doc.getRootElement();// 指向根节点
            List<Element> es = root.getChildren();
            if(es != null && es.size() != 0) {
                for (Element element : es) {
                    resultMap.put(element.getName(), element.getValue());
                }
            }
        } catch (Exception e) {
        }
        return resultMap;
    }

    public static String sendPostUrl(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流（设置请求编码为UTF-8）
            out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 获取请求返回数据（设置返回数据编码为UTF-8）
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            System.out.println("test===" + result);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(out != null) {
                    out.close();
                }
                if(in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return result;
    }


}
