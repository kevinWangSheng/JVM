package com.kevin.demo.JVM;

/**
 * @author wang
 * @create 2023-11-20-14:13
 */
public class HelloWorld {
    public static void main(String[] args) {
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            sum += i;
        }
        System.out.println(sum);
    }
}
