package com.willianhdz.cine;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etedad, etprecio;
    private Button btnprecio, btnlimpiar;
    private TextView tvprecio;

//Instancia
Cine cines = new Cine();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etedad = (EditText)findViewById(R.id.etedad);
        etprecio = (EditText)findViewById(R.id.etprecio);
        btnlimpiar = (Button)findViewById(R.id.btnlimpiar);
        btnprecio = (Button)findViewById(R.id.btnprecio);

        tvprecio = (TextView)findViewById(R.id.tvprecio);


        btnprecio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo code here
                if (etedad.length() == 0) {
                    etedad.setError("Campo obligatorio");
                }else if (etprecio.length() == 0){
                    etprecio.setError("Campo obligatorio");
                }else {
                    double precio = Double.parseDouble(etprecio.getText().toString());
                    int edad = Integer.parseInt(etedad.getText().toString());

                    cines.setPrecio(precio);
                    cines.setEdad(edad);

                    tvprecio.setText(String.valueOf(cines.total()));

                    if (cines.desc() >= cines.total()){
                        Toast.makeText(MainActivity.this, "Felicidades tienes un descuento del 50% ", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(MainActivity.this, "No tiene descuento por ahora :(", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnlimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo code here
                etprecio.setText(null);
                etedad.setText(null);
                tvprecio.setText("0.0");
            }
        });

    }

}