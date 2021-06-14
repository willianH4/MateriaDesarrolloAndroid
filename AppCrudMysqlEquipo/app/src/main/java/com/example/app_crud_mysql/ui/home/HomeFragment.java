package com.example.app_crud_mysql.ui.home;

import android.os.Bundle;
import android.provider.Settings;
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
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.app_crud_mysql.MySingleton;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.example.app_crud_mysql.R;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private TextView textView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        textView = root.findViewById(R.id.text_home);
       // final Button btn1 = root.findViewById(R.id.btn1);

     /*   btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            peticionGson();
            }
        });

/*
 homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
*/

        return root;
    }

    private void pruebaVolly(){
       final String url = "http://httpbin.org/html";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                    System.out.println(response.substring(0, 16));
                    textView.setText(response.substring(0, 16));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                System.out.println("something went wrong!");
                Toast.makeText(getContext(),"Sin conexion a inter", Toast.LENGTH_SHORT).show();
                volleyError.printStackTrace();
            }

        });
        MySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
    }

    private void peticionGson(){
       final  String url = "http://192.168.1.20/service2020/json1.php";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, (String) null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                textView.setText("Response: " + response.toString());
                Toast.makeText(getContext(), ""+response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
    });
        MySingleton.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);
    }

    private void recibirJson(){
        final String url = "http://192.168.1.20/service2020/json1.php";
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
            try {
                //creamos un objeto json pára acceder a los atributos (campos) del objeto.
                JSONObject respuestaJSON = new JSONObject(response.toString());
                //Accedemos al vector de resultado
                String var1 = respuestaJSON.getString("id");  //Estado del id del campo json
                String var2 = respuestaJSON.getString("nombre");
                textView.setText("Response " + response.toString());
                Toast.makeText(getContext(), "Id: " +var1 + "\n Nombre: "+var2, Toast.LENGTH_SHORT).show();
            }catch (JSONException e){
                e.printStackTrace();
            }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
    });
        MySingleton.getInstance(getContext()).addToRequestQueue(request);
    }

    private void baseRequest(){
        final String url = "http://127.0.0.1/service2020/json1.php";
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }

    }) {
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> map = new HashMap<>();
                map.put("Content-Type", "application/json; charset=utf-8");
                map.put("Accept", "application/json");
                map.put("id", "1");
                return map;
            }
        };
    }

}