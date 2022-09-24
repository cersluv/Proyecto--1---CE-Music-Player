package com.example;

import java.io.File;
import java.io.Serializable;

/**
 * Clase songs, la cual recolecta la información del nombre, path y si es favorita o no la canción.
 */
public class songs implements Serializable {

    public String nombre;
    public File path;
    public String favorita;


    public String getNombre() {
        return nombre;
    }

    public File getPath() {
        return path;

    }

    public String getFavorita() {
        return favorita;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPath(File path) {
        this.path = path;
    }

    public void setFavorita(String favorita) {
        this.favorita = favorita;
    }

    /**
     * Método Contructor de la clase "songs"
     */
    public songs(File path, String nombre, String favorita) {
        this.path = path;
        this.nombre = nombre;
        this.favorita = favorita;

    }


    @Override
    public String toString() {
        return "[" + path + ", " + nombre + ", " + favorita + "]";
    }
}
