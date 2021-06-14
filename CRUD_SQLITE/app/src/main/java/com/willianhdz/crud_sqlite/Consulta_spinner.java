package com.willianhdz.crud_sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

public class Consulta_spinner extends AppCompatActivity {

    private Spinner sp_options;
    private TextView tv_cod, tv_descripcion, tv_precio;

    //ArrayList<String>listaArticulos;
    //ArrayList<Dto>articulosList;

    ConexionSQLite conexion = new ConexionSQLite(this);
    Dto datos = new Dto();

    //Para menu/////////////////////
    private FloatingActionMenu menu;
    private FloatingActionButton item1, item2, item3, item4, item5;

    //instancia de la clase modal
    modal_Toast_Custom modal = new modal_Toast_Custom();
    //Hasata aca ////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_spinner);


        sp_options = (Spinner)findViewById(R.id.sp_options);
        tv_cod = (TextView)findViewById(R.id.tv_cod);
        tv_descripcion = (TextView)findViewById(R.id.tv_descripcion);
        tv_precio = (TextView)findViewById(R.id.tv_precio);

        //Inicia bloque para pruebas del menu/
        menu = findViewById(R.id.menu_fab);
        item1 = findViewById(R.id.item1);
        item2 = findViewById(R.id.item2);
        item3 = findViewById(R.id.item3);
        item4 = findViewById(R.id.item4);
        item5 = findViewById(R.id.item5);

        menu.setOnMenuToggleListener(new FloatingActionMenu.OnMenuToggleListener() {
            @Override
            public void onMenuToggle(boolean opened) {
                if (opened) {
                    Toast.makeText(Consulta_spinner.this, "Menu abierto", Toast.LENGTH_SHORT);
                }else {
                    Toast.makeText(Consulta_spinner.this, "Menu cerrado", Toast.LENGTH_SHORT);
                }
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (menu.isOpened()){
                    menu.close(true);
                }
            }
        });

        item1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View view) {
                finishAffinity();
            }
        });

        item2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modal.dialogAbout(Consulta_spinner.this);
            }
        });

        item3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Consulta_spinner.this, MainActivity.class);
                startActivity(intent);

                modal.dialogConfirmCustom2(Consulta_spinner.this);
            }
        });

        item4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Consulta_spinner.this, Lista_articulos_recyclerview.class);
                startActivity(intent);

                modal.dialogConfirmCustom2(Consulta_spinner.this);
            }
        });

        item5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Consulta_spinner.this, List_view_articulos.class);
                startActivity(intent);

                modal.dialogConfirmCustom2(Consulta_spinner.this);
            }
        });


 //Finaliza bloque para preuba de l menu

        //conexion. obtenerListaArticulos(),
        conexion.consultaListaArticulos();

        //ArrayAdapter<CharSequence> adaptador —— new ArrayAdapter(this, android. R.layout. simple spinner item, IistaArticulos);
        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, conexion.obtenerListaArticulos());
        sp_options.setAdapter(adaptador);


        sp_options.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position,
                                       long I) {

                if (position != 0) {
                    tv_cod.setText("Codigo: " + conexion.consultaListaArticulos().get(position -
                            1).getCodigo());
                    tv_descripcion.setText("Descripcion: " + conexion.consultaListaArticulos().get(position - 1).getDescripcion());
                    tv_precio.setText("Precio: " + String.valueOf(conexion.consultaListaArticulos().get(position - 1).getPrecio()));

                } else {
                    tv_cod.setText("Codigo: ");
                    tv_descripcion.setText("Descripcion: ");
                    tv_precio.setText("Precio: ");

                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });

    }

}