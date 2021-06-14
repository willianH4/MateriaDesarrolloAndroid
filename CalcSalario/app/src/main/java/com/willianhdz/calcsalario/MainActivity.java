package com.willianhdz.calcsalario;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etNombreTrabaja, etHorasTrabajadas, etPagoHora;
    private Button btnCalcular, btnNuevo, btnSalir;
    private TextView tvResultado1, tvResultado2;

    //Instancua de la clase
    SueldoEmpleado sueldo = new SueldoEmpleado();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNombreTrabaja = findViewById(R.id.etNombreTrabaja);
        etHorasTrabajadas = findViewById(R.id.etHorasTrabajadas);
        etPagoHora = findViewById(R.id.etPagoHora);

        btnCalcular = findViewById(R.id.btnCalcular);
        btnNuevo = findViewById(R.id.btnNuevo);
        btnSalir = findViewById(R.id.btnSalir);

        tvResultado1 = findViewById(R.id.tvResultado1);
        tvResultado2 = findViewById(R.id.tvResultado2);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo code here
                if (etNombreTrabaja.length() == 0){
                    etNombreTrabaja.setError("Campo obligatorio");
                }else if (etHorasTrabajadas.length() == 0){
                    etHorasTrabajadas.setError("Campo obliatorio");
                }else if (etPagoHora.length() == 0){
                    etPagoHora.setError("Campo obligatorio");
                }else {
                    String nombre = etNombreTrabaja.getText().toString();
                    double ht = Double.parseDouble(etHorasTrabajadas.getText().toString());
                    double ph = Double.parseDouble(etPagoHora.getText().toString());

                    sueldo.setNombre(nombre);
                    sueldo.setHorasTrabajadas(ht);
                    sueldo.setPagoHora(ph);

                    tvResultado1.setText(String.valueOf(sueldo.sueldo()));
                    tvResultado2.setText(String.valueOf(sueldo.renta()));

                    Toast.makeText(MainActivity.this, ""+sueldo.nombre(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo code here
                etNombreTrabaja.setText(null);
                etHorasTrabajadas.setText(null);
                etPagoHora.setText(null);
                tvResultado1.setText("0.0");
                tvResultado2.setText("0.0");
            }
        });

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo code here
                finish();
            }
        });
    }
}