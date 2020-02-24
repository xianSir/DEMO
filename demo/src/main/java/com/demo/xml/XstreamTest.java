package com.demo.xml;


import com.demo.xml.insurance.DataModel;
import com.demo.xml.insurance.DsEmp;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import org.xml.sax.InputSource;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * @author xks
 * @date 2020-02-20
 */
public class XstreamTest {
    public static void main(String[] args) {
//        Dom4JDriver dom4JDriver = new Dom4JDriver();
//        dom4JDriver.getOutputFormat().setEncoding("GBK");
//不进行特殊字符处理 new StaxDriver(new NoNameCoder())
        StaxDriver staxDriver=new StaxDriver();
        XStream xStream = new XStream(staxDriver);
        DataModel model = new DataModel();
        model.setRsxtid("123");
        model.setXmbh("1");
        ArrayList<DsEmp> dsEmps = new ArrayList<>();
        for (int i=0;i<3;i++) {
            DsEmp emp=new DsEmp();
            emp.setJtzz("第一个  "+i);
            dsEmps.add(emp);
        }
        model.setDs_emp(dsEmps);

        xStream.processAnnotations(DataModel.class);
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        xStream.toXML(model,new OutputStreamWriter(stream , Charset.forName("GBK")));
//        String s = new String(stream.toByteArray());
//        System.out.println(s);
        //Object to XML Conversion
        // String xml = xStream.toXML(model);
        // System.out.println(xml);
//        System.out.println(formatXml(xml));
    }


    public static String formatXml(String xml) {
        try {
            Transformer serializer = SAXTransformerFactory.newInstance().newTransformer();
            serializer.setOutputProperty(OutputKeys.INDENT, "yes");
            serializer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            Source xmlSource = new SAXSource(new InputSource(new ByteArrayInputStream(xml.getBytes())));
            StreamResult res = new StreamResult(new ByteArrayOutputStream());
            serializer.transform(xmlSource, res);
            return new String(((ByteArrayOutputStream) res.getOutputStream()).toByteArray());
        } catch (Exception e) {
            return xml;
        }
    }

}
