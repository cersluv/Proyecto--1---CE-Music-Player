package com.example;

import java.io.Serializable;

public class LinkedList implements Serializable{
    public String tag;
    public Nodos head;
    public Nodos tail;
    public Nodos current;
    public Integer size;

    /**
     * Método constructor
     */
    public LinkedList(){

    }
    public LinkedList(String tag) {
        this.tag = tag;
        this.head = null;
        this.tail = null;
        this.current = null;
        this.size = 0;
    }
    /**
     * Método para obtener el tamaño de la lista
     */
    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Nodos getHead() {
        return head;
    }

    public void setHead(Nodos head) {
        this.head = head;
    }

    public Nodos getTail() {
        return tail;
    }

    public void setTail(Nodos tail) {
        this.tail = tail;
    }

    public Nodos getCurrent() {
        return current;
    }

    public void setCurrent(Nodos current) {
        this.current = current;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    /**
     * Método para añadir elementos a la lista
     */
    public void añadir(String cancion, String nombrecan, String fav) {
        Nodos nuevaSong = new Nodos(cancion, nombrecan, fav);
        if (head == null) {
            head = nuevaSong;
            nuevaSong.next = head;
            nuevaSong.prev = head;
            tail = nuevaSong;
            current = head;
            size++;
        } else {
            Nodos ultimo = head.prev;
            tail.next = nuevaSong;
            nuevaSong.next = head;
            tail = nuevaSong;
            head.prev = tail;
            tail.prev = ultimo;
            size++;
        }
    }
    /**
     * Método para borrar el último elemento de la lista
     */
    public void deleteLast(){
    }
    /**
     * Método para mostrar los elementos de la lista
     */
    public void showPlaylist() {
        Nodos uno = head;
        int breaker = 0;
        while (breaker < this.size){
            breaker++;
            System.out.println(uno.getPath()+" - "+uno.getNombrecan()+" - "+uno.getFav());
            uno = uno.next;
        }
    }
    /**
     * Método para mover al inicio de la lista el Nodo Current
     */
    public void moveToStartCurrent(){current = head;}
    /**
     * Método para mover al final de la lista el Nodo Current
     */
    public void moveToEndCurrent(){current = tail;}
    /**
     * Método para mover para adelante en la lista el Nodo Current
     */
    public void moveForwardCurrent(){current = current.next;}
    /**
     * Método para mover para atrás en la lista el Nodo Current
     */
    public void moveBackCurrent(){current = current.prev;}
}


