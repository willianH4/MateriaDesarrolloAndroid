package com.willianhdz.lab1_semana4;

public class Programa {
    //atributos
    public int cantidad;
    public double precioVenta = 500;
    public double precioReal;
    public double ganancia;

    public Programa() {
    }

    public Programa(int cantidad, double precioVenta, double precioReal, double ganancia) {
        this.cantidad = cantidad;
        this.precioVenta = precioVenta;
        this.precioReal = precioReal;
        this.ganancia = ganancia;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public double getPrecioReal() {
        return precioReal;
    }

    public void setPrecioReal(double precioReal) {
        this.precioReal = precioReal;
    }

    public double getGanancia() {
        return ganancia;
    }

    public void setGanancia(double ganancia) {
        this.ganancia = ganancia;
    }

    public double costoVenta(){
        double total = precioVenta;
        return total;
    }

    public double costoReal(){
        double real = precioVenta - (precioVenta * 0.2);
        return real;
    }

    public double ganancia(){
        double ganancias = cantidad * (precioVenta * 0.2);
        return ganancias;
    }
}
