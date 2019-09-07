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

    /**
     * Get the data buffer in string way
     * @return String representation of the buffer
     */
    String getBuffer() {
        return getHumanReadableData(this.buffer);
    }

    /**
     * Enqueue one element
     * @param value The new element to add to the Queue
     */
    void addValue(String value) {
        this.buffer.offer(value);
    }

    /**
     * Takes the enqueued item and return it
     * @return The next item to get by FIFO logic
     */
    String getValue() {
        return this.buffer.poll();
    }

    /**
     * Return whether the Queue is full or not
     * @return True if the Queue is full, False otherwise
     */
    boolean isFull() {
        return this.buffer.size() == this.size;
    }

    /**
     * Return whether the Queue is empty or not
     * @return True if the Queue is empty (zero elements), False otherwise
     */
    boolean isEmpty() {
        return this.buffer.isEmpty();
    }

    /**
     * Return a string representation of a Queue of strings
     * @param data The Queue to convert
     * @return The string representation of the data
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
