package org.example.radiogroupyradiobutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText et1, et2;
    private TextView tv3;
    private RadioButton r1, r2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText)findViewById(R.id.et1);
        et2 = (EditText)findViewById(R.id.et2);
        tv3 = (TextView)findViewById(R.id.tv3);
        r1 = (RadioButton)findViewById(R.id.rb1);
        r2 = (RadioButton)findViewById(R.id.rb2);
    }

//Este metodo se ejecutara al presionar el boton
    public void operar(View view){
        String valor1 = et1.getText().toString();
        String valor2 = et2.getText().toString();
        int numero1 = Integer.parseInt(valor1);
        int numero2 = Integer.parseInt(valor2);
        if (r1.isChecked() == true){
            int suma = numero1 + numero2;
            String resultado = String.valueOf(suma);
            tv3.setText(resultado);
        }else if (r2.isChecked() == true){
            int resta = numero1 - numero2;
            String resultado = String.valueOf(resta);
            tv3.setText(resultado);
        }
    }
}