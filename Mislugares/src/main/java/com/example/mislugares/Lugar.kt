package com.example.mislugares

public class Lugar {
    private String nombre;
    private String direccion;
    private Geopunto posicion;
    private String foto;
    private int telefono;
    private String url;
    private String comentario;
    private long fecha;
    private float valoracion;

    //Constructor de la clase
    public Lugar(String nombre, String direccion, double longitud, double latitud,
    int telefono, String url, String comentario, int valoracion){
        fecha = java.lang.System.currentTimeMillis();
        posicion = new Geopunto(longitud, latitud);
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.url = url;
        this.comentario = comentario;
        this.valoracion = valoracion;


    }
}