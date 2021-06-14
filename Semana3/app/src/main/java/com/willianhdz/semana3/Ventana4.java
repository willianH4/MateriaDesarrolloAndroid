package com.willianhdz.semana3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class Ventana4 extends AppCompatActivity {

    private Spinner spinner;
    private EditText et1, et2;
    private TextView tv3;
    private Button btnOperar3;

    Operaciones_Basicas ope = new Operaciones_Basicas();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana4);

        et1 = (EditText)findViewById(R.id.et1);
        et2 = (EditText)findViewById(R.id.et2);
        tv3 = (TextView)findViewById(R.id.tv3);

        spinner = (Spinner) findViewById(R.id.spinner);

        //declarando un arreglo
        String[] opciones = {"Sumar", "Restar", "Multiplicar", "Dividir"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, opciones);

        spinner.setAdapter(adapter);

        btnOperar3 = (Button) findViewById(R.id.btnOperar3);
        btnOperar3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String seleccionar = spinner.getSelectedItem().toString();
                if (et1.length() == 0) {
                    et1.setError("Campo oblogatorio");
                } else if (et2.length() == 0) {
                    et2.setError("Campo oblogatorio");
                }   if(seleccionar.equals("Sumar") ){
                    double n1 = Double.parseDouble(et1.getText().toString());
                    double n2 = Double.parseDouble(et2.getText().toString());
                    ope.setN1(n1);
                    ope.setN2(n2);
                    tv3.setText(String.valueOf("La Suma es: "+ope.Sumar()));

                } if (seleccionar.equals("Restar")){
                    double n1 = Double.parseDouble(et1.getText().toString());
                    double n2 = Double.parseDouble(et2.getText().toString());
                    ope.setN1(n1);
                    ope.setN2(n2);
                    tv3.setText(String.valueOf("La Resta es: "+ope.Restar()));
                } if (seleccionar.equals("Multiplicar")){
                    double n1 = Double.parseDouble(et1.getText().toString());
                    double n2 = Double.parseDouble(et2.getText().toString());

                    ope.setN1(n1);
                    ope.setN2(n2);
                    tv3.setText(String.valueOf("La Multiplicación es: "+ope.Multiplicar()));
                }
                 if (seleccionar.equals("Dividir")){
                    double n1 = Double.parseDouble(et1.getText().toString());
                    double n2 = Double.parseDouble(et2.getText().toString());

                    ope.setN1(n1);
                    ope.setN2(n2);

                    tv3.setText(String.valueOf("La Divición es: "+ope.Dividir()));
                }
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

        if (id == R.id.menu_v1){
            //Toast.makeText(this, "Has hecho clic en la opcion 1 del menu", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, Ventana1.class);
            intent.putExtra("profe", "manuel gamez");
            startActivity(intent);
            return true;
        }else if (id == R.id.menu_v2){
            //acciones
            Intent intent = new Intent(this, Ventana2.class);
            intent.putExtra("Clic para", "Ingresar");
            startActivity(intent);
            return true;
        }else if (id == R.id.menu_v3){
            //acciones
            Intent intent = new Intent(this, Ventana3.class);
            intent.putExtra("Clic para", "Ingresar");
            startActivity(intent);
            return true;
        }else if (id == R.id.menu_v4){
            //acciones
            Intent intent = new Intent(this, Ventana4.class);
            intent.putExtra("Clic para", "Ingresar");
            startActivity(intent);
            return true;
        }else if (id == R.id.menu_v5){
            //acciones
            Intent intent = new Intent(this, Ventana5.class);
            intent.putExtra("Clic para", "Ingresar");
            startActivity(intent);
            return true;
        }else if (id == R.id.menu_v6){
            //acciones
            Intent intent = new Intent(this, Ventana6.class);
            intent.putExtra("Clic para", "Ingresar");
            startActivity(intent);
            return true;
        }else if (id == R.id.menu_v7){
            //acciones
            Intent intent = new Intent(this, Ventana7.class);
            intent.putExtra("Clic para", "Ingresar");
            startActivity(intent);
            return true;
        }else if (id == R.id.menu_v8){
            //acciones
            Intent intent = new Intent(this, Ventana8.class);
            intent.putExtra("Clic para", "Ingresar");
            startActivity(intent);
            return true;
        }else if (id == R.id.menu_v9){
            //accions
            Intent intent = new Intent(this, Ventana9.class);
            intent.putExtra("Clic para", "Ingresar");
            startActivity(intent);
            return true;
        }else if (id == R.id.menu_v10){
            //acciones
            Intent intent = new Intent(this, Ventana10.class);
            intent.putExtra("Clic para", "Ingresar");
            startActivity(intent);
            return true;
        }else if (id == R.id.menu_v11){
            //acciones
            Intent intent = new Intent(this, Ventana11.class);
            intent.putExtra("Clic para", "Ingresar");
            startActivity(intent);
            return true;
        }else if (id == R.id.menu_v12){
            //acciones
            Intent intent = new Intent(this, Ventana12.class);
            intent.putExtra("Clic para", "Ingresar");
            startActivity(intent);
            return true;
        }else if (id == R.id.menu_v13){
            //acciones
            Intent intent = new Intent(this, Ventana13.class);
            intent.putExtra("Clic para", "Ingresar");
            startActivity(intent);
            return true;
        }else if (id == R.id.menu_v14){
            //acciones
            Intent intent = new Intent(this, Ventana14.class);
            intent.putExtra("Clic para", "Ingresar");
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    //Cierra menu

/*
    public void operar(View view){
        String valor1 = et1.getText().toString();
        String valor2 = et2.getText().toString();
        int numero1 = Integer.parseInt(valor1);
        int numero2 = Integer.parseInt(valor2);
        String seleccionar = spinner.getSelectedItem().toString();

        //condicional segun opcion seleccionadaa
        if (seleccionar.equals("sumar")){
            int suma = numero1 + numero2;
            String resultado = String.valueOf(suma);
            tv3.setText(resultado);

        }else if (seleccionar.equals("restar")){
            int resta = numero1 - numero2;
            String resultado = String.valueOf(resta);
            tv3.setText(resultado);

        }else if (seleccionar.equals("multiplicar")){
            int multiplica = numero1 * numero2;
            String resultado = String.valueOf(multiplica);
            tv3.setText(resultado);

        }else if (seleccionar.equals("dividir")){
            int division = numero1 / numero2;
            String resultado = String.valueOf(division);
            tv3.setText(resultado);
        }
    }

 */
}