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

public class Depositar implements HelloController{
    private final GridPane root;


    public Depositar(){

        GridPane gridPane = new GridPane();
        gridPane = consultaSaldo();
        contenido(gridPane);

        root = gridPane;

    }

    private GridPane consultaSaldo(){
        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-background-color: LIGHTBLUE");
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(40, 40, 40, 40));
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        return gridPane;
    }

    private void contenido(GridPane gridPane){
        Styles estilos = new Styles();
        Label titulo = new Label("Deposito");
        titulo.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 24));
        gridPane.add(titulo, 0,0,2,1);
        GridPane.setHalignment(titulo, HPos.CENTER);
        GridPane.setMargin(titulo, new Insets(20, 0,20,0));
        Label id = new Label("Ingresa la id del cliente a depositar dinero");
        gridPane.add(id,0,1,2,1);
        TextField idIn = new TextField();
        gridPane.add(idIn,0,2,2,1);

        Label deposito = new Label("Ingrese la cantidad del deposito");
        gridPane.add(deposito,0,3,2,1);
        TextField depositoIn = new TextField();
        gridPane.add(depositoIn,0,4,2,1);

        Button confirmar = new Button("Confirmar");
        confirmar.setPrefSize(100,30);
        gridPane.add(confirmar,1,5);

        Button cancelar = new Button("Volver");
        cancelar.setPrefSize(100,30);
        gridPane.add(cancelar,0,5);

        estilos.setStyleLabelGrande(id,deposito);
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
                try{
                    int id = Integer.parseInt(idIn.getText());
                } catch (NumberFormatException nfe){
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "ERROR!", "Ingrese un ID valido");
                }
                int id =  Integer.parseInt(idIn.getText());
                try{
                    float saldo = Float.valueOf(depositoIn.getText());
                } catch (NumberFormatException nfe){
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "ERROR!", "El formato de deposito  ingresado no es valido");
                }
                float saldo = Float.valueOf(depositoIn.getText());
                if(saldo<=0){
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "ERROR!", "El deposito no puede ser negativo o igual a 0");
                    return;
                }
                if(idIn.getText().isEmpty() || depositoIn.getText().isEmpty()){
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "ERROR!", "No se puede dejar ningun dato en blanco");
                    return;
                }
                Registros registros = new Registros();
                int x = 0;
                ArrayList<Cliente> listaC = registros.getListaC();
                for(int i = 0; i<listaC.size(); i++){
                    if(id == listaC.get(i).getId()){
                        float depositacion = saldo+listaC.get(i).getSaldo();
                        showAlert(Alert.AlertType.CONFIRMATION, gridPane.getScene().getWindow(), "Deposito", "Se han depositado" +saldo+" al usuario "
                                + listaC.get(i).getId() + "\n Su nuevo saldo es $" +depositacion);
                        Cliente cliente1 = new Cliente(listaC.get(i).getId(), listaC.get(i).getNombre(), listaC.get(i).getApellidos(),
                                listaC.get(i).getNoCuenta(),listaC.get(i).getDireccion(), listaC.get(i).getFechaNac(), depositacion);
                        listaC.set(i, cliente1);
                        registros.setListaC(listaC);
                        x = 1;
                    }
                }
                if (x == 0){
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "ERROR!", "No existe ningun usuario con el ID ingresado");
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
