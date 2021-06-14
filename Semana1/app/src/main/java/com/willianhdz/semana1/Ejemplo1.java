package com.willianhdz.semana1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Ejemplo1 extends AppCompatActivity {

    //Todas las variables son de tipo global y son reconocidas o se pueden
    //utilizar dentro de toda la clase java.MainActivity

    private EditText numero1, numero2;
    private TextView resultado;
    String nombre = "Willian";

    //instancia
    Ejemplo1POO ejemplo = new Ejemplo1POO();

    //El metodo oncreate se ejecuta al momento de ejecutar la actividad (Activity)
    //Tenga en cuenta que todo el codigo o conjunto de instrucciones que se coloquen
    //dentro de los limites "{...}" de OnCreate sera ejecutado en ese preciso momento

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejemplo1);

        numero1 = (EditText)findViewById(R.id.txtnumero1);
        numero2 = (EditText)findViewById(R.id.txtnumero2);


        resultado = (TextView)findViewById(R.id.lblresultado);
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

        if (id == R.id.menu_v1){
            //Toast.makeText(this, "Has hecho clic en la opcion 1 del menu", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, Ejemplo1.class);
            intent.putExtra("profe", "manuel gamez");
            startActivity(intent);
            return true;
        }else if (id == R.id.menu_v2) {
            //acciones
            Intent intent = new Intent(this, Ejemplo2.class);
            intent.putExtra("Clic para", "Ingresar");
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    //Cierra menu

    public void sumar(View view){
        String valor1 = numero1.getText().toString();
        String valor2 = numero2.getText().toString();

        float a = Float.parseFloat(valor1);
        float b = Float.parseFloat(valor1);

        float suma = a + b;

        String res = String.valueOf(suma);
        resultado.setText(res);

        Toast msj = Toast.makeText(this, "El resultado de "+a+ "+"+b+"="+suma,
                Toast.LENGTH_LONG);
        msj.show();
    }

}