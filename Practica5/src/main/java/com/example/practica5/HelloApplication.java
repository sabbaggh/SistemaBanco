package com.example.practica5;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        //Se manda a llamar a las escena 1
        stage.setTitle("Sistema clientes");
        stage.setWidth(900);
        stage.setHeight(650);
        stage.setResizable(false);
        Escena1 escena1 = new Escena1();
        Scene scene1 = new Scene(escena1.getContent(), Color.BEIGE);
        stage.setScene(scene1);
        stage.show();
    }

    public static void main(String[] args) {

        launch(args);
    }

}