package com.willianhdz.semana3;

public class Operaciones_Basicas {
    private double n1, n2;

    public Operaciones_Basicas() {
    }

    public Operaciones_Basicas(double n1, double n2) {
        this.n1 = n1;
        this.n2 = n2;
    }

    public double getN1() {
        return n1;
    }

    public void setN1(double n1) {
        this.n1 = n1;
    }

    public double getN2() {
        return n2;
    }

    public void setN2(double n2) {
        this.n2 = n2;
    }

    public double Sumar(){
        return (this.n1 + this.n2);
    }

    public double Restar(){
        return (this.n1 - this.n2);
    }
    public double Dividir(){
        return (this.n1 / this.n2);
    }
    public double Multiplicar(){
        return (this.n1 * this.n2);
    }






}

