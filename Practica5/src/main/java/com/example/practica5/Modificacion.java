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

public class Modificacion implements HelloController {
    private final GridPane root;


    public Modificacion() {

        GridPane gridPane = new GridPane();
        gridPane = modificacion();
        contenido(gridPane);

        root = gridPane;

    }

    private GridPane modificacion() {
        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-background-color: LIGHTBLUE");
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(40, 40, 40, 40));
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        return gridPane;
    }

    private void contenido(GridPane gridPane) {
        AlmacenarID index = new AlmacenarID();
        int x = index.getId();
        Registros registros = new Registros();
        ArrayList<Cliente> listaC = registros.getListaC();
        Styles estilos = new Styles();
        Label titulo = new Label("Datos del Usuario");
        titulo.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 24));
        gridPane.add(titulo, 0,0,2,1);
        GridPane.setHalignment(titulo, HPos.CENTER);
        GridPane.setMargin(titulo, new Insets(20, 0,20,0));

        Label nombre = new Label("Nombre: ");
        gridPane.add(nombre, 0,1);
        TextField ingresoNombre = new TextField(listaC.get(x).getNombre());
        gridPane.add(ingresoNombre,1,1);

        Label apellido = new Label("Apellidos: ");
        gridPane.add(apellido, 0,2);
        TextField ingresoap = new TextField(listaC.get(x).getApellidos());
        gridPane.add(ingresoap,1,2);

        Label fechaNac = new Label("Año De Nacimiento: ");
        gridPane.add(fechaNac, 0,3);
        TextField ingresofec = new TextField(String.valueOf(listaC.get(x).getFechaNac()));
        gridPane.add(ingresofec,1,3);

        Label direccion = new Label("Direccion: ");
        gridPane.add(direccion, 0,4);
        TextField direccionIn = new TextField(listaC.get(x).getDireccion());
        gridPane.add(direccionIn,1,4);

        Label id = new Label("ID del usuario: ");
        gridPane.add(id,0,5);
        Label idIn = new Label(String.valueOf(listaC.get(x).getId()));
        gridPane.add(idIn,1,5);

        Label cuenta = new Label("Cuenta: ");
        gridPane.add(cuenta,0,6);
        Label cuentaIn = new Label(listaC.get(x).getNoCuenta());
        gridPane.add(cuentaIn,1,6);

        Label saldo = new Label("Saldo: ");
        gridPane.add(saldo,0,7);
        Label saldoIn = new Label(String.valueOf(listaC.get(x).getSaldo()));
        gridPane.add(saldoIn,1,7);

        Label tipocuenta = new Label("Tipo de cuenta: ");
        Label estandar = new Label("Estandar");
        Label premium = new Label("Premium");
        gridPane.add(tipocuenta,0,8);
        if(listaC.get(x).getSaldo() < 100000){
            gridPane.add(estandar,1,8);
        }
        else{
            gridPane.add(premium,1,8);
        }

        Button confirmar = new Button("Confirmar");
        gridPane.add(confirmar, 1,9);

        Button cancelar = new Button("Cancelar");
        gridPane.add(cancelar,0,9);


        estilos.setStyleLabelGrande(nombre, apellido, fechaNac,direccion,id,idIn, cuenta, cuentaIn,saldoIn,saldo,tipocuenta,premium,estandar);
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
                    int fecha = Integer.parseInt(ingresofec.getText());
                } catch (NumberFormatException nfe){
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "ERROR!", "El formato de año de nacimiento ingresado no es valido");
                }

                int fecha = Integer.parseInt(ingresofec.getText());

                if(2023-fecha <18){
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "ERROR!", "Se necesita ser mayor de edad para poder registrarse");
                    return;
                }
                if(2023-fecha >100){
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "ERROR!", "Ingrese un año de nacimiento valido");
                    return;
                }
                if(ingresoNombre.getText().isEmpty() || ingresoap.getText().isEmpty() || ingresofec.getText().isEmpty() || direccionIn.getText().isEmpty()){
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "ERROR!", "No se puede dejar ningun dato en blanco");
                }
                else {
                    Cliente cliente1 = new Cliente(listaC.get(x).getId(), ingresoNombre.getText(), ingresoap.getText(), listaC.get(x).getNoCuenta(), direccionIn.getText(), fecha, listaC.get(x).getSaldo());
                    listaC.set(x,cliente1);
                    registros.setListaC(listaC);
                    Escena1 menu = new Escena1();
                    cancelar.getScene().setRoot(menu.getContent());
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
