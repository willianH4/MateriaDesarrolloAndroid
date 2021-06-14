package com.willianhdz.appinvertaryindividual;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class AdminSQLiteOpenHerlper extends SQLiteOpenHelper {

    public AdminSQLiteOpenHerlper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //segunda tabla Categorias
        db.execSQL("create table tb_categoria(id_categoria int not null primary key autoincrement, nom_categoria varchar(50) not null, " +
                "estado_categoria int not null)");

        //Creacion de la primera tabla Productos
        db.execSQL("create table tb_producto(id_producto int not null primary key autoincrement, nom_producto varchar(50) not null, " +
                "des_producto varchar(90) not null, stock real not null, precio real not null, unidad_de_medida varchar(20) not null," +
                "estado_producto int not null, categoria int not null, fecha_entrada datetime not null, FOREIGN KEY(categoria) references tb_categoria(id_categoria))");


        //Tercera tabla Usuario
        db.execSQL("create table tb_producto(id_usuario int not null primary key autoincrement, nombre varchar(50) not null, " +
                "apellido varchar(50) not null, correo varchar(45) not null, usuario varchar(45) not null, clave varchar(150) not null," +
                "tipo int not null, estado int not null, pregunta varchar(60) not null, respuesta varchar(35) not null," +
                "fecha_registro datetime not null)");

        //Insertanto un registro
        db.execSQL("insert into tb_categoria values('Bebidas', 2)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists tb_producto");
        db.execSQL("drop table if exists tb_categoria");
        db.execSQL("drop table if exists tb_usuario");
        onCreate(db);
    }
}
