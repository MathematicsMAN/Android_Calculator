package com.example.android_calculator;

import java.io.Serializable;

public class Counters implements Serializable {
    private int counter1;
    private int counter2;

    public Counters() {
        this.counter1 = 0;
        this.counter2 = 0;
    }

    public int getCounter1() {
        return counter1;
    }

    public int getCounter2() {
        return counter2;
    }

    public void incrementCounter1() {
        this.counter1 ++;
    }

    public void incrementCounter2() {
        this.counter2 ++;
    }
}
