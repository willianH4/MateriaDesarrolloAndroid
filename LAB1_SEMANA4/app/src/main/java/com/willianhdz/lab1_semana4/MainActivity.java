package com.willianhdz.lab1_semana4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
            intent.putExtra("profe", "manuel gamez");
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
        }else if (id == R.id.opcion4){
            //acciones
             finishActivity();
             return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void finishActivity() {
        finish();
    }
    //Cierra menu

}