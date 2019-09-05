package com.udem.reader_writer;

import java.util.concurrent.*;

public class Main {
    public static void main(String args[]) throws InterruptedException {

        Semaphore writer = new Semaphore(1);
        Semaphore reader = new Semaphore(1);

        WriterThread t1 = new WriterThread(writer, reader, "A", "Hello, what is it going");
        ReaderThread t2 = new ReaderThread(writer, reader, "B");
        ReaderThread t3 = new ReaderThread(writer, reader, "C");
        WriterThread t4 = new WriterThread(writer, reader, "D", "Another message here");

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();

        System.out.println("Final readers: " + SharedResource.getInstance().getReaders());
        System.out.println("Final data: " + SharedResource.getInstance().getData());
    }
}
