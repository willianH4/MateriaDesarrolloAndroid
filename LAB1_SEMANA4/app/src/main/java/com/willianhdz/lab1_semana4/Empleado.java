package com.willianhdz.lab1_semana4;

public class Empleado {

    //Variables
   public double ht;
   public double phA = 7;
   public double phB = 8.5;
   public double phC = 10;
   public double phD = 12.5;
   String nombre = "";

   public Empleado(){
       //constructor vacio
   }

    public Empleado(double ht, double phA, double phB, double phC, double phD, String nombre) {
        this.ht = ht;
        this.phA = phA;
        this.phB = phB;
        this.phC = phC;
        this.phD = phD;
        this.nombre = nombre;
    }

    public double getHt() {
        return ht;
    }

    public void setHt(double ht) {
        this.ht = ht;
    }

    public double getPhA() {
        return phA;
    }

    public void setPhA(double phA) {
        this.phA = phA;
    }

    public double getPhB() {
        return phB;
    }

    public void setPhB(double phB) {
        this.phB = phB;
    }

    public double getPhC() {
        return phC;
    }

    public void setPhC(double phC) {
        this.phC = phC;
    }

    public double getPhD() {
        return phD;
    }

    public void setPhD(double phD) {
        this.phD = phD;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Metodos
    public double SueldoA(){
        double pagoA = this.ht  * this.phA;
        return pagoA;
    }

    public double SueldoB(){
        double pagoB = this.ht * this.phB;
        return pagoB;
    }

    public double SueldoC(){
        double pagoC = this.ht * this.phC;
        return pagoC;
    }

    public double SueldoD(){
        double pagoD = this.ht * this.phD;
        return pagoD;
    }

}
