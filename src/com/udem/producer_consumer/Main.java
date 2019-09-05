package com.udem.producer_consumer;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String args[]) throws InterruptedException {

        Semaphore sem = new Semaphore(1);

        ProducerThread t1 = new ProducerThread(sem, "A", "Hello, what is it going");
        ConsumerThread t2 = new ConsumerThread(sem, "B");
        ConsumerThread t3 = new ConsumerThread(sem, "C");

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();
    }
}
