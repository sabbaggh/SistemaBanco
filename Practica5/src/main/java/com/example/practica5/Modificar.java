package com.example.practica5;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Window;

import java.util.ArrayList;

public class Modificar implements HelloController {

    private final GridPane root;


    public Modificar() {

        GridPane gridPane = new GridPane();
        gridPane = modificar();
        contenido(gridPane);

        root = gridPane;

    }

    private GridPane modificar() {
        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-background-color: LIGHTBLUE");
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(40, 40, 40, 40));
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        return gridPane;
    }

    private void contenido(GridPane gridPane) {
        Styles estilos = new Styles();
        Label titulo = new Label("Modificar Datos");
        titulo.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 24));
        gridPane.add(titulo, 0, 0, 2, 1);
        GridPane.setHalignment(titulo, HPos.CENTER);
        GridPane.setMargin(titulo, new Insets(20, 0, 20, 0));
        Label id = new Label("Ingresa la id del cliente a modificar sus datos");
        gridPane.add(id, 0, 1, 2, 1);
        TextField idIn = new TextField();
        gridPane.add(idIn, 0, 2, 2, 1);

        Button confirmar = new Button("Confirmar");
        confirmar.setPrefSize(100, 30);
        gridPane.add(confirmar, 1, 3);

        Button cancelar = new Button("Volver");
        cancelar.setPrefSize(100, 30);
        gridPane.add(cancelar, 0, 3);

        estilos.setStyleLabelGrande(id);
        estilos.setStyleBoton(cancelar, confirmar);

        cancelar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Escena1 menu = new Escena1();
                cancelar.getScene().setRoot(menu.getContent());
            }
        });

        confirmar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    int id = Integer.parseInt(idIn.getText());
                } catch (NumberFormatException nfe) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "ERROR!", "Ingrese un ID valido");
                }
                int id = Integer.parseInt(idIn.getText());
                Registros registros = new Registros();
                int x = 0;
                ArrayList<Cliente> listaC = registros.getListaC();
                for (int i = 0; i < listaC.size(); i++) {
                    if (id == listaC.get(i).getId()) {
                        AlmacenarID id1 = new AlmacenarID();
                        id1.setId(i);
                        x = 1;
                    }
                }
                if (x == 0) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "ERROR!", "No existe ningun usuario con el ID ingresado");
                    return;
                }
                else{
                    Modificacion modi = new Modificacion();
                    confirmar.getScene().setRoot(modi.getContent());
                }
            }
        });

    }


    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    @Override
    public Parent getContent() {
        return root;
    }
}

