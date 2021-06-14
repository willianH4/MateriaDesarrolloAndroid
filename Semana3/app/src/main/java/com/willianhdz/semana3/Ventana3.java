package com.willianhdz.semana3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class Ventana3 extends AppCompatActivity {

    //variables
    private EditText et1, et2;
    private TextView tv3, tv4;
    private CheckBox cb1, cb2;
    private Button btnOperar2;

    Operaciones_Basicas restarsumar = new Operaciones_Basicas();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana3);


        et1 = (EditText)findViewById(R.id.et1);
        et2 = (EditText)findViewById(R.id.et2);
        tv3 = (TextView)findViewById(R.id.tv3);
        tv4 = (TextView)findViewById(R.id.tv4);
        cb1 = (CheckBox)findViewById(R.id.cb1);
        cb2 = (CheckBox)findViewById(R.id.cb2);
        btnOperar2 = (Button) findViewById(R.id.btnOperar2);
        btnOperar2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (et1.length() == 0) {
                    et1.setError("Campo oblogatorio");
                } else if (et2.length() == 0) {
                    et2.setError("Campo oblogatorio");
                } else if(cb1.isChecked()== true && cb2.isChecked()== true ){
                    double n1 = Double.parseDouble(et1.getText().toString());
                    double n2 = Double.parseDouble(et2.getText().toString());
                    restarsumar.setN1(n1);
                    restarsumar.setN2(n2);
                   // tv3.setText(String.valueOf("\n La suma es: "+restarsumar.Sumar()+"\n La resta es: "+restarsumar.Restar()));
                    tv3.setText(String.valueOf("La suma es: "+restarsumar.Sumar()));
                    tv4.setText(String.valueOf("La resta es: "+restarsumar.Restar()));

                }
                if (cb1.isChecked() == true){
                    double n1 = Double.parseDouble(et1.getText().toString());
                    double n2 = Double.parseDouble(et2.getText().toString());
                    restarsumar.setN1(n1);
                    restarsumar.setN2(n2);
                    tv3.setText(String.valueOf("\nLa suma es: "+restarsumar.Sumar()));
                }else if (cb2.isChecked() == true){
                    double n1 = Double.parseDouble(et1.getText().toString());
                    double n2 = Double.parseDouble(et2.getText().toString());
                    String res = "";
                    restarsumar.setN1(n1);
                    restarsumar.setN2(n2);
                    tv4.setText(String.valueOf("\nLa resta es: "+restarsumar.Restar()));

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
        String resultado = "";

        if (cb1.isChecked() == true){
            int suma = numero1 + numero2;
            resultado = "La suma es: "+suma;
        }if (cb2.isChecked() == true){
            int resta = numero1 - numero2;
            resultado = resultado + "\nLa resta es: "+resta;
        }
        tv3.setText(resultado);
    }
 */
}