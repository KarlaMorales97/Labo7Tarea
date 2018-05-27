package com.morales.labo7t.Datos;

/**
 * Created by Karla on 21/05/2018.
 */

public class estudiante {
    private String carnet, nombre;
    private String nota;


    public estudiante(String carnet, String nombre) {
        this.carnet = carnet;
        this.nombre = nombre;
    }


    public estudiante(){}

    public estudiante(String carnet, String nombre, String nota) {
        this.carnet = carnet;
        this.nombre = nombre;
        this.nota = nota;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }
}
