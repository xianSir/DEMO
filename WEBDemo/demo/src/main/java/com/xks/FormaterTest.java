package com.xks;


import java.text.MessageFormat;
import java.util.Formatter;
import java.util.Locale;

/**
 * @author xks
 * @date 2019-11-08
 */
public class FormaterTest {
    public static void main(String[] args) {
//        String s = "a disturbance in the Force";
//        Object[] event={s};
//        MessageFormat temp = new MessageFormat("at{0}");
//        System.out.println(temp.format(event));
//        MessageFormat messageFormat = new MessageFormat("at{0}", Locale.CHINA);
//        String format1 = messageFormat.format(event);
//        String result = MessageFormat.format("At {0}",event);
        //String format = MessageFormat.format("启动流程 {0}","测试");
        Formatter a = new Formatter();
        System.out.println(String.format("%d", 93));
        System.out.println(a.format("%1$s","96").toString());
        System.out.println(String.format("%2$s", 32, "Hello"));

    }
}
