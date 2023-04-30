package com.example.project1.fx.alerts;

import javafx.scene.control.Alert;

public class ErrorAlert {

    public ErrorAlert(String message) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setContentText(message);
        a.show();
    }
}
