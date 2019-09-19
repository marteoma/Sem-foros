/**
 * Solución al problema de los filósofos para Sistemas Operativos de la Universida de Medellín
 * Semestre 2019 - 2
 * Realizado por Mateo Arboleda Roldán
 */

package com.udem.filosofos;

import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        // Instancia de semáforos, cada semáforo es un palo
        Semaphore stick1 = new Semaphore(1);
        Semaphore stick2 = new Semaphore(1);
        Semaphore stick3 = new Semaphore(1);
        Semaphore stick4 = new Semaphore(1);
        Semaphore stick5 = new Semaphore(1);

        // Instancia de los filósofos
        PhilosopherThread t1 = new PhilosopherThread(stick1, stick5, "A");
        PhilosopherThread t2 = new PhilosopherThread(stick1, stick2, "B");
        PhilosopherThread t3 = new PhilosopherThread(stick2, stick3, "C");
        PhilosopherThread t4 = new PhilosopherThread(stick3, stick4, "D");

        // Este filósofo empieza por la izquierda
        PhilosopherThread t5 = new PhilosopherThread(stick4, stick5, "E", true);

        // Inicio de los procesos
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        // Espera a que finalicen
        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();
    }
}
