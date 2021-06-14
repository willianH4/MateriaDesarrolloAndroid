package com.willianhdz.crud_sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.willianhdz.crud_sqlite.*;

import java.util.ArrayList;

public class Lista_articulos_recyclerview extends AppCompatActivity {

    private RecyclerView recycler;
    private AdaptadorArticulos adaptadorArticulos;
    ConexionSQLite datos = new ConexionSQLite(Lista_articulos_recyclerview.this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_articulos_recyclerview);

        recycler = (RecyclerView)findViewById(R.id.rview);
        //Esta linea mejora el rendimiento, si sabemos que el contenido
        //no va a afectar el tama;p del recycler
        recycler.setHasFixedSize(true);

        //Nuestro Recyclerview usara un Linear layout manager
        recycler.setLayoutManager(new LinearLayoutManager(this));

        //adaptadorArticulos = new AdaptadorArticulos(consulta_recyclerView.this,
        //obtenerArticulos());

        adaptadorArticulos = new AdaptadorArticulos(Lista_articulos_recyclerview.this,
                datos.mostrarArticulos());
        recycler.setAdapter(adaptadorArticulos);
    }
}