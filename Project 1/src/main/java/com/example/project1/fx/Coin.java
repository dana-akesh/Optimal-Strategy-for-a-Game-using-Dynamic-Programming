package com.example.project1.fx;

import javafx.scene.control.Button;
import javafx.scene.shape.Circle;

// Coin object to represent each coin the user inputs,
// so it can be implemented in the interface
public class Coin extends Button {
    // the value of each coin
    private final int value;

    // creates a coin ui with a value that is given
    public Coin(int value) {
        this.value = value;
        setStyle("-fx-background-color: White; -fx-text-fill: Black; -fx-font-weight: BOLD; -fx-border-width: 1.5; -fx-border-color:  black");
        setShape(new Circle(5));
        setDisable(true);
        setText(value + "");
    }

    public int getValue() {
        return value;
    }

}
