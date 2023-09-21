package com.example.practica5;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.Parent;
import javafx.stage.Window;

import java.util.ArrayList;

public class Escena1 implements HelloController {

    private final GridPane root;


    public Escena1(){

        GridPane gridPane = new GridPane();
        gridPane = menu();
        contenido(gridPane);


        root = gridPane;
    }

    //crea la gridpane
    private GridPane menu(){
        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-background-color: LIGHTBLUE");
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(40, 40, 40, 40));
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        return gridPane;
    }
    //Aqui se declaran todos los botnes y labels
    void contenido(GridPane gridPane){
        Registros registros = new Registros();
        ArrayList <Cliente> listaC = registros.getListaC();
        Label titulo = new Label("Sistema Bancario");
        titulo.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 24));
        gridPane.add(titulo, 0,0,2,1);
        GridPane.setHalignment(titulo, HPos.CENTER);
        GridPane.setMargin(titulo, new Insets(20, 0,20,0));

        Button registrarCliente = new Button("Registrar cliente");
        registrarCliente.setPrefSize(200, 30);
        gridPane.add(registrarCliente, 0,1);
        Button modificarDatos = new Button("Modificar Datos");
        modificarDatos.setPrefSize(200, 30);
        gridPane.add(modificarDatos, 0,2);
        Button consultarSaldo = new Button("Consultar saldo");
        consultarSaldo.setPrefSize(200, 30);
        gridPane.add(consultarSaldo, 0,3);
        Button retirar = new Button("Retirar dinero");
        retirar.setPrefSize(200, 30);
        gridPane.add(retirar, 0,4);
        Button depositar = new Button("Depositar dinero");
        depositar.setPrefSize(200, 30);
        gridPane.add(depositar, 0,5);
        Button transferir = new Button("Transferir dinero");
        transferir.setPrefSize(200, 30);
        gridPane.add(transferir, 0,6);
        Button salir = new Button("Salir");
        salir.setPrefSize(200, 30);
        gridPane.add(salir, 0,7);
        System.out.println(javafx.scene.text.Font.getFamilies());
        Styles estilo = new Styles();
        estilo.setStyleBoton(registrarCliente,modificarDatos,consultarSaldo,retirar,transferir,salir, depositar);
        //Cuando se aprieta salir se sale
        salir.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent salida) {
                System.exit(0);
            }
        });
        //Aqui se manda a llamar a la escena 2 para registrar a un nuevo cliente
        registrarCliente.setOnMouseClicked(event->{
            Escena2 registro = new Escena2();
            registrarCliente.getScene().setRoot(registro.getContent());
            System.out.println("Hola");
        });
        //Manda a llamar a la escena consultaSaldo, pero si la lista de clientes esta vacia entonces entonces manda una alerta que no se puede
        consultarSaldo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if(listaC.isEmpty()){
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "ERROR!", "No hay ningun cliente registrado aun");
                }
                else{
                    ConsultaSaldo consultar = new ConsultaSaldo();
                    consultarSaldo.getScene().setRoot(consultar.getContent());
                }

            }
        });

        //Se manda a llamar al metodo para obtener la escena de modificar y tambien si la lista esta vacia entonces no deja
        modificarDatos.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e1) {
                if(listaC.isEmpty()){
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "ERROR!", "No hay ningun cliente registrado aun");
                }
                else{
                    Modificar modi = new Modificar();
                    modificarDatos.getScene().setRoot(modi.getContent());
                }

            }
        });

        //Se manda a llamar a la escena para depositar y tambien si la lista esta vacia entonces no se puede
        depositar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e1) {
                if(listaC.isEmpty()){
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "ERROR!", "No hay ningun cliente registrado aun");
                }
                else{
                    Depositar depo = new Depositar();
                    depositar.getScene().setRoot(depo.getContent());
                }

            }
        });

        //lo mismo que en depositar pero en este caso para retirar
        retirar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e1) {
                if(listaC.isEmpty()){
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "ERROR!", "No hay ningun cliente registrado aun");
                }
                else{
                    Retirar retiro = new Retirar();
                    retirar.getScene().setRoot(retiro.getContent());
                }

            }
        });

        //Lo mismo que las dos anteriores pero para transferir solo que aqui si no hay por lo menos dos usuarios registrados entonces no deja
        transferir.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e1) {
                if(listaC.size()<2){
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "ERROR!", "Se necesitan de al menos dos clientes registrados para realizar una transferencia");
                }
                else{
                    Transferencia transferencia = new Transferencia();
                    transferir.getScene().setRoot(transferencia.getContent());
                }

            }
        });
    }
    //Funcion para mandar alertas
    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    //regresa la raiz que servira para crear las escenas
    @Override
    public Parent getContent() {
        return root;
    }

}
