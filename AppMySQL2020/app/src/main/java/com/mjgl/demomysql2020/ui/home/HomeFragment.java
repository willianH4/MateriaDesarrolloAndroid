package com.mjgl.demomysql2020.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.mjgl.demomysql2020.MainActivity;
import com.mjgl.demomysql2020.MySingleton;
import com.mjgl.demomysql2020.R;
import com.mjgl.demomysql2020.Setting_VAR;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private TextView textView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        //final TextView textView = root.findViewById(R.id.text_home);
        textView = root.findViewById(R.id.text_home);
        final Button btn1 = root.findViewById(R.id.btn1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //pruebaVolley();

                //peticionGson();

                recibirJson();
            }
        });

        /*homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        return root;
    }


    //METODO DE PRUEBA.
    private void pruebaVolley() {
        String url = "http://httpbin.org/html";

        // Request a string response
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Result handling
                        System.out.println(response.substring(0, 100));
                        textView.setText(response.substring(0, 100));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Error handling
                System.out.println("Something went wrong!");
                Toast.makeText(getContext(), "Sin conexión a Internet", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });

        // Add the request to the queue
        //Volley.newRequestQueue(this).add(stringRequest);
        MySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
        //MySingleton.getInstance(this).getRequestQueue().add(stringRequest);




        /*
         // Get a RequestQueue
            RequestQueue queue = MySingleton.getInstance(this.getApplicationContext()).
                getRequestQueue();

            // ...

            // Add a request (in this example, called stringRequest) to your RequestQueue.
            MySingleton.getInstance(this).addToRequestQueue(stringRequest);
         */
    }


    private void peticionGson() {
        //String url = "http://192.168.57.1/service2020/json1.php";
        String url1 = Setting_VAR.URL_PRUEBA;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url1, (String) null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        textView.setText("Response: " + response.toString());
                        Toast.makeText(getContext(), "" + response.toString(), Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });
        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);
    }


    private void recibirJson() {
        StringRequest request = new StringRequest(Request.Method.GET, Setting_VAR.URL_PRUEBA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    //Creamos un objeto JSONObject para poder acceder a los atributos (campos) del objeto.
                    JSONObject respuestaJSON = new JSONObject(response.toString());   //Creo un JSONObject a partir del StringBuilder pasado a cadena
                    //Accedemos al vector de resultados
                    String var1 = respuestaJSON.getString("id");       // estado es el nombre del campo en el JSON
                    String var2 = respuestaJSON.getString("nombre");   // estado es el nombre del campo en el JSON
                    String var3 = respuestaJSON.getString("apellidos");
                    textView.setText("Response: " + response.toString());
                    //Toast.makeText(getContext(), ""+response.toString(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(getContext(), "Id: " + var1 + "\nNombre: " + var2 + "\nApellidos: "+var3, Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                }
            }
        );
        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(getContext()).addToRequestQueue(request);
    }

    private void base(){

        StringRequest r1 = new StringRequest(Request.Method.POST, Setting_VAR.URL_PRUEBA, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });

    }


    //Estructura base
    private void baseRequest(){

        StringRequest requesst = new StringRequest(Request.Method.GET, Setting_VAR.URL_PRUEBA, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("Content-Type", "application/json; charset=utf-8");
                map.put("Accept", "application/json");
                map.put("id", "1");
                return map;
            }
        };

        MySingleton.getInstance(getContext()).addToRequestQueue(requesst);
    }


}















//Autor: Prof. Manuel de Jesús Gámez López