package com.willianhdz.crudmysql.ui.categorias;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.willianhdz.crudmysql.Dtos.Categorias;
import com.willianhdz.crudmysql.MySingleton;
import com.willianhdz.crudmysql.R;
import com.willianhdz.crudmysql.Setting_VAR;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UpdateAndDelete extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //Se declaran los elementos de la interfaz grafica
    EditText et_idcategoria, et_nombre, et_estado;
    ProgressDialog pDialogog;
    Spinner sp_estado;
    ImageButton btnConsultar;
    Button btnActualizar, btnEliminar;
    String datoSelect = "";

    JsonObjectRequest jsonObjectRequest;
    StringRequest stringRequest;

    public UpdateAndDelete() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UpdateAndDelete.
     */
    // TODO: Rename and change types and number of parameters
    public static UpdateAndDelete newInstance(String param1, String param2) {
        UpdateAndDelete fragment = new UpdateAndDelete();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_update_and_delete, container, false);

        et_idcategoria = (EditText) vista.findViewById(R.id.et_idcategoria);
        et_nombre = (EditText) vista.findViewById(R.id.et_nombre);
        et_estado = (EditText) vista.findViewById(R.id.et_estado);
        btnConsultar= (ImageButton) vista.findViewById(R.id.btnConsultarCategoria);
        btnActualizar=(Button) vista.findViewById(R.id.btnActualizar);
        btnEliminar=(Button) vista.findViewById(R.id.btnEliminar);
        sp_estado = (Spinner) vista.findViewById(R.id.sp_estado);

        //Recorriendo el spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.estadoCategorias, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_estado.setAdapter(adapter);
        sp_estado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(sp_estado.getSelectedItemPosition()>0) {
                    datoSelect = sp_estado.getSelectedItem().toString();
                }else{
                    datoSelect = "";
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

        });

        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarWebService();
            }
        });

        //Eventos de botones Update y Delete
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webServiceActualizar();
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            String codigo_de = et_idcategoria.getText().toString();
            @Override
            public void onClick(View view) {
                delete_server(getContext(), Integer.parseInt(codigo_de));
            }
        });

        return vista;
    }

    private void cargarWebService(){
        pDialogog = new ProgressDialog(getContext());
        pDialogog.setMessage("Cargando, espere por favor!");
        pDialogog.show();

        //Para conexion remota desde el servidor
        //String ip=getString(R.string.ip);
        //String url=ip+ Setting_VAR.URL_buscar_categoria1+et_idcategoria.getText().toString();

        //Para pruebas en local
        String url = "http://192.168.108.2/service2020/buscar_categoria1.php?id_categoria="+et_idcategoria.getText().toString();

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, (String) null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                pDialogog.hide();

                Categorias miCategoria = new Categorias();

                JSONArray json = response.optJSONArray("categorias");
                JSONObject jsonObject = null;

                try {
                    jsonObject = json.getJSONObject(0);
                    miCategoria.setNom_categoria(jsonObject.optString("nom_categoria"));
                    miCategoria.setEstado_categoria(jsonObject.optInt("estado_categoria")); //Comentar si hay error
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                et_nombre.setText(miCategoria.getNom_categoria());
                //sp_estado.getSelectedItemPosition(miCategoria.getEstado_categoria());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "No se puede conectar "+error.toString(), Toast.LENGTH_LONG).show();
                System.out.println();
                pDialogog.hide();
                Log.d("ERROR: ", error.toString());
            }
    });

        MySingleton.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);
        //VolleySingleton.getIntanciaVolley(getContext()).addToRequestQueue(jsonObjectRequest);
    }

    private void webServiceActualizar(){
        pDialogog = new ProgressDialog(getContext());
        pDialogog.setMessage("Cargando, espere por favor! ");
        pDialogog.show();

        //Para acceso a base de datos en la nube
       // String ip=getString(R.string.ip);
        //String url=ip+Setting_VAR.URL_actualizar_categoria;

        //Para conexion local
        String url = "http://192.168.108.2/service2020/actualizar_categoria.php";

        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pDialogog.hide();

                if (response.trim().equalsIgnoreCase("actualiza")) {
                    Toast.makeText(getContext(), "Se ha actualizado con exito ", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "No se ha actualizado ", Toast.LENGTH_SHORT).show();
                    Log.i("RESPUESTA: ", "" + response);
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"No se ha podido conectar",Toast.LENGTH_SHORT).show();
                pDialogog.hide();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
               String id_categoria = et_idcategoria.getText().toString();
               String nom_categoria = et_nombre.getText().toString();
               String estado_categoria = datoSelect;

               Map<String,String> parametros = new HashMap<>();
               parametros.put("id_categoria",id_categoria);
               parametros.put("nom_categoria",nom_categoria);
               parametros.put("estado_categoria",estado_categoria);

               return parametros;
            }
        };

        MySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
        //VolleySingleton.getIntanciaVolley(getContext()).addToRequestQueue(stringRequest);
    }

    private void delete_server(final Context context, final int id_cat_de) {
        StringRequest request = new StringRequest(Request.Method.POST, Setting_VAR.URL_eliminar_categoria,
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
                Toast.makeText(context, "Error Delete Categoria. \n" +
                        "Intentelo más tarde.", Toast.LENGTH_SHORT).show();
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                //En este método se colocan o se setean los valores a recibir por el fichero *.php
                Map<String, String> map = new HashMap<>();
                map.put("id_categoria", String.valueOf(id_cat_de));
                return map;
            }
        };
        MySingleton.getInstance(context).addToRequestQueue(request);
    }
}