package com.example;

import java.io.Serializable;

public class Nodos implements Serializable {
    public String path;
    public String nombrecan;
    public Nodos next;
    public Nodos prev;
    public String fav;

    public Nodos(){
    }

    /**
     * Método constructor
     */
    public Nodos(String path, String nombrecan, String fav) {
        this.path = path;
        this.nombrecan  = nombrecan;
        this.fav = fav;
        this.next = null;
        this.prev = null;

    }
    /**
     * Metodos setters and getter
     */
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getNombrecan() {
        return nombrecan;
    }

    public void setNombrecan(String nombrecan) {
        this.nombrecan = nombrecan;
    }

    public Nodos getNext() {
        return next;
    }

    public void setNext(Nodos next) {
        this.next = next;
    }

    public Nodos getPrev() {
        return prev;
    }

    public void setPrev(Nodos prev) {
        this.prev = prev;
    }

    public String getFav() {
        return fav;
    }

    public void setFav(String fave) {
        this.fav = fav;
    }
}


