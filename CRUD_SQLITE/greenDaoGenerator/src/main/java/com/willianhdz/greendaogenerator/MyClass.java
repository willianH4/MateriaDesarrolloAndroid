package com.willianhdz.greendaogenerator;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class MyClass {
    public static void main(String[] args) {
        Schema schema = new Schema(1, "com.willianhdz.greendaogenerator.db"); //package de la app
        schema.enableKeepSectionsByDefault();
        
        addTables(schema);

        try {
            new DaoGenerator().generateAll(schema, "./app/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addTables(final Schema schema) {
        addArticulosEntities(schema);
        //addPhonesEntities(schema);
    }

    //esto describe las columnas en la tabla
    private static Entity addArticulosEntities(final Schema schema) {
        Entity articulos = schema.addEntity("Articulos");
        articulos.addIntProperty("codigo").primaryKey();
        articulos.addStringProperty("descripcion");
        articulos.addDoubleProperty("precio");
        return articulos;
    }
    //seguir agregando tablas hacia abajo
}