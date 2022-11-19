package com.accfcx.concurrency;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author accfcx
 * @desc
 **/
public class NestedLockoutDemo {
    final ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(10);
    int processed = 0;
    int accepted = 0;

    public static void main(String[] args) throws InterruptedException {
        NestedLockoutDemo demo = new NestedLockoutDemo();

        demo.start();
        for (int i = 0; i < 10000; i++) {
            System.out.println("start - accept");
            demo.accept("message-" + i);
            System.out.println("end - accept");
            try {
                Thread.sleep(100);
            } catch (Exception e){}
        }
    }

    public synchronized void accept(String msg) throws InterruptedException {
        System.out.println("accept:" + msg);
        queue.put(msg);
        accepted++;
    }

    public synchronized void doProcess() throws InterruptedException {
        System.out.println("toke:" + queue.take());
        processed++;
    }

    public void start() {
        System.out.println("start");
        new WorkerThread().start();
    }

    class WorkerThread extends Thread {
        @Override
        public void run() {
            try{
                while (true) {
                    try{
                        Thread.sleep(90);
                    } catch (Exception e) {

                    }
                    System.out.println("start - doProcess");
                    doProcess();
                    System.out.println("end - doProcess");
                }
            } catch (InterruptedException e) {

            }
        }
    }

}
