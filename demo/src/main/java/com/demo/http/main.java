package com.demo.http;

import com.demo.io.FileUtil;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
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
import java.net.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xks
 * @date 2019-04-19
 */
public class main {
    public static void main(String[] args) throws Exception {
        Download.downloadFile();
    }


}
