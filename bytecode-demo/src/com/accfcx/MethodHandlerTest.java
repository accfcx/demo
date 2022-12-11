package com.accfcx;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * @author accfcx
 **/
public class MethodHandlerTest {
    public static void main(String[] args) throws Throwable {
        MethodHandlerTest test = new MethodHandlerTest();

        MethodType methodType = MethodType.methodType(void.class, String.class);
        MethodHandle methodHandle = MethodHandles.lookup().findVirtual(MethodHandlerTest.class, "foo", methodType);
        methodHandle.invokeExact(test, "world");
    }

    public void foo(String s) {
        System.out.println("hello , " + s);
    }
}
