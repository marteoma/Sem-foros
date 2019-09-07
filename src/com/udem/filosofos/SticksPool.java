package com.udem.filosofos;

import java.util.ArrayList;

public class SticksPool {

    private ArrayList<Boolean> sticks;

    private static SticksPool ourInstance = new SticksPool();

    public static SticksPool getInstance() {
        return ourInstance;
    }

    private SticksPool() {
    }

    /**
     * Start the set of sticks with the specified size
     * @param poolSize Size of the pool of sticks
     */
    public void setSize(int poolSize) {
        this.sticks = new ArrayList<>();
        for (int i = 0; i < poolSize; i++) {
            sticks.add(true);
        }
    }

    /**
     * Return whether stick on the specified position is available or not
     * @param position Position of the stick to verify
     * @return State of the specified stick
     */
    public boolean isAvailable(int position) {
        return sticks.get(position);
    }

    /**
     * Changes the value of the indicated element to the specified value
     * @param position Position of the element to change
     * @param state Value to set to the element
     */
    public void update(int position, boolean state) {
        this.sticks.set(position, state);
    }

    /**
     * Return a string representation of an ArrayList of boolean
     * @param data The ArrayList to convert
     * @return The string representation of the data
     */
    private String getHumanReadableData(ArrayList<Boolean> data) {
        StringBuilder humanReadableData = new StringBuilder("{ ");
        for (Boolean value : data) {
            humanReadableData.append(value);
            humanReadableData.append(", ");
        }
        humanReadableData.append("}");
        return humanReadableData.length() == 0 ? "No data yet" : humanReadableData.toString();
    }

    public String getPool() {
        return getHumanReadableData(this.sticks);
    }
}
