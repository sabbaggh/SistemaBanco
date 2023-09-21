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

public class Transferencia implements HelloController{
    private final GridPane root;


    public Transferencia(){

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
        Label titulo = new Label("Transferencia");
        titulo.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 24));
        gridPane.add(titulo, 0,0,2,1);
        GridPane.setHalignment(titulo, HPos.CENTER);
        GridPane.setMargin(titulo, new Insets(20, 0,20,0));
        Label idEm = new Label("Ingresa la ID del cliente va a mandar el dinero");
        gridPane.add(idEm,0,1);
        TextField idEmIn = new TextField();
        gridPane.add(idEmIn,1,1);

        Label idRe = new Label("Ingrese la ID del cliente que va a recibir el dinero");
        gridPane.add(idRe,0,2);
        TextField idReIn = new TextField();
        gridPane.add(idReIn,1,2);

        Label cantidad = new Label("Ingrese la cantidad a transferir");
        gridPane.add(cantidad,0,3);
        TextField cantidadIn = new TextField();
        gridPane.add(cantidadIn,1,3);

        Button confirmar = new Button("Confirmar");
        confirmar.setPrefSize(100,30);
        gridPane.add(confirmar,1,4);

        Button cancelar = new Button("Volver");
        cancelar.setPrefSize(100,30);
        gridPane.add(cancelar,0,4);

        estilos.setStyleLabelGrande(idEm,idRe);
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
                    int idemi = Integer.parseInt(idEmIn.getText());
                } catch (NumberFormatException nfe){
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "ERROR!", "Ingrese un ID valido en el emisor");
                }
                int idemi =  Integer.parseInt(idEmIn.getText());
                try{
                    int idrec = Integer.parseInt(idReIn.getText());
                } catch (NumberFormatException nfe){
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "ERROR!", "Ingrese un ID valido en el recepto");
                }
                int idrec =  Integer.parseInt(idReIn.getText());
                try{
                    float saldo = Float.valueOf(cantidadIn.getText());
                } catch (NumberFormatException nfe){
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "ERROR!", "El formato de transferencia  ingresado no es valido");
                }
                float saldo = Float.valueOf(cantidadIn.getText());
                if(saldo<=0){
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "ERROR!", "La transferencia no puede ser negativo o igual a 0");
                    return;
                }
                if(idEmIn.getText().isEmpty() || idReIn.getText().isEmpty() || cantidadIn.getText().isEmpty()){
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "ERROR!", "No se puede dejar ningun dato en blanco");
                    return;
                }
                Registros registros = new Registros();
                int xem = -1;
                int xre = -1;
                ArrayList<Cliente> listaC = registros.getListaC();
                for(int i = 0; i<listaC.size(); i++){
                    if(idemi == listaC.get(i).getId()){
                        if(listaC.get(i).getSaldo() - saldo < 0) {
                            showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "ERROR!", "El usuario emisor no cuenta con el saldo suficiente para realizar la operacion");
                            return;
                        }
                        else
                            xem = i;
                    }
                }
                if (xem == -1){
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "ERROR!", "No existe ningun usuario con el ID ingresado");
                }
                else{
                    for(int j =0; j <listaC.size(); j++){
                        if(idrec == listaC.get(j).getId()) {
                            float depositacion = saldo+listaC.get(j).getSaldo();
                            float retiracion = listaC.get(xem).getSaldo()-saldo;
                            showAlert(Alert.AlertType.CONFIRMATION, gridPane.getScene().getWindow(), "Transferencia", "Se han transferido" +saldo+" desde el usuario "
                                    + listaC.get(xem).getId() + "\n Su nuevo saldo es $" +retiracion+"\nEl nuevo saldo del usuario receptor "+listaC.get(j).getId()+" es $" +depositacion);
                            Cliente emisor = new Cliente(listaC.get(xem).getId(), listaC.get(xem).getNombre(), listaC.get(xem).getApellidos(),
                                    listaC.get(xem).getNoCuenta(),listaC.get(xem).getDireccion(), listaC.get(xem).getFechaNac(), retiracion);
                            Cliente receptor = new Cliente(listaC.get(j).getId(), listaC.get(j).getNombre(), listaC.get(j).getApellidos(),
                                    listaC.get(j).getNoCuenta(),listaC.get(j).getDireccion(), listaC.get(j).getFechaNac(), depositacion);
                            listaC.set(xem, receptor);
                            listaC.set(j,emisor);
                            registros.setListaC(listaC);

                        }
                        /*
                        Cliente cliente1 = new Cliente(listaC.get(i).getId(), listaC.get(i).getNombre(), listaC.get(i).getApellidos(),
                        listaC.get(i).getNoCuenta(),listaC.get(i).getDireccion(), listaC.get(i).getFechaNac(), depositacion);
                        listaC.set(i, cliente1);
                        registros.setListaC(listaC);*/
                        else
                            xre = 1;
                    }
                }
                if(xre == -1){
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "ERROR!", "No existe ningun usuario receptor con el ID ingresado");
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
