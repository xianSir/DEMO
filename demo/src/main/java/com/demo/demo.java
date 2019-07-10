package com.demo;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author xks
 * @date 2019-05-20
 */
public class demo {
    public static void main(String[] args) throws Exception {

        File file = new File("F:\\09 e筑云平台\\标准版\\09 数据库脚本\\初始化脚本\\smz_db\\1_inittable.sql");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        Stream<String> s = reader.lines();


        long a1 = new Date().getTime();

        while (reader.read()!=-1){
            System.out.println(reader.readLine());
        }

        System.out.println(new Date().getTime()-a1);
        System.out.println("********************************");

        long a2 = new Date().getTime();
        s.forEach(System.out::println);
        System.out.println(new Date().getTime()-a2);


    }
    @FunctionalInterface
    interface  demointerface{
        int out(int a,int b);
    }
    static void count(demointerface a){
        System.out.println(a.out(3, 5));
    }
}
