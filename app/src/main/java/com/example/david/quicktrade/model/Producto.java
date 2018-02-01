package com.example.david.quicktrade.model;

/**
 * Created by David on 29/01/2018.
 */

public class Producto {
    private String nombreProducto;
    private String categoria;
    private String usuario;

    public Producto(String nombreProducto, String categoria, String usuario) {
        this.nombreProducto = nombreProducto;
        this.categoria = categoria;
        this.usuario = usuario;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String toString() {
        return "Producto{" +
                "nombreProducto='" + nombreProducto + '\'' +
                ", categoria='" + categoria + '\'' +
                ", usuario='" + usuario + '\'' +
                '}';
    }

}
