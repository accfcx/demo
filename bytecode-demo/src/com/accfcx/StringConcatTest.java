package com.accfcx;

/**
 * @author accfcx
 **/
public class StringConcatTest {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Must enter any two args.");
            return;
        }

        System.out.println(args[0] + args[1]);
    }
}
