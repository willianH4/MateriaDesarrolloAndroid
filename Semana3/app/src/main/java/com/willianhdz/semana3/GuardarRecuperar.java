package com.willianhdz.semana3;

public class GuardarRecuperar {
    private String nombre;
    private String datos;

    public GuardarRecuperar() {
    }

    public GuardarRecuperar(String nombre, String datos) {
        this.nombre = nombre;
        this.datos = datos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDatos() {
        return datos;
    }

    public void setDatos(String datos) {
        this.datos = datos;
    }
}
