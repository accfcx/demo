package com.accfcx;

/**
 * @author accfcx
 **/
public class SubClassInvokeMethodTest extends InvokeMethodTest {

    void interestingMethod() {
        System.out.println("Subclass's interesting method.");
    }

    public static void main(String[] args) {
        SubClassInvokeMethodTest me = new SubClassInvokeMethodTest();
        me.exampleMethod();
    }
}
