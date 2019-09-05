package com.udem.reader_writer;

import java.util.concurrent.Semaphore;

class WriterThread extends Thread {

    private Semaphore writerSemaphore;

    private Semaphore readerSemaphore;

    private String threadName;

    private String value;

    WriterThread(Semaphore writerSemaphore, Semaphore readerSemaphore, String threadName, String value) {
        super(threadName);
        this.writerSemaphore = writerSemaphore;
        this.readerSemaphore = readerSemaphore;
        this.threadName = threadName;
        this.value = value;
    }

    @Override
    public void run() {
        System.out.println("Starting writer " + threadName);
        try {
            System.out.println(threadName + "(Writer) is waiting for a permit.");
            writerSemaphore.acquire();
//            readerSemaphore.acquire();
            System.out.println(threadName + "(Writer) gets a permit.");

            SharedResource.getInstance().addData(this.value);
            System.out.println(threadName + " wrote the data");
            Thread.sleep(50);
        } catch (InterruptedException exc) {
            System.out.println(exc);
        }

        System.out.println(threadName + "(Writer) releases the permit.");
        writerSemaphore.release();
    }
}

