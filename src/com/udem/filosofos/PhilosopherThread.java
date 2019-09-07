package com.udem.filosofos;

import java.util.concurrent.Semaphore;

class PhilosopherThread extends Thread{

    private Semaphore stick1;

    private Semaphore stick2;

    private String philosopherName;

    private boolean isLeft;

    PhilosopherThread(Semaphore stick1, Semaphore stick2, String philosopherName) {
        this.stick1 = stick1;
        this.stick2 = stick2;
        this.philosopherName = philosopherName;
        this.isLeft = false;
    }

    PhilosopherThread(Semaphore stick1, Semaphore stick2, String philosopherName, boolean isLeft) {
        this.stick1 = stick1;
        this.stick2 = stick2;
        this.philosopherName = philosopherName;
        this.isLeft = isLeft;
    }

    @Override
    public void run() {
        try {

            if (isLeft) {
                System.out.println(philosopherName + " is waiting for the second stick.");
                stick2.acquire();
                System.out.println(philosopherName + " gets the second stick.");

                System.out.println(philosopherName + " is waiting for the first stick.");
                stick1.acquire();
                System.out.println(philosopherName + " gets the first stick.");
            } else {
                System.out.println(philosopherName + " is waiting for the first stick.");
                stick1.acquire();
                System.out.println(philosopherName + " gets the first stick.");

                System.out.println(philosopherName + " is waiting for the second stick.");
                stick2.acquire();
                System.out.println(philosopherName + " gets the second stick.");
            }
            System.out.println(philosopherName + " is eating.");
            Thread.sleep(50);
            System.out.println(philosopherName + " stops eating.");
        } catch (InterruptedException exc) {
            System.out.println(exc);
        }

        stick2.release();
        System.out.println(philosopherName + " releases the second stick.");
        stick1.release();
        System.out.println(philosopherName + " releases the first stick.");
    }

}
