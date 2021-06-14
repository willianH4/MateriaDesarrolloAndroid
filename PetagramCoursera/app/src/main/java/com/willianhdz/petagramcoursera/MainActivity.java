package com.willianhdz.petagramcoursera;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.github.clans.fab.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    SwipeRefreshLayout swIndicador;
    ListView miLista;
    Adapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Invocacion del metodo en el boton flotante
        agregarFAB();

        swIndicador = findViewById(R.id.swIndicador);
        miLista = findViewById(R.id.miLista);

        String[] planetas = getResources().getStringArray(R.array.planetas);
        miLista.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, planetas));

        //evento onlistener para que el refres este escuchando al evento que se le solicite
        swIndicador.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refrescarContenido();
            }
        });

    }

    //Metodo para cargar la lista o refrescar
    public void refrescarContenido(){
        String[] planetas = getResources().getStringArray(R.array.planetas);
        miLista.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, planetas));
        swIndicador.setRefreshing(false);
    }

    //Metodo para funcionalidad del boton flotante
    public void agregarFAB(){
        FloatingActionButton miFAB = (FloatingActionButton) findViewById(R.id.button_fab);
        miFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Probando el widget snackbar para mostrar mensajes emergentes
                Snackbar.make(v, getResources().getString(R.string.mensaje),
                        Snackbar.LENGTH_SHORT).setAction(getResources().getString(R.string.texto_accion), new
                        View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i("Click en snackbar", "haz clickeado"); //Se muestra en la consola
                    }
                })
                .setActionTextColor(getResources().getColor(R.color.colorPrimary)) //color a opcion dentro de snackbar
                .show();
            }
        });
    } //Cierre de metodo agregarFab
}