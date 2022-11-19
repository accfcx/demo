package com.accfcx;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class Main {

    public static void main(String[] args) {

        System.out.println((byte)'\n');
        // write your code here
        try{

            // byte被符号扩展为 int

            StringBuilder stringBuilder = new StringBuilder();
            byte[] bytes = new byte[5];
            for (int i = 0; i < 5; i++) {
                bytes[i] = (byte) (i - 100);
                stringBuilder.append( (bytes[i]) + " ");
            }

            byte b = (byte) -118;
            System.out.println(Integer.toBinaryString(b));

            long v = 10001;
            System.out.println(v >> 16);

            System.out.println(stringBuilder);


            Enumeration<NetworkInterface> interfaceList = NetworkInterface.getNetworkInterfaces();

            if (interfaceList == null) {
                System.out.println("NO interfaces found");
            } else {
                while (interfaceList.hasMoreElements()) {
                    NetworkInterface ni = interfaceList.nextElement();

                    System.out.println("START**************  Interface " + ni.getName() + " ");

                    Enumeration<InetAddress> addrList =  ni.getInetAddresses();

                    if (!addrList.hasMoreElements()) {
                        System.out.println("interface has no inetAddress");
                    } else {
                        InetAddress address = addrList.nextElement();
                        System.out.println(address instanceof Inet4Address ? "v4" : "v6");
                        System.out.println(":" + address.getHostAddress());
                    }
                    System.out.println("END**************");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        for ( String host: args) {
            System.out.println("host:" + host);

            try{
                InetAddress[] addressList = InetAddress.getAllByName(host);

                for (InetAddress address : addressList) {
                    System.out.println(address.getHostName() + "/" +  address.getHostAddress());
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }


}
