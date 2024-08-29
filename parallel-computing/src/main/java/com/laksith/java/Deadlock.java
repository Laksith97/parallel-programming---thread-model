package com.laksith.java;

public class Deadlock {

    public static void main(String[] args) {
        // System.out.println("Deadlock!");
        final String res1 = "res1";
        final String res2 = "res2";

        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                // to lock resource 1: Allocate r1 for thread 1
                // so, t1 owns r1
                synchronized (res1) {
                    System.out.println("T1, Locked on res1");

                    // sleep res1 for 100 milliseconds
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // then try to lock res2
                    // kind of nested resource allocation
                    synchronized (res2) {
                        System.out.println("T1, Locked on res2");
                    }
                }

            }

        });

        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                // to lock resource 2: Allocate r2 for thread 2
                // so, t1 owns r1
                synchronized (res2) {
                    System.out.println("T2, Locked on res2");

                    // sleep res1 for 100 milliseconds
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // then try to lock res2
                    synchronized (res1) {
                        System.out.println("T2, Locked on res1");
                    }

                    // to avoid deadlock situations, should not use nested executions like this
                    // or use some time period for own it
                    // then release the resourse for others

                }

            }

        });

        // start threads
        t1.start();
        t2.start();
    }
}
