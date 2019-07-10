package com.demo.dateUntils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.logging.SimpleFormatter;

/**
 * @author xks
 * @date 2019-04-03
 */
public class main {
    public static void main(String[] args) {
        SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c=Calendar.getInstance();
       // c.add(Calendar.MONTH, 0);
        //c.set(Calendar.MONTH, 1);
       // c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        c.set(Calendar.DAY_OF_MONTH, 24);
        System.out.println(s.format(c.getTime()));
        System.out.println( c.get(Calendar.DAY_OF_WEEK) );
        Optional.ofNullable("");
    }
}
