package com.example;
/**
 * Clase de la lista doblemente enlazada circular, la cual es utilizada para almacenar los nodos y poder avanzar entre
 * ellos libremente. Una vez al final de esta, nos envía al inicio nuevamente.
 */
public class LinkedList{
    public String tag;
    public Nodos head;
    public Nodos tail;
    public Nodos current;
    public Integer size;

    /**
     * Método constructor
     */

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
            this.size++;
        } else {
            Nodos ultimo = head.prev;
            tail.next = nuevaSong;
            nuevaSong.next = head;
            tail = nuevaSong;
            head.prev = tail;
            tail.prev = ultimo;
            this.size++;
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
    public void adelanteCurrent(){current = current.next;}
    /**
     * Método para mover para atrás en la lista el Nodo Current
     */
    public void atrasCurrent(){current = current.prev;}

}


