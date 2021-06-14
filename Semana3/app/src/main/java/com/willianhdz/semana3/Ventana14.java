package com.willianhdz.semana3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Ventana14 extends AppCompatActivity {

    //Declaro variables...
    private EditText numero;
    private Button jugar, reiniciar, atras;
    private TextView mensaje, mensaje2, mensaje3;
    private int aleatorio;
    private int intento = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana14);

        //Inicializacion de variables...
        numero = (EditText) findViewById(R.id.txtnumero);
        jugar = (Button) findViewById(R.id.btnjugar);
        reiniciar = (Button) findViewById(R.id.btnreiniciar);
        mensaje = (TextView) findViewById(R.id.lblmensaje);
        mensaje2 = (TextView) findViewById(R.id.lblmensaje2);
        mensaje3 = (TextView) findViewById(R.id.lblmensaje3);
        reiniciar.setEnabled(false);

        //Asigno el método donde se genera el numero aleatorio a una variable...
        aleatorio = crearAleatorio();

        //Funcion que genera método donde se desarrolla lo que sucede cuando se
        //presiona el boton "Jugar"...
        jugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //declaro una variable para asignarle el cambio de tipo de caracter...
                int nroUsuario;
                //Convierte y asigna el caracter ingresado de cadena a entero...
                nroUsuario = Integer.parseInt(numero.getText().toString());
                if (nroUsuario < 0 || nroUsuario > 50) {
                    mensaje.setText("Este no es un numero valido");
                } else if (aleatorio < nroUsuario) {
                    mensaje.setText("Ingrese un numero más bajo");
                } else {
                    mensaje.setText("Ingrese un numero más alto");
                }
                if (aleatorio == nroUsuario) {
                    jugar.setEnabled(false);
                    reiniciar.setEnabled(true);
                    mensaje.setText("Felicitaciones Has Ganado!");
                    mensaje3.setText("El numero ganador es: " + aleatorio);
                }
                intento = intento - 1;
                mensaje2.setText("Intentos restantes: " + intento);

                if (intento == 0 && aleatorio != nroUsuario) {
                    jugar.setEnabled(false);
                    reiniciar.setEnabled(true);
                    mensaje.setText("Perdiste!");
                    mensaje2.setText("");
                    mensaje3.setText("El numero ganador es: " + aleatorio);
                }

            }
        });
        reiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intento = 20;
                aleatorio = crearAleatorio();
                jugar.setEnabled(true);
                reiniciar.setEnabled(false);
                numero.setText("");
                mensaje.setText("");
                mensaje2.setText("");
                mensaje3.setText("");
            }
        });

    }

    private int crearAleatorio() {
        return (int) (Math.random() * 50 + 1);
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
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("profe", "manuel gamez");
            startActivity(intent);
            return true;
        }else if (id == R.id.menu_v2){
            //Toast.makeText(this, "Has hecho clic en la opcion 1 del menu", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, Ventana1.class);
            intent.putExtra("profe", "manuel gamez");
            startActivity(intent);
            return true;
        }else if (id == R.id.menu_v3){
            //acciones
            Intent intent = new Intent(this, Ventana2.class);
            intent.putExtra("Clic para", "Ingresar");
            startActivity(intent);
            return true;
        }else if (id == R.id.menu_v4){
            //acciones
            Intent intent = new Intent(this, Ventana3.class);
            intent.putExtra("Clic para", "Ingresar");
            startActivity(intent);
            return true;
        }else if (id == R.id.menu_v5){
            //acciones
            Intent intent = new Intent(this, Ventana4.class);
            intent.putExtra("Clic para", "Ingresar");
            startActivity(intent);
            return true;
        }else if (id == R.id.menu_v6){
            //acciones
            Intent intent = new Intent(this, Ventana5.class);
            intent.putExtra("Clic para", "Ingresar");
            startActivity(intent);
            return true;
        }else if (id == R.id.menu_v7){
            //acciones
            Intent intent = new Intent(this, Ventana6.class);
            intent.putExtra("Clic para", "Ingresar");
            startActivity(intent);
            return true;
        }else if (id == R.id.menu_v8){
            //acciones
            Intent intent = new Intent(this, Ventana7.class);
            intent.putExtra("Clic para", "Ingresar");
            startActivity(intent);
            return true;
        }else if (id == R.id.menu_v9){
            //acciones
            Intent intent = new Intent(this, Ventana8.class);
            intent.putExtra("Clic para", "Ingresar");
            startActivity(intent);
            return true;
        }else if (id == R.id.menu_v10){
            //accions
            Intent intent = new Intent(this, Ventana9.class);
            intent.putExtra("Clic para", "Ingresar");
            startActivity(intent);
            return true;
        }else if (id == R.id.menu_v11){
            //acciones
            Intent intent = new Intent(this, Ventana10.class);
            intent.putExtra("Clic para", "Ingresar");
            startActivity(intent);
            return true;
        }else if (id == R.id.menu_v12){
            //acciones
            Intent intent = new Intent(this, Ventana11.class);
            intent.putExtra("Clic para", "Ingresar");
            startActivity(intent);
            return true;
        }else if (id == R.id.menu_v13){
            //acciones
            Intent intent = new Intent(this, Ventana12.class);
            intent.putExtra("Clic para", "Ingresar");
            startActivity(intent);
            return true;
        }
        else if (id == R.id.menu_v14){
            //acciones
            Intent intent = new Intent(this, Ventana13.class);
            intent.putExtra("Clic para", "Ingresar");
            startActivity(intent);
            return true;
        }else if (id == R.id.menu_v15){
            //acciones
            Intent intent = new Intent(this, Ventana14.class);
            intent.putExtra("Clic para", "Ingresar");
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    //Cierra menu

}