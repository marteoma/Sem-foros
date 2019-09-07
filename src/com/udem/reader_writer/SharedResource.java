package com.udem.reader_writer;

import java.util.ArrayList;

class SharedResource {
    private static SharedResource ourInstance = new SharedResource();

    static SharedResource getInstance() {
        return ourInstance;
    }

    private int readers;

    private ArrayList<String> data;

    private SharedResource() {
        this.readers = 0;
        this.data = new ArrayList<>();
    }

    /**
     * Return the total number of readers
     * @return Number of current readers
     */
    int getReaders() {
        return readers;
    }

    /**
     * Increment the total number of readers
     */
    void incrementReaders() {
        this.readers++;
    }

    /**
     * Decrement the total number of readers
     */
    void decrementReaders() {
        this.readers--;
    }

    /**
     * Return the data in a string way
     * @return The data as a string
     */
    String getData() {
        return getHumanReadableData(this.data);
    }

    /**
     * Add a new value to the data
     * @param value The new value to add
     */
    void addData(String value) {
        this.data.add(value);
    }

    /**
     * Return a string representation of an ArrayList of strings
     * @param data The ArrayList to convert
     * @return The string representation of the data
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
