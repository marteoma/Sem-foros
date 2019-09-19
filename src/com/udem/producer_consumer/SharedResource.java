/**
 * Representación del buffer
 * Se simula utilizando una cola de Strings para cada dato
 * Está diseñado como un Singleton para que este sea Global
 */
package com.udem.producer_consumer;

import java.util.ArrayDeque;
import java.util.Queue;

class SharedResource {
    private static SharedResource ourInstance = new SharedResource();

    static SharedResource getInstance() {
        return ourInstance;
    }

    // Cola de datos
    private Queue<String> buffer;

    // Tamaño de la cola
    private int size;

    private SharedResource() {
        this.size = 10;
        this.buffer = new ArrayDeque<>(this.size);
    }

    /**
     * Get the data buffer in string way
     * @return String representation of the buffer
     */
    String getBuffer() {
        return getHumanReadableData(this.buffer);
    }

    /**
     * Encola un elemento
     * @param value Elemento a añadir
     */
    void addValue(String value) {
        this.buffer.offer(value);
    }

    /**
     * Toma el elemento encolado y lo retorna
     * @return Siguiente item según FIFO
     */
    String getValue() {
        return this.buffer.poll();
    }

    /**
     * Si la cola está llena o no
     * @return True si está llena
     */
    boolean isFull() {
        return this.buffer.size() == this.size;
    }

    /**
     * Si la cola está vacía o no
     * @return True si está vacía
     */
    boolean isEmpty() {
        return this.buffer.isEmpty();
    }

    /**
     * Representación en String de la cola
     * @param data Cola a convertir
     * @return Representación de la cola como cadena
     */
    private String getHumanReadableData(Queue<String> data) {
        StringBuilder humanReadableData = new StringBuilder("\n");
        for (String value : data) {
            humanReadableData.append(value);
            humanReadableData.append("\n");
        }
        return humanReadableData.length() == 0 ? "No data yet" : humanReadableData.toString();
    }
}
