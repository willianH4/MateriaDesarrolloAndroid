package com.willianhdz.appinvertaryindividual;

import java.io.Serializable;
import java.util.Date;

//Para enviar objetos entre actividades (Como parâmetro) se coloca el "implements Serializable"
public class Producto implements Serializable {

    //Atributos de la clase
    int id_producto;
    String nombre;
    String descripcion;
    double stock;
    double precio;
    String unidad_de_medida;
    int estado;
    String fecha_entrada;
    int categoria;

    public Producto() {
        //vaciio
    }

    public Producto(int id_producto, String nombre, String descripcion, double stock, double precio,
                    String unidad_de_medida, int estado, String fecha_entrada, int categoria) {
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.stock = stock;
        this.precio = precio;
        this.unidad_de_medida = unidad_de_medida;
        this.estado = estado;
        this.fecha_entrada = fecha_entrada;
        this.categoria = categoria;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getUnidad_de_medida() {
        return unidad_de_medida;
    }

    public void setUnidad_de_medida(String unidad_de_medida) {
        this.unidad_de_medida = unidad_de_medida;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getFecha_entrada() {
        return fecha_entrada;
    }

    public void setFecha_entrada(String fecha_entrada) {
        this.fecha_entrada = fecha_entrada;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }
}