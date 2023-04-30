package com.example.project1.fx;

import com.example.project1.logic.Algorithm;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MainUI extends Thread {

    private HBox coinsHB;
    private Stage stage;

    public MainUI(Algorithm algorithm) {
        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: #f1f7ed");
        borderPane.setPadding(new Insets(20, 20, 20, 20));

        coinsHB = new HBox(15);
        coinsHB.setPadding(new Insets(10, 10, 10, 10));
        coinsHB.setAlignment(Pos.CENTER);
        borderPane.setCenter(coinsHB);

        Label label = new Label("       Optimal Game Strategy");
        label.setFont(Font.font("Georgia", FontWeight.BOLD, 24));
        label.setStyle("-fx-text-fill: #54494b");
        label.setAlignment(Pos.CENTER);
        borderPane.setTop(label);

        HBox buttonsHB = new HBox(20);
        buttonsHB.setAlignment(Pos.CENTER);
        buttonsHB.setPadding(new Insets(10, 10, 10, 10));
        borderPane.setBottom(buttonsHB);

        Button animationBtn = new Button("Animation");
        animationBtn.setDisable(true);
        animationBtn.setStyle("-fx-background-color: #91c7b1; -fx-text-fill: White;  -fx-border-width: 2; -fx-border-color: #6a9485");
        animationBtn.setFont(Font.font("Georgia", FontWeight.BOLD, 14));
        animationBtn.setOnAction(e -> {
            try {
                algorithm.start();
            } catch (IllegalThreadStateException e1) {
                e1.getCause();
            }
        });

        Button StartBtn = new Button("Start ");
        StartBtn.setStyle("-fx-background-color: #91c7b1; -fx-text-fill: White;  -fx-border-width: 2; -fx-border-color: #6a9485");
        StartBtn.setFont(Font.font("Georgia", FontWeight.BOLD, 14));
        StartBtn.setOnAction(e -> {
            algorithm.findOptimalGameStrategy();
            Stage stage2 = new Stage();
            TextArea TA = new TextArea(" -> The DP Table: \n"
                    + algorithm.printDPTable()
                    + "\n -> The expected value: " + algorithm.getExpectedValue()
                    + "\n -> The values that give the result: \n" + algorithm.printSequence());
            TA.setPadding(new Insets(10,10,10,10));
            TA.setStyle("-fx-text-fill: #54494b; -fx-background-color: #ffffff; -fx-font-family: Georgia; ");
            TA.setEditable(false);
            stage2.setScene(new Scene(new Pane(TA)));
            stage2.setTitle("DP table and coin sequence");
            stage2.show();
            stage2.setResizable(false);
            animationBtn.setDisable(false);
        });

        Button exitBtn = new Button("Exit");
        exitBtn.setStyle("-fx-background-color: #b33951; -fx-text-fill: White; -fx-border-width: 2; -fx-border-color: #80283b");
        exitBtn.setFont(Font.font("Georgia", FontWeight.BOLD, 14));
        exitBtn.setOnAction(e -> ((Node) e.getSource()).getScene().getWindow().hide());

        buttonsHB.getChildren().addAll(StartBtn, animationBtn, new Label("          "), new Label("          "), exitBtn);

        Scene scene = new Scene(borderPane);
        stage = new Stage();
        stage.setScene(scene);
        stage.setMinWidth(650);
        stage.setMinHeight(500);
        stage.setResizable(true);
        stage.setTitle("Coin Game");
    }

    public Stage getStage() {
        return stage;
    }

    public HBox getCoinsHB() {
        return coinsHB;
    }
}
