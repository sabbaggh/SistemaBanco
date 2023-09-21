package com.example.practica5;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.Parent;
import javafx.stage.Window;

import java.util.ArrayList;

public class  Escena2 implements HelloController{
    private final GridPane root;
    //Se ponen todos los datos para
    public Escena2(){

        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-background-color: LIGHTBLUE");
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(40, 40, 40, 40));
        gridPane.setHgap(20);
        gridPane.setVgap(20);

        Label titulo = new Label("Ingresa Los Datos Del Cliente");
        titulo.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 24));
        gridPane.add(titulo, 0,0,2,1);
        GridPane.setHalignment(titulo, HPos.CENTER);
        GridPane.setMargin(titulo, new Insets(20, 0,20,0));

        Label nombre = new Label("Nombre: ");
        nombre.setPrefHeight(30);
        gridPane.add(nombre, 0,1);
        TextField ingresoNombre = new TextField();
        ingresoNombre.setPrefHeight(30);
        gridPane.add(ingresoNombre,1,1);

        Label apellido = new Label("Apellidos: ");
        apellido.setPrefHeight(30);
        gridPane.add(apellido, 0,2);
        TextField ingresoap = new TextField();
        ingresoap.setPrefHeight(30);
        gridPane.add(ingresoap,1,2);

        Label fechaNac = new Label("Año De Nacimiento: ");
        fechaNac.setPrefHeight(30);
        gridPane.add(fechaNac, 0,3);
        TextField ingresofec = new TextField();
        ingresofec.setPrefHeight(30);
        gridPane.add(ingresofec,1,3);

        Label direccion = new Label("Direccion: ");
        direccion.setPrefHeight(30);
        gridPane.add(direccion, 0,4);
        TextField ingresodir = new TextField("Ingresa direccion");
        ingresodir.setPrefHeight(30);
        gridPane.add(ingresodir,1,4);

        Label saldoInicial = new Label("Saldo Inicial: ");
        saldoInicial.setPrefHeight(30);
        gridPane.add(saldoInicial, 0,5);
        TextField ingresoSal = new TextField();
        ingresoSal.setPrefHeight(30);
        gridPane.add(ingresoSal,1,5);

        Button confirmar = new Button("Confirmar");
        confirmar.setPrefSize(100,30);
        gridPane.add(confirmar,1,6);

        Button regresar = new Button("Volver");
        regresar.setPrefSize(100,30);
        gridPane.add(regresar,0,6);

        Styles estilo = new Styles();
        estilo.setStyleLabel(nombre, apellido, fechaNac, direccion, saldoInicial);
        estilo.setStyleBoton(regresar, confirmar);


        regresar.setOnMouseClicked(event->{
            Escena1 menu = new Escena1();
            regresar.getScene().setRoot(menu.getContent());
        });

        confirmar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent mostrarDatos) {
                try{
                    int fecha = Integer.parseInt(ingresofec.getText());
                } catch (NumberFormatException nfe){
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "ERROR!", "El formato de año de nacimiento ingresado no es valido");
                }
                int fecha = Integer.parseInt(ingresofec.getText());
                try{
                    float saldo = Float.valueOf(ingresoSal.getText());
                } catch (NumberFormatException nfe){
                        showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "ERROR!", "El formato de saldo  ingresado no es valido");
                }
                float saldo = Float.valueOf(ingresoSal.getText());

                if(2023-fecha <18){
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "ERROR!", "Se necesita ser mayor de edad para poder registrarse");
                    return;
                }
                if(2023-fecha >100){
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "ERROR!", "Ingrese un año de nacimiento valido");
                    return;
                }
                if(saldo < 0){
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "ERROR!", "El saldo inicial no puede ser menor a cero");
                    return;
                }
                if(ingresoNombre.getText().isEmpty() || ingresoap.getText().isEmpty() || ingresodir.getText().isEmpty() || ingresoSal.getText().isEmpty() || ingresofec.getText().isEmpty()){
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "ERROR!", "No se puede dejar ningun dato en blanco!!");
                    return;
                }
                else {
                    registroCliente(ingresoNombre.getText(), ingresoap.getText(), ingresodir.getText(), fecha, saldo);
                    Escena3 mostrar = new Escena3();
                    confirmar.getScene().setRoot(mostrar.getContent());
                }
            }
        });

        root = gridPane;
    }

    void registroCliente(String nombre, String apellido, String direccion, int fechaNac, float saldo){
        Registros registros = new Registros();
        ArrayList <Cliente> listaC = registros.getListaC();
        double id = (Math.random() * ((9999 - 1000) + 1)) + 1000;
        int idint = (int)id;
        String cuenta = "A" + idint;
        Cliente cliente = new Cliente(idint, nombre, apellido, cuenta, direccion, fechaNac, saldo);
        listaC.add(cliente);
        System.out.println(listaC.get(listaC.size()-1).getNombre());
        System.out.println(listaC.size());
        registros.setListaC(listaC);
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