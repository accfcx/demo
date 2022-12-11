package com.accfcx;

import java.lang.reflect.Method;

/**
 * @author accfcx
 **/
public class ReflectTest {
    private static int count = 0;

    public static void foo() {
        new Exception("test#" + (count++)).printStackTrace();
    }

    public static void main(String[] args) throws Exception {
        Class<?> clazz = Class.forName("com.accfcx.ReflectTest");
        Method method = clazz.getMethod("foo");

        for (int i = 0; i < 20; i++) {
            method.invoke(null);
        }

        System.in.read();
    }
}
