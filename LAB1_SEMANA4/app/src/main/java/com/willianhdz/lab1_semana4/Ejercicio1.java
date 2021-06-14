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

private EditText etnota1, etnota2, etnota3, etnota4, etnota5;
private Button btnCalcular, btnNuevo;
private TextView tvresultado;

//istamcia de la clase
    Alumno estudiante = new Alumno();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio1);

        etnota1 = (EditText)findViewById(R.id.etnota1);
        etnota2 = (EditText)findViewById(R.id.etnota2);
        etnota3 = (EditText)findViewById(R.id.etnota3);
        etnota4 = (EditText)findViewById(R.id.etnota4);
        etnota5 = (EditText)findViewById(R.id.etnota5);

        btnCalcular = (Button)findViewById(R.id.btnCalcular);
        btnNuevo = (Button)findViewById(R.id.btnNuevo);

        tvresultado = (TextView)findViewById(R.id.tvresultado);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo code here
                if (etnota1.length() == 0){
                    etnota1.setError("Campo obligatorio");
                }else if (etnota2.length() == 0){
                    etnota2.setError("Campo obliatorio");
                }else if (etnota3.length() == 0){
                    etnota3.setError("Campo obligatorio");
                }else if (etnota4.length() == 0) {
                    etnota4.setError("Campo obligatorio");
                }else if (etnota5.length() == 0) {
                    etnota5.setError("Campo obligatorio");
                }else {
                    double n1 = Double.parseDouble(etnota1.getText().toString());
                    double n2 = Double.parseDouble(etnota2.getText().toString());
                    double n3 = Double.parseDouble(etnota3.getText().toString());
                    double n4 = Double.parseDouble(etnota4.getText().toString());
                    double n5 = Double.parseDouble(etnota5.getText().toString());

                    estudiante.setN1(n1);
                    estudiante.setN2(n2);
                    estudiante.setN3(n3);
                    estudiante.setN4(n4);
                    estudiante.setN5(n5);

                    tvresultado.setText(String.valueOf(estudiante.total()));

                    if (estudiante.total() < 7){
                        Toast.makeText(Ejercicio1.this, "Lo sentimos has reprobado, mayor esfuerzo a la proxima :( ", Toast.LENGTH_SHORT).show();
                    }else if (estudiante.total() >= 7){
                        Toast.makeText(Ejercicio1.this, "Felicidades has aprobado!!!", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        btnNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo code here
                etnota1.setText(null);
                etnota2.setText(null);
                etnota3.setText(null);
                etnota4.setText(null);
                etnota5.setText(null);
                tvresultado.setText("0.0");

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
            intent.putExtra("profe", "manuel gamez");
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