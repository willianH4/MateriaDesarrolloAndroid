package org.willianhdz.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText et1, et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText)findViewById(R.id.et1);
        et2 = (EditText)findViewById(R.id.et2);
    }

    public void grabar(View v){
        String nombre = et1.getText().toString();
        String datos = et2.getText().toString();
        SharedPreferences preferencias = getSharedPreferences("agenda", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencias.edit();
        editor.putString(nombre, datos);
        editor.commit();
        Toast.makeText(this, "Datos grabados", Toast.LENGTH_LONG).show();
    }

    public void recuperar(View v){
        String nombre  = et1.getText().toString();
        SharedPreferences prefe  = getSharedPreferences("agenda", Context.MODE_PRIVATE);
        String d = prefe.getString(nombre, "");
        if (d.length() == 0){
            Toast.makeText(this, "No existe el nombre en la agenda",Toast.LENGTH_LONG).show();
        }
        else {
            et2.setText(d);
        }
    }
}