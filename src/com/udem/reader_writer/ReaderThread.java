/**
 * Representación del lector
 */
package com.udem.reader_writer;

import java.util.concurrent.Semaphore;

class ReaderThread extends Thread {

    // Semáforo que controla escritura
    private Semaphore writerSemaphore;

    // Semáforo que controla lectura
    private Semaphore readerSemaphore;

    // Nombre del hilo
    private String threadName;

    ReaderThread(Semaphore writerSemaphore, Semaphore readerSemaphore, String threadName) {
        super(threadName);
        this.writerSemaphore = writerSemaphore;
        this.threadName = threadName;
        this.readerSemaphore = readerSemaphore;
    }

    @Override
    public void run() {
        System.out.println("Starting reader " + threadName);
        try {
            System.out.println(threadName + "(Reader) is waiting for a permit.");
            readerSemaphore.acquire();
            System.out.println(threadName + "(Reader) gets a permit over counter.");

            // Aumenta la cantidad de lectores
            SharedResource.getInstance().incrementReaders();
            System.out.println("Readers: " + SharedResource.getInstance().getReaders() + '\n');

            // Cuando hay un solo lector se apodera de la escritura
            if (SharedResource.getInstance().getReaders() == 1) {
                writerSemaphore.acquire();
                System.out.println(threadName + "(Reader) gets a permit over data.");
            }
            readerSemaphore.release();

            // Leer los datos
            String readData = SharedResource.getInstance().getData();
            System.out.println("Read data " + readData);
            Thread.sleep(50);

            readerSemaphore.acquire();

            // Antes de salir se disminuye el número de lectores
            SharedResource.getInstance().decrementReaders();
            if (SharedResource.getInstance().getReaders() == 0) {
                System.out.println(threadName + "(Reader) releases the permit over data.");
                writerSemaphore.release();
            }
        } catch (InterruptedException exc) {
            System.out.println(exc);
        }

        System.out.println(threadName + "(Reader) releases the permit.");
        readerSemaphore.release();
    }
}

