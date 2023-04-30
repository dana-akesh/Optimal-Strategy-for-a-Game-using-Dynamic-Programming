package com.example.project1.fx.alerts;

import javafx.scene.control.Alert;

public class ConfirmationAlert {
    public ConfirmationAlert(String message) {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setResizable(true);
        a.setContentText(message);
        a.show();
    }
}
