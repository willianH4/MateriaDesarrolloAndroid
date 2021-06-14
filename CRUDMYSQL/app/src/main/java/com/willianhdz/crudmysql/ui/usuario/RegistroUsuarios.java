package com.willianhdz.crudmysql.ui.usuario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.willianhdz.crudmysql.MySingleton;
import com.willianhdz.crudmysql.R;
import com.willianhdz.crudmysql.Setting_VAR;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static java.security.AccessController.getContext;

public class RegistroUsuarios extends AppCompatActivity implements View.OnClickListener {

    private EditText et_idusuario, et_nombre, et_apellidos, et_correo, et_usuario, et_clave, et_respuesta;
    private Spinner sp_tipo, sp_estadoUsuario, sp_preguntaSecreta;
    private Button btnSave, btnNew;
    String datoSelect = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuarios);

        //Declaracion de los componentes o controles de la interfaz
        et_idusuario = findViewById(R.id.et_idusuario);
        et_nombre = findViewById(R.id.et_nombre);
        et_apellidos = findViewById(R.id.et_apellidos);
        et_correo = findViewById(R.id.et_correo);
        et_usuario = findViewById(R.id.et_usuario);
        et_clave = findViewById(R.id.et_clave);
        et_respuesta = findViewById(R.id.et_respuesta);

        //Spinner cargados desde el recurso Strings
        sp_tipo = findViewById(R.id.sp_tipo);
        sp_estadoUsuario = findViewById(R.id.sp_estadoUsuario);
        sp_preguntaSecreta = findViewById(R.id.sp_preguntaSecreta);

        //PARA LOS BOTONES
        btnNew = findViewById(R.id.btnNew);
        btnSave = findViewById(R.id.btnSave);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.preguntaSecreta));
                sp_preguntaSecreta.setAdapter(adapter);
        sp_preguntaSecreta.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(sp_preguntaSecreta.getSelectedItemPosition()>0) {
                    datoSelect = sp_preguntaSecreta.getSelectedItem().toString();
                }else{
                    datoSelect = "";
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        btnSave = findViewById(R.id.btnSave);
        btnNew = findViewById(R.id.btnNew);
        btnSave.setOnClickListener(this);
        btnNew.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSave:
//save_server(getContext(),);
                String id = et_idusuario.getText().toString();
                String nombre = et_nombre.getText().toString();
                String apellidos = et_apellidos.getText().toString();
                String correo = et_correo.getText().toString();
                String usuario = et_usuario.getText().toString();
                String clave = et_clave.getText().toString();
                String respuesta = et_respuesta.getText().toString();
                if (id.length() == 0){
                    et_idusuario.setError("Campo obligatorio");
                }else if(nombre.length() == 0){
                    et_nombre.setError("Campo obligatorio");
//}else if(!datoSelect.isEmpty()){
                }else if(sp_preguntaSecreta.getSelectedItemPosition() > 0){
//Acciones para guardar registro en la base de datos.
                    Toast.makeText(getContext(), "Bien...", Toast.LENGTH_SHORT).show();
                    save_server(getContext(), Integer.parseInt(id), nombre, apellidos, correo, usuario, clave, Integer.parseInt(tipoSelect),
                            Integer.parseInt(estadoSelect), String.valueOf(datoSelect), respuesta);
                }else{
                    Toast.makeText(getContext(), "Debe seleccionar un estado para la categoria", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnNew:
                new_categories();
                break;
            default:
        }
    }


    private void save_server(final Context context, final int id, final String nombre, final String apellidos, final String
            correo, final String usuario, final String clave, final int tipo, final int estado, final String pregunta,
            final String respuesta) {

        StringRequest request = new StringRequest(Request.Method.POST, Setting_VAR.URL_guardar_usuario,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        JSONObject requestJSON = null;
                        try {
                            requestJSON = new JSONObject(response.toString());
                            String estado = requestJSON.getString("estado");
                            String mensaje = requestJSON.getString("mensaje");
                            if(estado.equals("1")){
                                Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show();
//Toast.makeText(context, "Registro almacenado en MySQL.", Toast.LENGTH_SHORT).show();
                            }else if(estado.equals("2")){
                                Toast.makeText(context, ""+mensaje, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(context, "No se puedo guardar. \n" +
                        "Intentelo más tarde.", Toast.LENGTH_SHORT).show();
            }
        }) {

            protected Map<String, String> getParams() throws AuthFailureError {
//En este método se colocan o se setean los valores a recibir por el fichero *.php
                Map<String, String> map = new HashMap<>();
                map.put("Content-Type", "application/json; charset=utf-8");
                map.put("Accept", "application/json");
                map.put("id", String.valueOf(id_categoria));
                map.put("nombre", nom_categoria);
                map.put("estado", String.valueOf(estado_categoria));
                return map;
            }
        };
        MySingleton.getInstance(context).addToRequestQueue(request);
    }

    private void new_categories() {
        et_idcategoria.setText(null);
        et_namecategoria.setText(null);
        sp_estado.setSelection(0);
    }
    }
}