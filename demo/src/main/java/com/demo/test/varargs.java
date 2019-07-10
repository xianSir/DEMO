package com.demo.test;

/**
 * @author xks
 * @date 2019-03-30
 */
public class varargs {
    public static void main(String[] args) {
        max(666,888,999);
    }
    public static int max(int... num) {
        int max = Integer.MIN_VALUE;
        System.out.println(max);
        for (int i = 0; i < num.length; i++) {
            System.out.println(num[i]);
            if (num[i] > max) {
                max = num[i];
            }
        }
        System.out.println("max="+max);
        return max;
    }
}
