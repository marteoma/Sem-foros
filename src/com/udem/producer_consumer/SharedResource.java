package com.udem.producer_consumer;

import java.util.ArrayDeque;
import java.util.Queue;

class SharedResource {
    private static SharedResource ourInstance = new SharedResource();

    static SharedResource getInstance() {
        return ourInstance;
    }

    private Queue<String> buffer;

    private int size;

    private SharedResource() {
        this.size = 10;
        this.buffer = new ArrayDeque<>(this.size);
    }

    String getBuffer() {
        return getHumanReadableData(this.buffer);
    }

    void addValue(String value) {
        this.buffer.offer(value);
    }

    String getValue() {
        return this.buffer.poll();
    }

    boolean isFull() {
        return this.buffer.size() == this.size;
    }

    boolean isEmpty() {
        return this.buffer.isEmpty();
    }

    private String getHumanReadableData(Queue<String> data) {
        StringBuilder humanReadableData = new StringBuilder("\n");
        for (String value : data) {
            humanReadableData.append(value);
            humanReadableData.append("\n");
        }
        return humanReadableData.length() == 0 ? "No data yet" : humanReadableData.toString();
    }
}
