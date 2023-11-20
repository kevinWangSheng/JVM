package com.kevin.demo.JVM;

/**
 * @author wang
 * @create 2023-11-20-14:13
 */
public class HelloWorld {
    public static void main(String[] args) {
        long x = fibonacci(10);
        System.out.println(x);
    }

    //斐波那契数列（Fibonacci sequence）
    private static long fibonacci(long n) {
        if (n <= 1) {
            return n;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }
}
