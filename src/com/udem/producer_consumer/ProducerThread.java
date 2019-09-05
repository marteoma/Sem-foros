package com.udem.producer_consumer;

import java.util.concurrent.Semaphore;

class ProducerThread extends Thread {

    private Semaphore semaphore;

    private String threadName;

    private String value;

    ProducerThread(Semaphore semaphore, String threadName, String value) {
        super(threadName);
        this.semaphore = semaphore;
        this.threadName = threadName;
        this.value = value;
    }

    @Override
    public void run() {
        System.out.println("Starting producer " + threadName);
        try {
            System.out.println(threadName + "(Producer) is waiting for a permit.");
            semaphore.acquire();
            System.out.println(threadName + "(Producer) gets a permit.");

            Thread.sleep(50);

            if (!SharedResource.getInstance().isFull()) {
                SharedResource.getInstance().addValue(value);
                System.out.println(threadName + "(Producer) produced a new value");
            } else {
                System.out.println("Buffer is full");
            }
        } catch (InterruptedException exc) {
            System.out.println(exc);
        }

        System.out.println(threadName + " releases the permit.");
        semaphore.release();
    }
}

