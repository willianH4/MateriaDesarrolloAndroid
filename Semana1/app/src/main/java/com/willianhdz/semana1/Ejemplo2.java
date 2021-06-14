package com.willianhdz.semana1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Ejemplo2 extends AppCompatActivity implements View.OnClickListener {

    //Todas estas variables son del tipo global y son reconocidas o se pueden
    //Utilizar dentro de toda la clase java

    private Button b1, b2, b3, b4, b5, b6;
    private EditText numero1, numero2;
    private TextView resu;
    View focusView = null;
    String Cadena = "Alumno: Willian Adalberto Hernandez";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejemplo2);

        Toast mensaje = Toast.makeText(this, "Bienvenidos, "+Cadena, Toast.LENGTH_LONG);
        mensaje.show();

        numero1 = (EditText)findViewById(R.id.txtnumero1);
        numero2 = (EditText)findViewById(R.id.txtnumero2);
        focusView=numero1;
        focusView.requestFocus();

        b1 = (Button)findViewById(R.id.btnsumar);
        b2 = (Button)findViewById(R.id.btnrestar);
        b3 = (Button)findViewById(R.id.btnmultiplicar);
        b4 = (Button)findViewById(R.id.btndividir);
        b5 = (Button)findViewById(R.id.btnew);
        b6 = (Button)findViewById(R.id.btnacerca);

        resu = (TextView)findViewById(R.id.lblresultado);

        //bl.setOnClickListener((View.OnClickListener) this);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
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


    @Override
    public void onClick(View v) {
        String valor1 = ""; String valor2 = "";
        String mensaje1 = ""; String res = "";
        float a; float b; float c;

        switch (v.getId()){
            case R.id.btnsumar:
                valor1 = numero1.getText().toString();
                valor2 = numero2.getText().toString();
                if ((valor1 != "") && (!valor2.isEmpty())){
                    a = Float.parseFloat(valor1);
                    b = Float.parseFloat(valor2);
                    c = a + b;
                    res = String.valueOf(c);
                    mensaje1 = "La suma de " +a + "+" +b+ "=" +c;
                }else {
                    focusView = numero1;
                    focusView.requestFocus();
                    res = "0.0";
                    mensaje1 = "Debe completar el formulario";
                }
                break;
            case R.id.btnrestar:
                valor1 = numero1.getText().toString();
                valor2 = numero2.getText().toString();
                a = Float.parseFloat(valor1);
                b = Float.parseFloat(valor2);
                c = a - b;
                res = String.valueOf(c);
                mensaje1 = "La resta de " +a+ "-" +b+ "=" +c;
                break;

            case R.id.btnmultiplicar:
                valor1 = numero1.getText().toString();
                valor2 = numero2.getText().toString();
                a = Float.parseFloat(valor1);
                b = Float.parseFloat(valor2);
                c = a * b;
                res = String.valueOf(c);
                mensaje1 = "La multiplicacion de " +a+ "x" +b+ "=" +c;
                break;

            case R.id.btndividir:

                valor1 = numero1.getText().toString();
                valor2 = numero2.getText().toString();
                a = Float.parseFloat(valor1);
                b = Float.parseFloat(valor2);
                c = a / b;
                res = String.valueOf(c);
                mensaje1 = "La division de " +a+ "/" +b+ "=" +c;
                break;

            case R.id.btnacerca:
                mensaje1 = "Creado por: Willian Hernandez";
                res = "0.0";
                break;

            case R.id.btnew:
                numero1.setText("0,0");
                numero2.setText("0.0");
                res = "0.0";
                focusView = numero1;
                focusView.requestFocus();
                mensaje1 = "Ahora puede ingresar nuevos valores";
                break;

            default:
                break;

        }

        resu.setText(res);
        Toast mensaje = Toast.makeText(this, mensaje1, Toast.LENGTH_LONG);
        mensaje.show();
    }
}