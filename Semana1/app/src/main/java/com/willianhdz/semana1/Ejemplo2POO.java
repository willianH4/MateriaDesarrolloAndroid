package com.willianhdz.semana1;

public class Ejemplo2POO {

    //variables
    private double numero1;
    private double numero2;

    public Ejemplo2POO(double numero1, double numero2) {
        this.numero1 = numero1;
        this.numero2 = numero2;
    }

    public Ejemplo2POO() {
        //vacio
    }

    public double getNumero1() {
        return numero1;
    }

    public void setNumero1(double numero1) {
        this.numero1 = numero1;
    }

    public double getNumero2() {
        return numero2;
    }

    public void setNumero2(double numero2) {
        this.numero2 = numero2;
    }

    public double suma(){
        double totalS = 0;
        totalS = numero1 + numero2;
        return totalS;
    }

    public double resta(){
        double totalR = 0;
        totalR = numero1 - numero2;
        return totalR;
    }

    public double multiplicacion(){
        double totalm = 0;
        totalm = numero1 * numero2;
        return totalm;
    }

    public double dividir(){
        double totald = 0;
        totald = numero1 / numero2;
        return totald;
    }
}
