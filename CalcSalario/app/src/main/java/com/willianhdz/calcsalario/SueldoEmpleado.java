package com.willianhdz.calcsalario;

public class SueldoEmpleado {
    public String nombre;
    public double horasTrabajadas;
    public double pagoHora;

    public SueldoEmpleado(){
        //constructor vacio
    }

    public SueldoEmpleado(double horasTrabajadas, double pagoHora){
        this.horasTrabajadas = horasTrabajadas;
        this.pagoHora = pagoHora;
    }

    public SueldoEmpleado(String nombre, double horasTrabajadas, double pagoHora){
        this.nombre = nombre;
        this.horasTrabajadas = horasTrabajadas;
        this.pagoHora = pagoHora;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public void setHorasTrabajadas(double horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }

    public double getPagoHora() {
        return pagoHora;
    }

    public void setPagoHora(double pagoHora) {
        this.pagoHora = pagoHora;
    }

    public double sueldo(){
        return (this.horasTrabajadas * this.pagoHora);
    }

    public double renta(){
        double descuento = 0;
        descuento = this.sueldo() - (this.sueldo() * 0.1);
        return descuento;
    }

    public String nombre(){
        return "El nombre del trabajador es: "+this.nombre;
    }

}
