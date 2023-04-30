package com.example.project1.logic;

// an object to represents the first and second person choices
// in each turn
public class Data {
    private int first, second;
    private int pick = 0; // is the index to get the value

    public void setFirst(int first) {
        this.first = first;
    }

    public int getFirst() {
        return first;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public int getSecond() {
        return second;
    }

    public void setPick(int pick) {
        this.pick = pick;
    }

    public int getPick() {
        return pick;
    }

    public String toString() {
        return first + " " + second + " " + pick;
    }

}
