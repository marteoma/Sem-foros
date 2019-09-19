/**
 * Solución al problema de Productor-Consumidor para Sistemas Operativos de la Universida de Medellín
 * Semestre 2019 - 2
 * Realizado por Mateo Arboleda Roldán
 */

package com.udem.producer_consumer;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String args[]) throws InterruptedException {

        // Semáforo para controlar el accesos al buffer de datos
        Semaphore sem = new Semaphore(1);

        // Instancia de los productores y consumidores a usar
        ProducerThread t1 = new ProducerThread(sem, "A", "Hello, what is it going");
        ConsumerThread t2 = new ConsumerThread(sem, "B");
        ConsumerThread t3 = new ConsumerThread(sem, "C");

        // Inicio de los hilos
        t1.start();
        t2.start();
        t3.start();

        // Esperar a que los hilos terminen
        t1.join();
        t2.join();
        t3.join();
    }
}
