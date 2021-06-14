package com.willianhdz.greendaogenerator.db;

import org.greenrobot.greendao.annotation.*;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END

/**
 * Entity mapped to table "ARTICULOS".
 */
@Entity
public class Articulos {

    @Id
    private Integer codigo;
    private String descripcion;
    private Double precio;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    @Generated
    public Articulos() {
    }

    public Articulos(Integer codigo) {
        this.codigo = codigo;
    }

    @Generated
    public Articulos(Integer codigo, String descripcion, Double precio) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    // KEEP METHODS - put your custom methods here
    // KEEP METHODS END

}
