package com.example.project1.logic;

// an object to represents the first and second person choices
// in each turn
public class Data {
    int first, second;
    int pick = 0; // is the index to get the value
    public String toString(){
        return first + " " + second + " " + pick;
    }
}
