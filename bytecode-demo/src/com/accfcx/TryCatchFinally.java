package com.accfcx;

/**
 * @author accfcx
 **/
public class TryCatchFinally {
    public static void main(String[] args) throws Exception {
//        foo();
    }

    public int foo() throws Exception {
        try {
            int a = 1 / 0;
            return 0;
        } catch (Exception e) {
            int b = 1 / 0;
            return 1;
        } finally {
            return 2;
        }
    }

    public int bar() {
        int i = 100;
        try {
            return i;
        } finally {
            ++i;
        }
    }

    public String bar2() {
        String s = "hello";
        try {
            return s;
        } finally {
            s = null;
        }
    }
}
