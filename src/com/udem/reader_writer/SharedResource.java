/**
 * Representación del recurso que se escribe y se lee
 * En el mundo real sería una base de datos, un archivo...
 * Es un singleton para tener acceso global unificado
 */
package com.udem.reader_writer;

import java.util.ArrayList;

class SharedResource {
    private static SharedResource ourInstance = new SharedResource();

    static SharedResource getInstance() {
        return ourInstance;
    }

    // Cantidad actual de lectores
    private int readers;

    private ArrayList<String> data;

    private SharedResource() {
        this.readers = 0;
        this.data = new ArrayList<>();
    }

    /**
     * Número total de lectores
     * @return Number of current readers
     */
    int getReaders() {
        return readers;
    }

    /**
     * Aumenta los lectores
     */
    void incrementReaders() {
        this.readers++;
    }

    /**
     * Disminuye los lectores
     */
    void decrementReaders() {
        this.readers--;
    }

    /**
     * Representación de los datos como cadena
     * @return Datos como cadena
     */
    String getData() {
        return getHumanReadableData(this.data);
    }

    /**
     * Escribir valor
     * @param value Valor a escribir
     */
    void addData(String value) {
        this.data.add(value);
    }

    /**
     * Convierte el arreglo a una representación en cadena
     * @param data Arreglo
     * @return Representación
     */
    private String getHumanReadableData(ArrayList<String> data) {
        StringBuilder humanReadableData = new StringBuilder("\n");
        for (String value : data) {
            humanReadableData.append(value);
            humanReadableData.append("\n");
        }
        return humanReadableData.length() == 0 ? "No data yet" : humanReadableData.toString();
    }
}
