package com.willianhdz.lab1_semana4;

public class Alumno {
    //atributos
    public double n1, n2, n3, n4, n5;
    public double nota1, nota2, nota3;

    public Alumno(){
    //vaciio
    }

    public Alumno(double n1, double n2, double n3, double n4, double n5){
        this.n1 = n1;
        this.n2 = n2;
        this.n3 = n3;
        this.n4 = n4;
        this.n5 = n5;
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

    public double getN3() {
        return n3;
    }

    public void setN3(double n3) {
        this.n3 = n3;
    }

    public double getN4() {
        return n4;
    }

    public void setN4(double n4) {
        this.n4 = n4;
    }

    public double getN5() {
        return n5;
    }

    public void setN5(double n5) {
        this.n5 = n5;
    }

    public double total(){
        double promedioFinal = 0;
        nota1 = (this.n1 + this.n2 + this.n3) / 3 * 0.55;
        nota2 = this.n4 * 0.3;
        nota3 = this.n5 * 0.15;
        promedioFinal = nota1 + nota2 + nota3;
        return promedioFinal;
    }

}
