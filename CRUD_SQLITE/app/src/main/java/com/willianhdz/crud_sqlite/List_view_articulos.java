package com.willianhdz.crud_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.github.fafaldo.fabtoolbar.widget.FABToolbarLayout;

import java.util.ArrayList;

public class List_view_articulos extends AppCompatActivity{

    ListView listViewPersonas;
    ArrayAdapter adaptador;
    SearchView searchView;
    ListView listView;
    ArrayList<String> list;
    ArrayAdapter adapter;

    String[] version =
            {"Aestro","Blender","CupCake","Donut","Eclair","Froyo","GingerBread","HoneyComb", "IceCream Sandwich",
                    "Jelly Bean","Kitkat","Lolipop","Marshmallow","Nought","Oreo"};


    ConexionSQLite conexion = new ConexionSQLite(this);
    Dto datos = new Dto();

    //para la toolbar
    private FABToolbarLayout morph;

    //pruebas
    private FloatingActionMenu menu;
    private FloatingActionButton item1, item2, item3, item4, item5;

    //instancia de la clase modal
    modal_Toast_Custom modal = new modal_Toast_Custom();
    //Hasata aca ////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_articulos);

        listViewPersonas= (ListView) findViewById(R.id.listViewPersonas);
        searchView = (SearchView) findViewById(R.id.searchView);

        //searchView —— findViewByld(R.id.searchView),
        //conexion. consultaListaAdiculos(),
        //ArrayAdapter<CharSequence> adaptador —— new ArrayAdapter(this, android. R.layout. simple spinner item, IistaArticulos);

        adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1,
                conexion.consultaListaArticulos1());
        listViewPersonas.setAdapter(adaptador);



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                //if(conexion.consultaListaArticulos1().contains(s)){

        /*if(list. contains(s)){ adapter.getFiIter().filter(s),

        return true,
} */


                String text = s;
                adaptador.getFilter().filter(text);
                return false;

            }

        });

        listViewPersonas.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long I){

                String informacion = "Codigo: " + conexion.consultaListaArticulos().get(pos).getDescripcion() + "\n";
                informacion += "Descripcion: " +
                        "" + conexion.consultaListaArticulos().get(pos).getDescripcion() + "\n";
                informacion += "Precio: " + conexion.consultaListaArticulos().get(pos).getPrecio();

                Dto articulos = conexion.consultaListaArticulos().get(pos);
                Intent intent = new Intent(List_view_articulos.this, Detalles_articulos.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("articulo", articulos);
                intent.putExtras(bundle);
                startActivity(intent);

            }

        });

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
                    Toast.makeText(List_view_articulos.this, "Menu abierto", Toast.LENGTH_SHORT);
                }else {
                    Toast.makeText(List_view_articulos.this, "Menu cerrado", Toast.LENGTH_SHORT);
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
                modal.dialogAbout(List_view_articulos.this);
            }
        });

        item3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(List_view_articulos.this, MainActivity.class);
                startActivity(intent);

                modal.dialogConfirmCustom2(List_view_articulos.this);
            }
        });

        item4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(List_view_articulos.this, Lista_articulos_recyclerview.class);
                startActivity(intent);

                modal.dialogConfirmCustom2(List_view_articulos.this);
            }
        });

        item5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(List_view_articulos.this, List_view_articulos.class);
                startActivity(intent);

                modal.dialogConfirmCustom2(List_view_articulos.this);
            }
        });


        //Finaliza bloque para preuba de l menu


    }

}
