package com.laksith.java;

public class Deadlock {
    public static void main(String[] args) {
        //System.out.println("Deadlock!");

        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                
                throw new UnsupportedOperationException("Unimplemented method 'run'");
            }
            
        })
    }
}
