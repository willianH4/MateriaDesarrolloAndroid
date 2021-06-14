package com.willianhdz.cine;

public class Cine {

    public double precio;
    public int edad;

    public Cine(double precio, int edad) {
        this.precio = precio;
        this.edad = edad;
    }

    public Cine(){
        //vacio para inicializar
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }


    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }


    public double total(){
        double costo = 0;
        if (edad < 18 || edad > 60){
            costo = precio * 0.50;
        }else {
            costo = precio;
        }
        return costo;
    }


    public double desc(){
        double descuento = 0;
        descuento = precio * 0.50;
        return descuento;
    }

}
