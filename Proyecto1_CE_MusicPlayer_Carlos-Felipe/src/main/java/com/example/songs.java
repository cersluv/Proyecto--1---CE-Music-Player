package com.example;

import java.io.Serializable;


public class songs implements Serializable {

    public String nombre ;
    public String path ;
    public String favorita ;


    public String getNombre() {
        return nombre;
    }

    public String getPath(){
        return path;

    }

    public String getFavorita() {
        return favorita;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setFavorita(String favorita) {
        this.favorita = favorita;
    }

    public songs(String path, String nombre, String favorita) {
        this.path = path;
        this.nombre = nombre;
        this.favorita = favorita;

    }
}