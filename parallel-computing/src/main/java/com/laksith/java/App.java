package com.laksith.java;

import java.util.stream.IntStream;

public class App {

    // static Integer iterations = 100;
    // static Integer iterations = 1000;
    // static Integer iterations = 10000;
    // static Integer iterations = 100000;

    static Integer iterations = Integer.MAX_VALUE;

    public static void main(String[] args) {
        // Threads Model
        // exeThreads();

        // resource manipulation scenario:
        // asyncVariableAccess();

        // Thread safe way: synchronized execute(index)
        // asyncVariableAccess2();

        // handle deadlocks: Deadlock.java

        // working with data types
        byInterStem();
        byInterStemSum();
        byForLoop();
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
        // System.out.println("Value: " + value);
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

    private static void byInterStem() {
        System.err.println("================");
        System.err.println("USING INT STREAM");

        long startTime = System.nanoTime();

        // IntStream contains stream of integer values
        int reduceSum = IntStream
                .range(0, iterations) // starting from 0, to Max value of the stream
                .parallel() // executing parallel way
                .sum(); // get sum one by one, ex: 0+1=1, 1+2=3, ...

        long endTime = System.nanoTime(); // end time of the execution of int stream
        long duration = (endTime - startTime);

        System.out.println("Total is: " + reduceSum);
        System.out.println("Duration is: " + duration);
        System.err.println("===============");
    }

    private static void byInterStemSum() {
        System.err.println("================");
        System.err.println("USING INT STREAM SUM");

        long startTime = System.nanoTime();

        // IntStream contains stream of integer values
        // not using parallel key word
        int reduceSum = IntStream
                .range(0, iterations) // starting from 0, to Max value of the stream
                .sum(); // get sum one by one, ex: 0+1=1, 1+2=3, ...

        long endTime = System.nanoTime(); // end time of the execution of int stream
        long duration = (endTime - startTime);

        System.out.println("Total is: " + reduceSum);
        System.out.println("Duration is: " + duration);
        System.err.println("===============");
    }

    private static void byForLoop() {
        System.err.println("================");
        System.err.println("USING FOR LOOP");

        long startTime = System.nanoTime();

        int sum = 0;

        // using for loop for getting sum of integer values
        for (int i = 0; i < iterations; i++) {
            sum = sum + i;
        }

        long endTime = System.nanoTime(); // end time of the execution of int stream
        long duration = (endTime - startTime);

        System.out.println("Total is: " + sum);
        System.out.println("Duration is: " + duration);
        System.err.println("===============");
    }

    // Output Note: parallel execution is good for larger datasets..
    // takes less amount of time than other executions, such as for loop.
}
