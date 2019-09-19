/**
 * Solución al problema de lectores y escritores para Sistemas Operativos de la Universida de Medellín
 * Semestre 2019 - 2
 * Realizado por Mateo Arboleda Roldán
 */

package com.udem.reader_writer;

import java.util.concurrent.*;

public class Main {
    public static void main(String args[]) throws InterruptedException {

        // Semáforos para acceder a los datos y escribir
        Semaphore writer = new Semaphore(1);
        // Semáforo para leer
        Semaphore reader = new Semaphore(1);

        // Instancia de lectores y escritores
        WriterThread t1 = new WriterThread(writer, reader, "A", "Hello, what is it going");
        ReaderThread t2 = new ReaderThread(writer, reader, "B");
        ReaderThread t3 = new ReaderThread(writer, reader, "C");
        WriterThread t4 = new WriterThread(writer, reader, "D", "Another message here");

        // Inicio de procesos
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        // Espera fin de procesos
        t1.join();
        t2.join();
        t3.join();
        t4.join();

        System.out.println("Final readers: " + SharedResource.getInstance().getReaders());
        System.out.println("Final data: " + SharedResource.getInstance().getData());
    }
}
