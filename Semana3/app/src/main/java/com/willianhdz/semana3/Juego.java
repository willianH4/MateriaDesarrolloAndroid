package com.willianhdz.semana3;

import android.widget.Toast;

public class Juego {

    public int num;

    public Juego(){
        //vacio
    }

    public Juego(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int number(){

        int intentos = 0;
        if (num > 0 && num <= 50){
            int puntosactual=num;
            puntosactual++;
        }
        return intentos;
    }

}
