package com.willianhdz.formulario;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Instancia de clase
    DatosContacto contacto = new DatosContacto();

    //Variables para captura de los valores selecionados en el picker de fecha
    private int dia, mes, anio;

    //Controles del formulario
    EditText et_Date, et_Nombre, et_Telefono, et_Email, et_Descripcion;
    Button btnEnviar, btnLimpiar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEnviar = findViewById(R.id.btnEnviar);
        btnLimpiar = findViewById(R.id.btnLimpiar);
        et_Date = findViewById(R.id.et_Date);
        et_Nombre = findViewById(R.id.et_Nombre);
        et_Telefono = findViewById(R.id.et_Telefono);
        et_Email = findViewById(R.id.et_Email);
        et_Descripcion = findViewById(R.id.et_Descripcion);

        //Metodo para recuperar datos al abrir la app
        recuperarDatos();

        et_Date.setOnClickListener(this);
        btnLimpiar.setOnClickListener(this);
        btnEnviar.setOnClickListener(this);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
 //Metodo de escucha para nuestro EdiText
    @Override
    public void onClick(View v) {

        if (v == et_Date){
           final Calendar cal = Calendar.getInstance();
           dia = cal.get(Calendar.DAY_OF_MONTH);
           mes = cal.get(Calendar.MONTH);
           anio = cal.get(Calendar.YEAR);

           DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
               //Metodo para setear la fecha y pintarla en nuestro EdiText
               @Override
               public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                   et_Date.setText(dayOfMonth+"/"+(month+1)+"/"+year);
               }
           }
           ,dia,mes,anio);
           datePickerDialog.show();

       }else if (v == btnEnviar){

           contacto.nombre = et_Nombre.getText().toString();
           contacto.fecha = et_Date.getText().toString();
           contacto.telefono = et_Telefono.getText().toString();
           contacto.email = et_Email.getText().toString();
           contacto.desc = et_Descripcion.getText().toString();

            guardarPreferencias();

               Intent intent = new Intent(this, MainActivity2.class);
               intent.putExtra("nombre", contacto.getNombre());
               intent.putExtra("fecha", contacto.getFecha());
               intent.putExtra("telefono", contacto.getTelefono());
               intent.putExtra("email", contacto.getEmail());
               intent.putExtra("desc", contacto.getDesc());

               startActivity(intent);

       }else if (v == btnLimpiar){
            limpiar();
            Context context = getApplicationContext();
            CharSequence text = "Campos limpiados correctamente!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
       }
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
        if (id == R.id.Salir){
          finishAffinity();
        }
        return super.onOptionsItemSelected(item);
    }
    //Cierra menu


    public void guardarPreferencias(){
        SharedPreferences preferences = getSharedPreferences("formulario", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("nombre", contacto.getNombre());
        editor.putString("fecha", contacto.getFecha());
        editor.putString("telefono", contacto.getTelefono());
        editor.putString("email", contacto.getEmail());
        editor.putString("descripcion", contacto.getDesc());
        editor.putBoolean("sesion", true);
        editor.commit();
    }

    public void recuperarDatos(){
        SharedPreferences preferences = getSharedPreferences("formulario", MODE_PRIVATE);
        et_Nombre.setText(preferences.getString("nombre", ""));
        et_Date.setText(preferences.getString("fecha", ""));
        et_Telefono.setText(preferences.getString("telefono", ""));
        et_Email.setText(preferences.getString("email", ""));
        et_Descripcion.setText(preferences.getString("descripcion", ""));
    }

    public void limpiar(){
        et_Nombre.setText("");
        et_Date.setText("");
        et_Telefono.setText("");
        et_Email.setText("");
        et_Descripcion.setText("");
    }

}