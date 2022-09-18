package com.example;

public class Usuario {
    private String nombre;
    private String contraseña;
    private String correo;
    private String provincia;

    public String getNombre() {
        return nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public String getCorreo() {
        return correo;
    }

    public String getProvincia() {
        return provincia;
    }

    public Usuario(String nombre, String contraseña, String correo, String provincia) {
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.correo = correo;
        this.provincia = provincia;
    }


}
