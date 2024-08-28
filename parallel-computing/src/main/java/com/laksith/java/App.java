package com.laksith.java;

public class App {
    public static void main(String[] args) {
        // Threads Model
        // exeThreads();

        // resource manipulation scenario:
        //asyncVariableAccess();

        // Thread safe way: synchronized execute(index)
        asyncVariableAccess2();
    }

    private static double value = 0; // static: this is a class variable, anyone within this application can access
                                     // it.

    private static void asyncVariableAccess() {
        for (int i = 0; i < 10; i++) {
            final int index = i;

            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    double random = Math.random() * 2000; // x2 delay, to change the execution time

                    try {
                        Thread.sleep((long) random);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    value = index + 1;
                    System.out.println("Current Thread id: " + Thread.currentThread().getId() + ", Value: " + value);
                }
            };

            new Thread(runnable).start();
        }
        System.out.println("Value: " + value);
    }


    private static void asyncVariableAccess2() {
        for (int i = 0; i < 10; i++) {
            final int index = i;

            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    execute(index);
                }
            };

            new Thread(runnable).start();
        }
        //System.out.println("Value: " + value);
    }

    private static synchronized void execute(double index) {
        System.out.println("Thread is starting: " + index);

        double sleep = Math.random() * 2000; // x2 delay

        try {
            Thread.sleep((long) sleep);
            System.err.println("Sleep time: " + sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        value = index + 1;
        
        System.out.println("Current Thread id: " + Thread.currentThread().getId() + ", Value: " + value);
    }


    private static void exeThreads() {
        for (int i = 0; i < 10; i++) {
            final int index = i;
            // new Thread(new Runnable() {
            // public void run() {
            // System.out.println("Current Thread id: " + Thread.currentThread().getId());
            // }
            // }).start();

            // Runnable runnable = new Runnable() {
            // @Override
            // public void run() {
            // System.out.println("Current Thread id: " + Thread.currentThread().getId() +
            // ", Index: " + index);
            // }
            // };
            // new Thread(runnable).start();

            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    // double random = Math.random() * 1000;
                    double random = Math.random() * 3000; // x3 delay

                    try {
                        Thread.sleep((long) random);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("Current Thread id: " + Thread.currentThread().getId() + ", Index: " + index);
                }
            };

            new Thread(runnable).start();
        }
    }
}
