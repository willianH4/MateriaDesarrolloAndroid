package com.willianhdz.semana1;

public class Ejemplo1POO {

    //Variables
    private double numero1;
    private double numero2;

    public Ejemplo1POO(double numero1, double numero2) {
        this.numero1 = numero1;
        this.numero2 = numero2;
    }

    public Ejemplo1POO() {
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

    public double sumar(){
        double resultado = 0;
        resultado = numero1 + numero2;
        return resultado;
    }
}

