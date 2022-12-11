package com.accfcx;

/**
 * @author accfcx
 **/
public class RunnableTest {
    public static void main(String[] args) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello, i'm a thread");
            }
        };
        r.run();
    }
}
