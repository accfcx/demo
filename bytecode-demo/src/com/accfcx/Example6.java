package com.accfcx;

/**
 * @author accfcx
 **/
public class Example6 {
    private int width = 3;

    public Example6() {
        this(1);
        System.out.println("Example6(), width = " + width);
    }

    Example6(int width) {
        this.width = width;
        System.out.println("Example6(int), width = " + width);
    }

    Example6(String msg) {

        super();
        System.out.println("Example6(String), width = " + width);
        System.out.println(msg);

    }


    public static void main(String[] args) {

        String msg = "The Agapanthus is also known as Lilly of the Nile.";
        Example6 one = new Example6();
        Example6 two = new Example6(2);
        Example6 three = new Example6(msg);

    }
}
