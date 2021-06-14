package com.willianhdz.lab1_semana4;

public class Empleado {

    //Variables
   public int meses;
   public double deposito;

    public Empleado() {
    }

    public Empleado(int meses, double deposito) {
        this.meses = meses;
        this.deposito = deposito;
    }

    public int getMeses() {
        return meses;
    }

    public void setMeses(int meses) {
        this.meses = meses;
    }

    public double getDeposito() {
        return deposito;
    }

    public void setDeposito(double deposito) {
        this.deposito = deposito;
    }

    public double intereses(){
        double total = meses * (deposito * 0.02);
        return total;
    }
}
