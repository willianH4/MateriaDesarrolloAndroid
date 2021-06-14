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

    private EditText ethoras, etnombre;
    private RadioButton rb1, rb2, rb3, rb4;
    private Button btnCalSalario, btnNew;
    private TextView tvmonto, tvnombre;

    //Instancia de la clase
    Empleado empleado = new Empleado();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio2);

        ethoras = (EditText)findViewById(R.id.ethoras);
        etnombre = (EditText)findViewById(R.id.etnombre);
        rb1 = (RadioButton)findViewById(R.id.rb1);
        rb2 = (RadioButton)findViewById(R.id.rb2);
        rb3 = (RadioButton)findViewById(R.id.rb3);
        rb4 = (RadioButton)findViewById(R.id.rb4);
        btnCalSalario = (Button)findViewById(R.id.btnCalSalario);
        btnNew = (Button)findViewById(R.id.btnNew);

        tvmonto = (TextView)findViewById(R.id.tvmonto);
        tvnombre = (TextView)findViewById(R.id.tvnombre);

        btnCalSalario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo code here
                if (ethoras.length() == 0) {
                    ethoras.setError("Campo obligatorio");
                }else if (etnombre.length() == 0){
                    etnombre.setError("Campo obligatorio");
                }else {
                    double ht = Double.parseDouble(ethoras.getText().toString());
                    String nombre = etnombre.getText().toString();

                    empleado.setHt(ht);
                    empleado.setNombre(nombre);

                    if (rb1.isChecked() == true){
                        tvmonto.setText(String.valueOf(empleado.SueldoA()));
                        tvnombre.setText(empleado.nombre);
                        Toast.makeText(Ejercicio2.this, "Es un empleado categoria A, tiene un pago por hora de $ 7", Toast.LENGTH_SHORT).show();
                    }else if (rb2.isChecked() == true){
                        tvmonto.setText(String.valueOf(empleado.SueldoB()));
                        tvnombre.setText(empleado.nombre);
                        Toast.makeText(Ejercicio2.this, "Es un empleado categoria B, tiene un pago por hora de $ 8.50", Toast.LENGTH_SHORT).show();
                    }else if (rb3.isChecked() == true){
                        tvmonto.setText(String.valueOf(empleado.SueldoC()));
                        tvnombre.setText(empleado.nombre);
                        Toast.makeText(Ejercicio2.this, "Es un empleado categoria C, tiene un pago por hora de $ 10", Toast.LENGTH_SHORT).show();
                    }else if (rb4.isChecked() == true){
                        tvmonto.setText(String.valueOf(empleado.SueldoD()));
                        tvnombre.setText(empleado.nombre);
                        Toast.makeText(Ejercicio2.this, "Es un empleado categoria D, tiene un pago por hora de $ 12.50", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo code here
                ethoras.setText(null);
                etnombre.setText(null);
                tvnombre.setText(null);
                tvmonto.setText("0.0");

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