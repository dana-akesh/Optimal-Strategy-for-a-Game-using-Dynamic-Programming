package com.example.project1.logic;

import com.example.project1.fx.Coin;

import java.util.ArrayList;

public class Algorithm extends Thread {
    private static Data[][] moves;
    public ArrayList<Coin> coinsArrayList = new ArrayList<>();
    private int expectedValue;
    private int[] indexes;

    // to calculate the dynamic 2D array and get the solution
    // T(n + n + n + n^2) = O(n^2)
    public void findOptimalGameStrategy() {

        // initializing the 2D array of type data which is:
        // integer first, second
        // int pick = 0; // is the index to get the value
        moves = new Data[coinsArrayList.size()][coinsArrayList.size()];

        // putting in each array cell the type data
        // O(n)
        for (int i = 0; i < moves.length; i++) {
            for (int j = 0; j < moves[i].length; j++) {
                moves[i][j] = new Data();
            }
        }

        // fills up the first diagonal which only has one coin to pick up from
        // i = j
        // O(n)
        for (int i = 0; i < coinsArrayList.size(); i++) {
            moves[i][i].first = coinsArrayList.get(i).getValue();
            //to track the sequence of moves
            moves[i][i].pick = i;
        }

        // starts filling up the 2 diagonal of the 2D array (Counting from 1)
        // O(n^2)
        for (int l = 2; l <= coinsArrayList.size(); l++) {
            // filling up the array in with ignoring the j > i
            for (int i = 0; i <= coinsArrayList.size() - l; i++) {
                int j = i + l - 1;
                if (coinsArrayList.get(i).getValue() + moves[i + 1][j].second > moves[i][j - 1].second + coinsArrayList.get(j).getValue()) {
                    moves[i][j].first = coinsArrayList.get(i).getValue() + moves[i + 1][j].second;
                    moves[i][j].second = moves[i + 1][j].first;
                    moves[i][j].pick = i;
                } else {
                    moves[i][j].first = coinsArrayList.get(j).getValue() + moves[i][j - 1].second;
                    moves[i][j].second = moves[i][j - 1].first;
                    moves[i][j].pick = j;
                }
            }
        }
        expectedValue = moves[0][moves.length - 1].first;
    }

    // prints the sequence of values and indexes
    // O(n)
    public String printSequence() {
        StringBuilder s = new StringBuilder();
        indexes = new int[coinsArrayList.size()];
        int i = 0;
        int j = coinsArrayList.size() - 1;
        int step;
        for (int k = 0; k < coinsArrayList.size(); k++) {
            step = moves[i][j].pick;
            //this is the value of pick and its index
            s.append(" Coin value: ").append(coinsArrayList.get(step).getValue()).append(" ").append("index in Array: ").append(step).append("\n");
            indexes[k] = step;
            if (step <= i) {
                i ++;
            } else {
                j --;
            }
        }
        return s.toString();
    }

    // returns the DP table which is the solution
    // O(n^2)
    public String printDPTable() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < moves.length; i++) {
            for (int j = 0; j < moves[i].length; j++) {
                s.append(moves[i][j]).append("  ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    public int getExpectedValue() {
        return expectedValue;
    }

    // to do the animation of the coins in the MainUI interface.
    @Override
    public void run() {
        for (int i = 0; i < coinsArrayList.size(); i++) {
            if (i % 2 == 0) {
                coinsArrayList.get(indexes[i]).setStyle("-fx-background-color: #3da6ff; -fx-text-fill: White; -fx-font-weight: BOLD; -fx-border-radius: 2; -fx-border-color: #145a96");
            } else {
                coinsArrayList.get(indexes[i]).setStyle("-fx-background-color: #cfd255; -fx-text-fill: White; -fx-font-weight: BOLD; -fx-border-radius: 2; -fx-border-color: #837307");
            }
            try {
                Thread.sleep(1400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
