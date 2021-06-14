package com.willianhdz.menuopcionescontextopopup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv2 = findViewById(R.id.tv2);
        registerForContextMenu(tv2);
    }

    //Metodo que infla el menu de opciones
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones, menu);
        return true;
    }

    //Metodo para controlar las acciones de cada opcion del menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.item1:
                //instruciones o acciones a ejecutar
                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
                break;

            case R.id.item2:
                //instrucciones a ejecutar
                Toast.makeText(this, "About", Toast.LENGTH_SHORT).show();
                break;

            case R.id.item3:
                Toast.makeText(this, "Salir", Toast.LENGTH_SHORT).show();
                break;

            default:
                Toast.makeText(this, "Elige una opcion", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }


    //Inicia menu de contexto
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_contexto, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.editar:
                //codigo a ejecutar
                Toast.makeText(this, "Salir", Toast.LENGTH_SHORT).show();
                break;

            case R.id.eliminar:
                Toast.makeText(this, "Salir", Toast.LENGTH_SHORT).show();
                break;

            default:
                Toast.makeText(this, "Salir", Toast.LENGTH_SHORT).show();
        }

        return super.onContextItemSelected(item);
    }

    //inicio menu popup
    public void menuPopUp(View v){
        TextView textView = findViewById(R.id.tv3);
        PopupMenu popupMenu = new PopupMenu(this, textView);
        popupMenu.getMenuInflater().inflate(R.menu.menu_popup, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()){
                    //acciones
                    case R.id.nuevo:
                        //acciones
                        Toast.makeText(MainActivity.this, "Nuevo", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.update:
                        //acciones
                        Toast.makeText(MainActivity.this, "Salir", Toast.LENGTH_SHORT).show();
                        break;

                    default:
                        Toast.makeText(MainActivity.this, "Salir", Toast.LENGTH_SHORT).show();
                }

                return true;
            }
        });

        popupMenu.show();
    }

}