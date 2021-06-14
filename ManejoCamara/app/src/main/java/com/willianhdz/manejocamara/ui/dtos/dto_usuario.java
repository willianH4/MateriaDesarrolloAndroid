package com.willianhdz.manejocamara.ui.dtos;
import android.graphics.Bitmap;

public class dto_usuario {
    int id_usuario;
    String nombre;
    String profesion;
    Bitmap imagen;
    String rutaImagen;

    public dto_usuario() {
        //vacio
    }

    public dto_usuario(int id_usuario, String nombre, String profesion, Bitmap imagen, String rutaImagen) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.profesion = profesion;
        this.imagen = imagen;
        this.rutaImagen = rutaImagen;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }
}
