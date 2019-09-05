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

    int getReaders() {
        return readers;
    }

    void incrementReaders() {
        this.readers++;
    }

    void decrementReaders() {
        this.readers--;
    }

    String getData() {
        return getHumanReadableData(this.data);
    }

    void addData(String value) {
        this.data.add(value);
    }

    private String getHumanReadableData(ArrayList<String> data) {
        StringBuilder humanReadableData = new StringBuilder("\n");
        for (String value : data) {
            humanReadableData.append(value);
            humanReadableData.append("\n");
        }
        return humanReadableData.length() == 0 ? "No data yet" : humanReadableData.toString();
    }
}
