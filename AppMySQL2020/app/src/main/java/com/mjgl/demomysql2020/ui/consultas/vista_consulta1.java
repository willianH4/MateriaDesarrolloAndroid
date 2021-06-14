package com.mjgl.demomysql2020.ui.consultas;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.mjgl.demomysql2020.MySingleton;
import com.mjgl.demomysql2020.ProductAdapter;
import com.mjgl.demomysql2020.R;
import com.mjgl.demomysql2020.Setting_VAR;
import com.mjgl.demomysql2020.dto_catprod;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class vista_consulta1 extends Fragment {

    List<dto_catprod> productosList;
    private RecyclerView recycler;
    private ProductAdapter productAdapter;

    private ImageView iv_back;
    private TextView textViewBack;

    String id;
    String nombre;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_vista_consulta1, container, false);

        iv_back = view.findViewById(R.id.iv_back);
        textViewBack = view.findViewById(R.id.textViewBack);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back_search();
            }
        });

        textViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back_search();
            }
        });

        Bundle data = this.getArguments();
        if(data != null){
            id = data.getString("id_categoria");
            nombre = data.getString("nombre_categoria");
            //Toast.makeText(getContext(), "data: "+id+"\n"+nombre, Toast.LENGTH_SHORT).show();
        }


        recycler = view.findViewById(R.id.recycler);
        // Esta línea mejora el rendimiento, si sabemos que el contenido
        // no va a afectar al tamaño del RecyclerView
        recycler.setHasFixedSize(true);

        // Nuestro RecyclerView usará un linear layout manager
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        //productAdapter = new ProductAdapter(getContext(), obtenerProductos());
        //recycler.setAdapter(productAdapter);
        productosList = new ArrayList<>();

        loadProductos(getContext(), nombre);

        return  view;


    }


    //Este método lo he creado para probar la carga de datos en el recycler view.
    public List<dto_catprod> obtenerProductos() {
        List<dto_catprod> productos = new ArrayList<>();
        productos.add(new dto_catprod("Zapatos", "1","Negro", "Cuero", 10.9, 90.0, 1, "2020-11-02"));
        productos.add(new dto_catprod("Zapatos", "2", "Cafe", "Sintético", 11.92, 10.1,1,"2020-11-02"));
        productos.add(new dto_catprod("Zapatos", "3", "Blanco", "Cuerina", 100, 72.1,1,"2020-11-02"));
        return productos;
    }


    //Este método es para regresar al fragmento principal y asi poder especificar otra busqueda.
    public void back_search(){
        busqueda_porcatetogias vista = new busqueda_porcatetogias();
        //FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.nav_host_fragment, vista,"details");
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        //ft.addToBackStack(null);  //opcional, si quieres agregarlo a la pila
        ft.commit();
    }


    private void loadProductos(final Context context, final String nombre_categoria) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Setting_VAR.URL_consulta_catproducto,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            int totalEncontrados = array.length();
                            Toast.makeText(context, "Total: "+totalEncontrados, Toast.LENGTH_SHORT).show();
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject articulosObject = array.getJSONObject(i);

                                /*int codigo = articulosObject.getInt("codigo");
                                String descripcion = articulosObject.getString("descripcion");
                                double precio = articulosObject.getDouble("precio");
                                String img = articulosObject.getString("imagen");
                                Productos objeto = new Productos(codigo, descripcion, precio, img);
                                productosList.add(objeto);*/

                                productosList.add(new dto_catprod(
                                        articulosObject.getString("nom_categoria"),
                                        articulosObject.getString("id_producto"),
                                        articulosObject.getString("nom_producto"),
                                        articulosObject.getString("des_producto"),
                                        articulosObject.getDouble("stock"),
                                        articulosObject.getDouble("precio"),
                                        articulosObject.getInt("categoria"),
                                        articulosObject.getString("fecha_entrada")
                                ));
                            }

                            //Aqui va
                            productAdapter = new ProductAdapter(getContext(), productosList);
                            recycler.setAdapter(productAdapter);


                            productAdapter.setOnItemClickListener(getContext(), new ProductAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(int position) {

                                    vista_parcelable vista = new vista_parcelable();

                                    Bundle data = new Bundle();
                                    data.putString("name_cat", productosList.get(position).getNombre_categoria());
                                    data.putString("id_prod", productosList.get(position).getId_producto());
                                    data.putString("name_prod", productosList.get(position).getNombre_producto());
                                    data.putString("des_prod", productosList.get(position).getDes_producto());
                                    data.putString("stock", String.valueOf(productosList.get(position).getStock()));
                                    data.putString("precio", String.valueOf(productosList.get(position).getPrecio()));
                                    data.putString("id_cat", String.valueOf(productosList.get(position).getCategoria()));
                                    data.putString("fecha", productosList.get(position).getFecha());
                                    vista.setArguments(data);

                                    //FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                                    ft.replace(R.id.nav_host_fragment, vista,"details");
                                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                                    //ft.addToBackStack(null);  //opcional, si quieres agregarlo a la pila
                                    ft.commit();

                                    /*Toast toast = Toast.makeText(getContext(), ""+productosList.get(position), Toast.LENGTH_SHORT);
                                    toast.setGravity(Gravity.CENTER, 0, 0);
                                    toast.show();*/

                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error. Compruebe su acceso a Internet.", Toast.LENGTH_SHORT).show();
            }
        }){
            protected Map<String, String> getParams() throws AuthFailureError {
                //En este método se colocan o se setean los valores a recibir por el fichero *.php
                Map<String, String> map = new HashMap<>();
                map.put("Content-Type", "application/json; charset=utf-8");
                map.put("Accept", "application/json");
                map.put("nombre_categoria", nombre_categoria);
                return map;
            }
        };

        MySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }



}















//Autor: Prof. Manuel de Jesús Gámez López