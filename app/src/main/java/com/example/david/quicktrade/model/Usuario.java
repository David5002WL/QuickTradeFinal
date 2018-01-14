package com.example.david.quicktrade.model;

/**
 * Created by David on 14/01/2018.
 */

public class Usuario {

    private String nombreusuario;
    private String correo;
    private String nombre;
    private String apellidos;
    private String direccion;

    public Usuario(){

    }

    public Usuario(String nombreusuario, String correo, String nombre, String apellidos, String direccion) {
        this.nombreusuario = nombreusuario;
        this.correo = correo;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
    }

    public String getNombreusuario() {
        return nombreusuario;
    }

    public void setNombreusuario(String nombreusuario) {
        this.nombreusuario = nombreusuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombreusuario='" + nombreusuario + '\'' +
                ", correo='" + correo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}
