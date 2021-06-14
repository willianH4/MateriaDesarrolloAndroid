package com.willianhdz.semana3;

import android.content.Intent;
import android.view.View;

public class Verificar {
    private String usuario;
    private String clave;

    public Verificar() {
    }

    public Verificar(String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

}
