package com.willianhdz.miscontactos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toolbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Contacto> contactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Toolbar miActionBar = findViewById(R.id.miActionBar);

        contactos = new ArrayList<Contacto>();

        //Lisat de objetos
        contactos.add(new Contacto("Molina", "1711-1112", "molina@yahoo.com"));
        contactos.add(new Contacto("Hernandez", "7666-2211", "hernandez@yahoo.com"));
        contactos.add(new Contacto("Jimenez", "2828-2211", "jimenez@yahoo.com"));
        contactos.add(new Contacto("Sandra", "7119-7799", "sandra@yahoo.com"));
        contactos.add(new Contacto("Aracely", "7907-2321", "aracely@yahoo.com"));

        final ArrayList<String> nombresContacto = new ArrayList<>();

        //Recorriendo el arreglo con un ForeAch
        for (Contacto contacto : contactos){
            nombresContacto.add(contacto.getNombre());
        }

//        ListView listView = findViewById(R.id.lstContactos);
        ListView lstContactos = findViewById(R.id.lstContactos);
        lstContactos.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombresContacto));
//        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombresContacto));

        lstContactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                Intent intent = new Intent(MainActivity.this, DetalleContacto.class);
                intent.putExtra(getResources().getString(R.string.pnombre), contactos.get(i).getNombre());
                intent.putExtra(getResources().getString(R.string.ptelefono), contactos.get(i).getTelefono());
                intent.putExtra(getResources().getString(R.string.pemail), contactos.get(i).getEmail());

                startActivity(intent);

                //Eliminar la actividad cuando vayamos a otra actividad con la instruccion finish()
                finish();
            }
        });
    }
}