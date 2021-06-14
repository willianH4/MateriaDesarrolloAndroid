package com.willianhdz.semana3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Ventana7 extends AppCompatActivity {

    private EditText et1;
    private int num;
    private Button btnA;


    Adivinado ramdons = new Adivinado();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana7);

        et1 = (EditText) findViewById(R.id.et1);
        num = (int) (Math.random() * 100001);
        String cadena = String.valueOf(num);
        Toast notificacion = Toast.makeText(this, cadena, Toast.LENGTH_LONG);
        notificacion.show();
        btnA = (Button) findViewById(R.id.btnA);
        btnA.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (et1.length() == 0) {
                    et1.setError("Campo oblogatorio");
                } else {
                    int n1 = Integer.parseInt(et1.getText().toString());
                    int n2 = num;
                    ramdons.setValoringresado(n1);
                    ramdons.setNum(n2);
                    controlar(view);

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


    public void controlar(View v){

      String valorIngresado = String.valueOf(Integer.parseInt(String.valueOf(ramdons.getValoringresado())));
        int valor = Integer.parseInt((valorIngresado));

        if (valor == num){
            Toast notificacion = Toast.makeText(this, "Muy bien recordaste el numero mostrado",Toast.LENGTH_LONG);
            notificacion.show();
        }else {
            Toast notificacion = Toast.makeText(this, "Lo siento no es el numero que se mostro", Toast.LENGTH_LONG);
            notificacion.show();
        }
    }


}