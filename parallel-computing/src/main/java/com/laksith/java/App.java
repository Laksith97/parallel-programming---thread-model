package com.laksith.java;

public class App {
    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            final int index = i;
            // new Thread(new Runnable() {
            //     public void run() {
            //         System.out.println("Current Thread id: " + Thread.currentThread().getId());
            //     }
            // }).start();
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    System.out.println("Current Thread id: " + Thread.currentThread().getId() + ", Index: " + index);
                }
            };
            new Thread(runnable).start();
        }
    }
}
