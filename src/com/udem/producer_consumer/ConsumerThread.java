package com.udem.producer_consumer;

import java.util.concurrent.Semaphore;

class ConsumerThread extends Thread {

    private Semaphore semaphore;

    private String threadName;

    ConsumerThread(Semaphore semaphore, String threadName) {
        super(threadName);
        this.semaphore = semaphore;
        this.threadName = threadName;
    }

    @Override
    public void run() {
        System.out.println("Starting consumer " + threadName);
        try {
            System.out.println(threadName + "(Consumer) is waiting for a permit.");
            semaphore.acquire();
            System.out.println(threadName + "(Consumer) gets a permit.");

            Thread.sleep(50);

            if (!SharedResource.getInstance().isEmpty()) {
                String readData = SharedResource.getInstance().getBuffer();
                System.out.println(threadName + "(Consumer) has got: " + readData);
            } else {
                System.out.println("The buffer is empty");
            }
        } catch (InterruptedException exc) {
            System.out.println(exc);
        }

        System.out.println(threadName + " releases the permit.");
        semaphore.release();
    }
}

