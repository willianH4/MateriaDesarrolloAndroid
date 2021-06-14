package com.willianhdz.crudmysql.ui.usuario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.willianhdz.crudmysql.MainActivity;
import com.willianhdz.crudmysql.R;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    EditText et_correo, et_clave;
    Button btn_ingresar, btn_nuevo;
    String correo, clave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        et_correo = (EditText) findViewById(R.id.et_correo);
        et_clave = (EditText) findViewById(R.id.et_clave);
        btn_ingresar = (Button) findViewById(R.id.btn_ingresar);

        recuperarDatos();

        btn_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                correo=et_correo.getText().toString();
                clave=et_clave.getText().toString();
                if (!correo.isEmpty() && !clave.isEmpty()){
                    validarUsuario("http://192.168.108.2/service2020/validarUsuario.php");
                }else{
                    Toast.makeText(Login.this, "No se permiten campos vacios", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void validarUsuario(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!response.isEmpty()){
                    guardarDatos();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(Login.this, "Usuario o contraseña incorrecta", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Login.this, "Error de conexion ", Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("correo", correo);
                parametros.put("clave", et_clave.getText().toString());
                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    //metodo para guardar correo y clave con Sharedpreferences
    private void guardarDatos(){
        SharedPreferences preferences = getSharedPreferences("Login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("correo", correo);
        //editor.putString("clave", clave);
        editor.putBoolean("sesion", true);
        editor.commit();
    }

    //Metodo para recuperar los valores
    private void recuperarDatos(){
        SharedPreferences preferences = getSharedPreferences("Login", Context.MODE_PRIVATE);
        et_correo.setText(preferences.getString("correo", ""));
        //et_clave.setText(preferences.getString("clave", ""));
    }
}