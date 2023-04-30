package com.example.project1;

import com.example.project1.fx.StartupUI;
import com.example.project1.fx.alerts.ErrorAlert;
import javafx.application.Application;
import javafx.stage.Stage;

public class Driver extends Application {

    public static void main(String[] args) {
        try {
            Application.launch();
        } catch (Exception e) {
            new ErrorAlert(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage stage) {
        StartupUI startupUI = new StartupUI();
        startupUI.stage.show();
    }

}