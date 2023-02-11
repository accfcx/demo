package com.accfcx;

/**
 * @author accfcx
 **/
public class StringInternTest {
    public static void main(String[] args) {
//        String a="c";
//        String b = new String("c");
//        System.out.println(a==b);
//        System.out.println((a==b.intern()));
//        System.out.println((b==b.intern()));

//        String a = new String("hellow") + new String("orld");
//        String b = new String("hello") + new String("world");
//        System.out.println((a==a.intern())); // true
//        System.out.println((a==b.intern())); // false
//        System.out.println((b==b.intern())); // false


        String a = "hello";
        String b = a+"world";
        String c = "helloworld";
        String d = "hello"+"world";
        System.out.println(b==c); // false
        System.out.println(d==c); // false
        System.out.println(b==d); // false

    }
}
