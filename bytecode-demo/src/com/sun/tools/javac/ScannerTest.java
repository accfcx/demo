package com.sun.tools.javac;

import com.sun.tools.javac.parser.Scanner;
import com.sun.tools.javac.parser.ScannerFactory;
import com.sun.tools.javac.util.Context;

/**
 * @author accfcx
 **/
public class ScannerTest {
    public static void main(String[] args) {
        ScannerFactory factory = ScannerFactory.instance(new Context());

        Scanner scanner = factory.newScanner("int k = i + j;", false);

        scanner.nextToken();
        System.out.println(scanner.token().kind);
        scanner.nextToken();
        System.out.println(scanner.token().name());

        scanner.nextToken();
        System.out.println(scanner.token().kind);
        scanner.nextToken();
        System.out.println(scanner.token().name());

        scanner.nextToken();
        System.out.println(scanner.token().kind);
        scanner.nextToken();
        System.out.println(scanner.token().name());

        scanner.nextToken();
        System.out.println(scanner.token().kind);
    }
}
