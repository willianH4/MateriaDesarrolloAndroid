package com.willianhdz.lab1_semana4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Ejercicio2 extends AppCompatActivity {

    private EditText etdeposito, etmeses;
    private Button btnCalcular, btnNew;
    private TextView tvganancia;

    //Instancia de la clase
    Empleado empleado = new Empleado();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio2);

        etdeposito = findViewById(R.id.etdeposito);
        etmeses = findViewById(R.id.etmeses);
        btnCalcular = findViewById(R.id.btnCalcular);
        btnNew = findViewById(R.id.btnNew);

        tvganancia = findViewById(R.id.tvganancia);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo code here
                if (etdeposito.length() == 0) {
                    etdeposito.setError("Campo obligatorio");
                }else if (etmeses.length() == 0){
                    etmeses.setError("Campo obligatorio");
                }else {
                    double deposito = Double.parseDouble(etdeposito.getText().toString());
                    int meses = Integer.parseInt(etmeses.getText().toString());

                    empleado.setDeposito(deposito);
                    empleado.setMeses(meses);

                    tvganancia.setText(String.valueOf(empleado.intereses()));
                }
            }
        });

        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo code here
                etdeposito.setText(null);
                etmeses.setText(null);
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
            intent.putExtra("examen", "semana4");
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