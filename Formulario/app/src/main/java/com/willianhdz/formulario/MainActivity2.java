package com.willianhdz.formulario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {

    Button btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btnVolver = findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(this);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String nom = intent.getStringExtra("nombre");
        String fec = intent.getStringExtra("fecha");
        String te = intent.getStringExtra("telefono");
        String cor = intent.getStringExtra("email");
        String des = intent.getStringExtra("desc");

        // Capture the layout's TextView and set the string as its text
        TextView tv1 = findViewById(R.id.tv1);
        tv1.setText(nom);
        TextView tv2 = findViewById(R.id.tv2);
        tv2.setText(fec);
        TextView tv3 = findViewById(R.id.tv3);
        tv3.setText(te);
        TextView tv4 = findViewById(R.id.tv4);
        tv4.setText(cor);
        TextView tv5 = findViewById(R.id.tv5);
        tv5.setText(des);
    }

    @Override
    public void onClick(View v) {
        if ( v == btnVolver){
            atras();
        }
    }

    public void atras(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}