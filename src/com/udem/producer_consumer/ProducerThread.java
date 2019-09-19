/**
 * Representación del hilo de productor
 */
package com.udem.producer_consumer;

import java.util.concurrent.Semaphore;

class ProducerThread extends Thread {

    // Semáforo de acceso
    private Semaphore semaphore;

    // Nombre del hilo
    private String threadName;

    // Valor que el productor meterá al buffer
    private String value;

    /**
     * Instanciación del Productor
     * @param semaphore Semáforo, debe ser global
     * @param threadName Nombre del hilo
     * @param value Valor que se ingresará al buffer
     */
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

            // Comportamiento diferente si está lleno el buffer o no
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

