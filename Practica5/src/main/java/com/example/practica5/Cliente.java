package com.example.practica5;



public class Cliente {
    private int id;
    private String nombre;
    private String apellidos;
    private String noCuenta;
    private String direccion;
    private int fechaNac;
    private float saldo;

    Cliente(int id, String nombre, String apellidos, String noCuenta, String direccion, int fechaNac, float saldo){
        this.id = id;
        this.nombre=nombre;
        this.apellidos=apellidos;
        this.noCuenta = noCuenta;
        this.direccion = direccion;
        this.fechaNac = fechaNac;
        this.saldo = saldo;
    }

    void setId(int id){
        this.id = id;
    }
    int getId(){
        return this.id;
    }

    void setNombre(String nombre){
        this.nombre = nombre;
    }
    String getNombre(){
        return this.nombre;
    }

    void setApellidos(String apellidos){
        this.apellidos = apellidos;
    }
    String getApellidos(){
        return this.apellidos;
    }

    void setNoCuenta(String noCuenta){
        this.noCuenta = noCuenta;
    }
    String getNoCuenta(){
        return this.noCuenta;
    }

    void setDireccion(String direccion){
        this.direccion = direccion;
    }
    String getDireccion(){
        return this.direccion;
    }

    void setFechaNac(int fechaNac){
        this.fechaNac = fechaNac;
    }
    int getFechaNac(){
        return this.fechaNac;
    }

    void setSaldo(float saldo){
        this.saldo = saldo;
    }
    float getSaldo(){
        return this.saldo;
    }

}
