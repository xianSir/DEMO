package com.xks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author xks
 * @date 2019-08-20
 */
public class deal {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("121.36");
        list.add("121.36");
        list.add("104.36");
        list.add("104.36");
        list.add("104.36");
        list.add("5.36");
        list.add("188.36");
        int count = 0;
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                BigDecimal a = new BigDecimal(o1);
                BigDecimal b = new BigDecimal(o2);
                //count = o1 + o2;
                //小 -->  大
                return a.compareTo(b);
            }
        });
        list.forEach((e) -> {
            System.out.println(e);
        });

    }
}
