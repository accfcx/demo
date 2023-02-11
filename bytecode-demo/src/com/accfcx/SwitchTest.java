package com.accfcx;

/**
 * @author accfcx
 **/
public class SwitchTest {
    public static void main(String[] args) {
        String vale = "a";

        switch (vale) {
            case "b1":
                System.out.println("B1");
                break;
            case "b2":
                System.out.println("B2");
                break;
            case "b5":
                System.out.println("B5");
                break;
            default:
                System.out.println("default B");
        }
    }
}
