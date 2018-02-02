package com.example.david.quicktrade.model;

/**
 * Created by David on 29/01/2018.
 */

public class Producto {
    private int imagen_producto;
    private String nombreProducto;
    private String categoria;
    private String UID;

    public Producto(){

    }

    public Producto(int imagen_producto, String nombreProducto, String categoria, String UID) {
        this.imagen_producto = imagen_producto;
        this.nombreProducto = nombreProducto;
        this.categoria = categoria;
        this.UID = UID;
    }


    public int getImagen_producto() {
        return imagen_producto;
    }

    public void setImagen_producto(int imagen_producto) {
        this.imagen_producto = imagen_producto;
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


    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "nombreProducto='" + nombreProducto + '\'' +
                ", categoria='" + categoria + '\'' +
                ", UID='" + UID + '\'' +
                ", imagen_producto='" + imagen_producto + '\'' +
                '}';
    }


}
