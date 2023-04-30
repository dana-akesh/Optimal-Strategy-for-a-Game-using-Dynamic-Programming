package com.example.project1.fx;

import com.example.project1.fx.alerts.ErrorAlert;
import com.example.project1.fx.alerts.SuccessAlert;
import com.example.project1.logic.Algorithm;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class StartupUI implements Runnable {
    public Stage stage;
    private Algorithm algorithm = new Algorithm();
    private MainUI mainUI = new MainUI(algorithm);

    public StartupUI() {

        // main border for the whole screen
        BorderPane mainBorderPane = new BorderPane();
        mainBorderPane.setStyle("-fx-background-color: #f1f7ed");
        mainBorderPane.setPadding(new Insets(20, 20, 20, 20));


        VBox vb = new VBox();
        vb.setAlignment(Pos.CENTER);
        vb.setSpacing(15);
        vb.setPadding(new Insets(30, 30, -20, 30));
        mainBorderPane.setCenter(vb);

        Label title = new Label("       Welcome to the coin game ");
        title.setFont(Font.font("Georgia", FontWeight.BOLD, 24));
        title.setStyle("-fx-text-fill: #54494b");
        title.setAlignment(Pos.CENTER);
        mainBorderPane.setTop(title);

        Label insertLabel = new Label("Please insert the value of the coin: ");
        insertLabel.setFont(Font.font("Georgia", FontWeight.BOLD, 16));
        insertLabel.setStyle("-fx-text-fill: #54494b");

        Label noOfCoinsLabel = new Label("The number of coins is: " + 0);
        noOfCoinsLabel.setFont(Font.font("Georgia", FontWeight.BOLD, 12));
        noOfCoinsLabel.setStyle("-fx-text-fill: #54494b");

        TextField coinValueTF = new TextField();
        coinValueTF.setAlignment(Pos.CENTER);
        coinValueTF.setMaxHeight(70);
        coinValueTF.setMaxWidth(100);

        Button insertBtn = new Button("Insert");
        insertBtn.setStyle("-fx-background-color: #91c7b1; -fx-text-fill: White;  -fx-border-width: 2; -fx-border-color: #6a9485");
        insertBtn.setFont(Font.font("Georgia", FontWeight.BOLD, 14));
        insertBtn.setOnAction(e -> {
            if (coinValueTF.getText().trim().isEmpty()) {
                new ErrorAlert("Value can't be empty");
                return;
            } else if (algorithm.coinsArrayList.size() >= 20) {
                new ErrorAlert("Coins limit reached, please start the game");
                return;
            }
            try {
                int x = Integer.parseInt(coinValueTF.getText().trim());
                Coin c = new Coin(x);
                algorithm.coinsArrayList.add(c);
                noOfCoinsLabel.setText("The number of coins is:" + algorithm.coinsArrayList.size());
                new SuccessAlert("Value added");
                coinValueTF.clear();
            } catch (Exception e1) {
                new ErrorAlert("Value can't be a string");
                e1.printStackTrace();
                coinValueTF.clear();
            }
        });

        HBox buttonsHB = new HBox(200);
        mainBorderPane.setBottom(buttonsHB);

        ImageView coinImg = new ImageView("https://img.icons8.com/arcade/512/coins.png");
        coinImg.setFitHeight(100);
        coinImg.setFitWidth(100);

        // start of the game button
        Button startBtn = new Button("Start →");
        startBtn.setPadding(new Insets(10, 10, 10, 10));
        startBtn.setStyle("-fx-background-color: #b33951; -fx-text-fill: White; -fx-border-width: 2; -fx-border-color: #80283b");
        startBtn.setFont(Font.font("Georgia", FontWeight.BOLD, 14));
        buttonsHB.getChildren().addAll(coinImg, startBtn);

        startBtn.setOnAction(e -> {
            try {
                int size = algorithm.coinsArrayList.size();
                if (size % 2 != 0) {
                    new ErrorAlert("The number of coins can't be Odd!!" + "\n please add another value");
                    return;
                } else if (size < 1) {
                    new ErrorAlert("Please add coins");
                    return;
                }
                // tp close the StartUp UI
                ((Node) e.getSource()).getScene().getWindow().hide();
                run();
                mainUI.stage.show();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        vb.getChildren().addAll(insertLabel, noOfCoinsLabel, coinValueTF, insertBtn);

        Scene scene = new Scene(mainBorderPane);
        stage = new Stage();
        stage.setScene(scene);
        stage.setMinWidth(450);
        stage.setMinHeight(300);
        stage.setResizable(false);
        stage.setTitle("Coin Game");
    }

    // to create coins in the mainUI while being in the StartupUI
    @Override
    public void run() {
        for (int i = 0; i < algorithm.coinsArrayList.size(); i++) {
            mainUI.coinsHB.getChildren().add(algorithm.coinsArrayList.get(i));
        }
    }
}
