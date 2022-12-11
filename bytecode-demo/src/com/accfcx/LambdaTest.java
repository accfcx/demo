package com.accfcx;

/**
 * @author accfcx
 **/
public class LambdaTest {
    public static void main(String[] args) {
        Runnable r = () -> System.out.println("hello, i'm a thread");

        r.run();
    }
}
