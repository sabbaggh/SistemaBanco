package com.example.practica5;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.Parent;

import java.util.ArrayList;
import java.util.List;

public class Escena3 implements HelloController{

    private final GridPane root;

    public Escena3(){
        Registros registros = new Registros();
        ArrayList <Cliente> listaC = registros.getListaC();
        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-background-color: LIGHTBLUE");
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(40, 40, 40, 40));
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        Label titulo = new Label("Datos Del Cliente");
        titulo.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 24));
        gridPane.add(titulo, 0,0,2,1);
        GridPane.setHalignment(titulo, HPos.CENTER);
        GridPane.setMargin(titulo, new Insets(20, 0,20,0));

        Label nombre = new Label("Nombre: ");
        gridPane.add(nombre, 0,1);
        Label ingresoNombre = new Label(listaC.get(listaC.size()-1).getNombre());
        gridPane.add(ingresoNombre,1,1);

        Label apellido = new Label("Apellido: ");
        gridPane.add(apellido, 0,2);
        Label ingresoApe = new Label(listaC.get(listaC.size()-1).getApellidos());
        gridPane.add(ingresoApe,1,2);



        Label id = new Label("ID del usuario: ");
        gridPane.add(id,0,3);
        Label ingresoId = new Label(String.valueOf(listaC.get(listaC.size()-1).getId()));
        gridPane.add(ingresoId,1,3);

        Label cuenta = new Label("Cuenta: ");
        gridPane.add(cuenta,0,4);
        Label cuentaIn = new Label(listaC.get(listaC.size()-1).getNoCuenta());
        gridPane.add(cuentaIn,1,4);

        Label direccion = new Label("Direccion: ");
        gridPane.add(direccion,0,5);
        Label direccionIn = new Label(listaC.get(listaC.size()-1).getDireccion());
        gridPane.add(direccionIn,1,5);

        Label nac = new Label("Año de nacimiento: ");
        gridPane.add(nac,0,6);
        Label nacIn = new Label(String.valueOf(listaC.get(listaC.size()-1).getFechaNac()));
        gridPane.add(nacIn,1,6);

        float saldoCliente = listaC.get(listaC.size()-1).getSaldo();
        Label saldo = new Label("Saldo: ");
        gridPane.add(saldo,0,7);
        Label saldoIn = new Label("$"+String.valueOf(saldoCliente));
        gridPane.add(saldoIn,1,7);

        Label tipocuenta = new Label("Tipo de cuenta: ");
        Label estandar = new Label("Estandar");
        Label premium = new Label("Premium");
        gridPane.add(tipocuenta,0,8);
        if(saldoCliente < 100000){
            gridPane.add(estandar,1,8);
        }
        else{
            gridPane.add(premium,1,8);
        }

        Button confirmar = new Button("Confirmar");
        confirmar.setPrefSize(200, 30);
        gridPane.add(confirmar, 0,9);

        Styles estilo = new Styles();
        estilo.setStyleLabelGrande(nombre, ingresoNombre, apellido, ingresoApe, id, ingresoId, cuentaIn, cuenta,
                direccionIn, direccion, nac, nacIn, saldoIn, saldo, tipocuenta, estandar, premium);
        estilo.setStyleBoton(confirmar);

        confirmar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent mostrarDatos) {
                Escena1 menu = new Escena1();
                confirmar.getScene().setRoot(menu.getContent());
            }
        });
        root = gridPane;
    }

    @Override
    public Parent getContent() {
        return root;
    }
}
