package com.example.practica5;

import javafx.scene.Node;

public class Styles {

    void setStyleBoton(Node...nodes){
        for(Node node : nodes){
            node.setStyle("-fx-font: 14px\"Gill Sans MT\"; -fx-padding: 5;");
        }
    }
    void setStyleLabel(Node...nodes){
        for(Node node : nodes){
            node.setStyle("-fx-font: 14px\"Gill Sans MT\";");
        }
    }
    void setStyleLabelGrande(Node...nodes){
        for(Node node : nodes){
            node.setStyle("-fx-font: 18px\"Gill Sans MT\";");
        }
    }
}
