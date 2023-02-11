package com.accfcx;

/**
 * @author accfcx
 **/
public class InvokeMethodTest {
    private void interestingMethod() {
        System.out.println("Superclass's interesting method.");
    }

    void exampleMethod() {
        interestingMethod();
    }
}
