package com.willianhdz.lab1_semana4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Ejercicio1 extends AppCompatActivity {

private EditText etCantidad;
private Button btnCalcular, btnNuevo;
private TextView tvcosto, tvventa, tvganancia;

//istamcia de la clase
    Programa progra = new Programa();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio1);

        etCantidad = findViewById(R.id.etCantidad);

        btnCalcular = findViewById(R.id.btnCalcular);
        btnNuevo = findViewById(R.id.btnNuevo);


        tvcosto = findViewById(R.id.tvcosto);
        tvventa = findViewById(R.id.tvventa);
        tvganancia = findViewById(R.id.tvganancia);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo code here
                if (etCantidad.length() == 0){
                    etCantidad.setError("Campo obligatorio");
                }else {
                    int cantidad = Integer.parseInt(etCantidad.getText().toString());

                    progra.setCantidad(cantidad);

                    tvventa.setText(String.valueOf(progra.costoVenta()));
                    tvcosto.setText(String.valueOf(progra.costoReal()));
                    tvganancia.setText(String.valueOf(progra.ganancia()));

                }
            }
        });

        btnNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo code here
                etCantidad.setText(null);
                tvventa.setText("0.0");
                tvcosto.setText("0.0");
                tvganancia.setText("0.0");

            }
        });
    }

    //Inicia menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == R.id.opcion1){
            //Toast.makeText(this, "Has hecho clic en la opcion 1 del menu", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, Ejercicio1.class);
            intent.putExtra("Alumno", "Desconocido");
            startActivity(intent);
            return true;

        }else if (id == R.id.opcion2){
            //acciones
            Intent intent = new Intent(this, Ejercicio2.class);
            intent.putExtra("Clic para", "Ingresar");
            startActivity(intent);
            return true;

        }else if (id == R.id.opcion3) {
            //acciones
            Intent intent = new Intent(this, AcercaDe.class);
            intent.putExtra("Clic para", "Ingresar");
            startActivity(intent);
            return true;
    }

        return super.onOptionsItemSelected(item);
    }
    //Cierra menu

}