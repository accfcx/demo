package com.accfcx;

/**
 * @author accfcx
 **/
public class InvokeTest {
    public static void main(String[] args) {
        Color red = new Red();
        Color yellow = new Yellow();

        red.printColor();
        yellow.printColor();
    }

    public static void foo(Color color) {
        color.printColor();
    }
}


class Color {
    public void printColor() {
        System.out.println("parent color");
    }
}

class Red extends Color {

    @Override
    public void printColor() {
        System.out.println("red color");
    }
}

class Yellow extends Color {
    @Override
    public void printColor() {
        System.out.println("yellow color");
    }
}
