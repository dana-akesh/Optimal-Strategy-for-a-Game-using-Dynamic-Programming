package com.example.project1.fx.alerts;

import javafx.scene.control.Alert;

public class SuccessAlert {
    public SuccessAlert(String message) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText(message);
        a.show();
    }
}
