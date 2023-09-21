package com.example.practica5;

import java.util.ArrayList;

public class Registros {

    private static ArrayList <Cliente> listaC = new ArrayList<>();

    void setListaC(ArrayList<Cliente> listaC){

        this.listaC=listaC;
    }

    ArrayList<Cliente> getListaC(){
        return this.listaC;
    }
}
