package com.willianhdz.crudmysql.ui.productos;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.willianhdz.crudmysql.Dtos.Categorias;
import com.willianhdz.crudmysql.Dtos.Productos;
import com.willianhdz.crudmysql.MySingleton;
import com.willianhdz.crudmysql.R;
import com.willianhdz.crudmysql.Setting_VAR;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class UpdateDelete extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    //Declarando elementos de la interfaz grafica
    EditText et_id, et_nombre_prod, et_descripcion, et_stock, et_precio, et_unidadmedida;
    Spinner sp_estadoProductos, sp_fk_categoria;
    TextView tv_fechahora;
    ProgressDialog progressDialog;
    ImageButton btnConsultar;
    Button btnActualizar, btnEliminar;
    String datoSelect = "";

    JsonObjectRequest jsonObjectRequest;
    StringRequest stringRequest;

    public UpdateDelete() {
        // Required empty public constructor
    }

    public static UpdateDelete newInstance(String param1, String param2) {
        UpdateDelete fragment = new UpdateDelete();
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
    ArrayList<String> lista = null;

    ArrayList<Categorias> listaCategorias; //Va a representar la información que se va a mostrar en el combo

    String idcategoria = "";
    String nombrecategoria = "";
    int conta = 0;

    String datoStatusProduct = "";

    //Instancia DTO
    Productos dto = new Productos();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update_delete, container, false);
        et_id = view.findViewById(R.id.et_id);
        et_nombre_prod = view.findViewById(R.id.et_nombre_prod);
        et_descripcion = view.findViewById(R.id.et_descripcion);
        et_stock = view.findViewById(R.id.et_stock);
        et_precio = view.findViewById(R.id.et_precio);
        et_unidadmedida = view.findViewById(R.id.et_unidadmedida);
        sp_estadoProductos = view.findViewById(R.id.sp_estadoProductos);
        sp_fk_categoria = view.findViewById(R.id.sp_fk_categoria);
        tv_fechahora = view.findViewById(R.id.tv_fechahora);
        tv_fechahora.setText(timedate());

        btnConsultar = view.findViewById(R.id.btnConsultarProducto);
        btnActualizar = view.findViewById(R.id.btnActualizar);
        btnEliminar = view.findViewById(R.id.btnEliminar);

        sp_estadoProductos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(sp_estadoProductos.getSelectedItemPosition()>0) {
                    datoStatusProduct = sp_estadoProductos.getSelectedItem().toString();
                }else{
                    datoStatusProduct = "";
                }
//Toast.makeText(getContext(), ""+datoStatusProduct, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

//Llamo al método para que muestre los datos de la busqueda al carga la actividad.
        fk_categorias(getContext());

        //ArrayAdapter<String> adaptador = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, obtenerListaCategorias());
//ArrayAdapter<String> adaptador =new ArrayAdapter<String> (getContext(),android.R.layout.simple_spinner_item, elementos);
//sp_fk_categoria.setAdapter(adaptador);
//Evento del spinner creado para extraer la información seleccionada en cada item/opción.

        sp_fk_categoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(conta>=1 && sp_fk_categoria.getSelectedItemPosition()>0){
                    String item_spinner = sp_fk_categoria.getSelectedItem().toString();

//Hago una busqueda en la cadena seleccionada en el spinner para separar el idcategoria y el nombre de la categoria
//Esto es necesario, debido a que lo que debe enviarse a guardar a la base de datos es únicamente el idcategoria.

                    String s[] = item_spinner.split("~");
                    //Dato ID CATEGORIA
                    idcategoria = s[0].trim(); //Con trim elimino espacios al inicio y final de la cadena para enviar limpio el ID CATEGORIA.
                    //Dato NOMBRE DE LA CATEGORIA
                    nombrecategoria = s[1];

                    Toast toast = Toast.makeText(getContext(), "Id cat: " + idcategoria + "\n" + "Nombre Categoria: "+nombrecategoria, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();

                }else{
                    idcategoria = "";
                    nombrecategoria = "";
                }
                conta++;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

/*int id = Integer.parseInt(et_id.getText().toString());
String nombre = et_nombre_prod.getText().toString();
String descripcion = et_descripcion.getText().toString();
double stock =
Double.parseDouble(et_stock.getText().toString());
double precio =
Double.parseDouble(et_precio.getText().toString());
String unidad = et_unidadmedida.getText().toString();*/

                String id = et_id.getText().toString();
                String nombre = et_nombre_prod.getText().toString();
                String descripcion = et_descripcion.getText().toString();
                String stock = et_stock.getText().toString();
                String precio = et_precio.getText().toString();
                String unidad = et_unidadmedida.getText().toString();

                if(id.length() == 0){
                    et_id.setError("Campo obligatorio.");
                }else if(nombre.length() == 0){
                    et_nombre_prod.setError("Campo obligatorio.");
                }else if(descripcion.length() == 0){
                    et_descripcion.setError("Campo obligatorio.");
                }else if(stock.length() == 0){
                    et_stock.setError("Campo obligatorio.");
                }else if(precio.length() == 0){
                    et_precio.setError("Campo obligatorio.");
                }else if(unidad.length() == 0){
                    et_unidadmedida.setError("Campo obligatorio.");
                }else if(sp_estadoProductos.getSelectedItemPosition() == 0){
                    Toast.makeText(getContext(), "Debe seleccionar el estado del producto.", Toast.LENGTH_SHORT).show();
                }else if(sp_fk_categoria.getSelectedItemPosition() > 0){
//Toast.makeText(getContext(), "Good...",Toast.LENGTH_SHORT).show();
                    actualizar_productos(getContext(), id, nombre, descripcion, stock, precio, unidad, datoStatusProduct, idcategoria);
                }else{
                    Toast.makeText(getContext(), "Debe seleccionar la categoria.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarProducto();
            }
        });

        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarWebService();
            }
        });

        return view;
    }

    private String timedate(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
        String fecha = sdf.format(cal.getTime());
        return fecha;
    }

    //public ArrayList<dto_categorias> fk_categorias(final Context context){
    public void fk_categorias(final Context context){
        listaCategorias = new ArrayList<Categorias>();
        lista = new ArrayList<String>();
        lista.add("Seleccione Categoria");

        String url = Setting_VAR.URL_consultaAllCategorias;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            int totalEncontrados = array.length();
//Toast.makeText(context, "Total: "+totalEncontrados, Toast.LENGTH_SHORT).show();
                            Categorias obj_categorias = null;
//dto_categorias obj_categorias = new dto_categorias();
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject categoriasObject = array.getJSONObject(i);
                                int id_categoria = categoriasObject.getInt("id_categoria");
                                String nombre_categoria = categoriasObject.getString("nom_categoria");
                                int estado_categoria = Integer.parseInt(categoriasObject.getString("estado_categoria"));

//Encapsulo registro por registro encontrado dentro del objeto de manera temporal
                                obj_categorias = new Categorias(id_categoria, nombre_categoria, estado_categoria);

/*obj_categorias.setId_categoria(id_categoria);
obj_categorias.setNom_categoria(nombre_categoria);
obj_categorias.setEstado_categoria(estado_categoria);*/

//Agrego todos los registros en el arraylist
                                listaCategorias.add(obj_categorias);

//Saco la información del arraylist y personalizo la forma en que deseo se muestren los datos en el spinner y
//Selecciono que datos se van a mostrar del resultado.
                                lista.add(listaCategorias.get(i).getId_categoria()+" ~ "+listaCategorias.get(i).getNom_categoria());

//Creo un adaptador para cargar la lista preparada anteriormente.
//ArrayAdapter<String> adaptador = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, obtenerListaCategorias());

                                ArrayAdapter<String> adaptador =new ArrayAdapter<String> (getContext(),android.R.layout.simple_spinner_item, lista);

//Cargo los datos en el Spinner
                                sp_fk_categoria.setAdapter(adaptador);

//Muestro datos en LogCat para verificar la respuesta obtenida desde el servidor.

                                Log.i("Id Categoria",
                                        String.valueOf(obj_categorias.getId_categoria()));
                                Log.i("Nombre Categoria",
                                        obj_categorias.getNom_categoria());
                                Log.i("Estado Categoria",
                                        String.valueOf(obj_categorias.getEstado_categoria()));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error. Compruebe su acceso a Internet.", Toast.LENGTH_SHORT).show();
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }

    private void cargarWebService(){
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Cargando, espere por favor!");
        progressDialog.show();

        //Para conexion remota desde el servidor
        //String ip=getString(R.string.ip);
        //String url=ip+ Setting_VAR.URL_buscarProductosID;

        //Para pruebas en local
        //String url = "http://192.168.108.2/service2020/buscar_categoria1.php?id_categoria="+et_idcategoria.getText().toString();
        String url = Setting_VAR.URL_buscarProductosID;

        jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, (String) null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progressDialog.hide();

                Productos productos = new Productos();

                JSONArray json = response.optJSONArray("productos");
                JSONObject jsonObject = null;

                try {
                    jsonObject = json.getJSONObject(0);
                    productos.setNom_producto(jsonObject.optString("nom_producto"));
                    productos.setDes_producto(jsonObject.optString("des_producto"));
                    productos.setStock(jsonObject.optDouble("stock"));
                    productos.setPrecio(jsonObject.optDouble("precio"));
                    productos.setUnidad_medida(jsonObject.optString("unidad_medida"));
                    //Comentar si hay error
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                et_nombre_prod.setText(productos.getNom_producto());
                et_descripcion.setText(productos.getDes_producto());
                et_stock.setText(String.valueOf(productos.getStock()));
                et_precio.setText(String.valueOf(productos.getPrecio()));
                et_unidadmedida.setText(String.valueOf(productos.getUnidad_medida()));
                //sp_estado.getSelectedItemPosition(miCategoria.getEstado_categoria());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "No se puede conectar "+error.toString(), Toast.LENGTH_LONG).show();
                System.out.println();
                progressDialog.hide();
                Log.d("ERROR: ", error.toString());
            }
        });

        MySingleton.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);
        //VolleySingleton.getIntanciaVolley(getContext()).addToRequestQueue(jsonObjectRequest);
    }

    /*private void save_productos(final Context context, final int id_prod,
    final String nom_prod, final String des_prod,
    final double stock, final double precio, final
    String uni_medida, final int estado_prod,
    final int categoria){*/

    private void actualizar_productos(final Context context, final String id_prod,
                                final String nom_prod, final String des_prod,
                                final String stock, final String precio, final
                                String uni_medida, final String estado_prod,
                                final String categoria){
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Setting_VAR.URL_actualizar_producto, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                JSONObject requestJSON = null;
                try {
                    requestJSON = new JSONObject(response.toString());
                    String estado = requestJSON.getString("estado");
                    String mensaje = requestJSON.getString("mensaje");
                    if(estado.equals("1")){
                        Toast.makeText(context, mensaje,
                                Toast.LENGTH_SHORT).show();
//Toast.makeText(context, "Registro almacenado en MySQL.", Toast.LENGTH_SHORT).show();
                    }else if(estado.equals("2")){
                        Toast.makeText(context, ""+mensaje,
                                Toast.LENGTH_SHORT).show();
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
        }){

            protected Map<String, String> getParams() throws AuthFailureError
            {
//En este método se colocan o se setean los valores a recibir por el fichero *.php
                Map<String, String> map = new HashMap<>();
                map.put("Content-Type", "application/json; charset=utf-8");
                map.put("Accept", "application/json");
                map.put("id_prod", id_prod);
                map.put("nom_prod", nom_prod);
                map.put("des_prod", des_prod);
                map.put("stock", stock);
                map.put("precio", precio);
                map.put("uni_medida", uni_medida);
                map.put("estado_prod", estado_prod);
                map.put("categoria", categoria);
                return map;
            }
        };
        MySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }

    private void eliminarProducto() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Cargando...");
        progressDialog.show();

        //Conexion desde el servidor remoto
        //String ip=getString(R.string.ip);
        //String url=ip+Setting_VAR.URL_eliminar_producto;
        //String url=ip+"/webservice/eliminarCategoria2.php?id_categoria="+et_idcategoria.getText().toString();
        String url = Setting_VAR.URL_eliminar_producto;
        //Para conexion local
        //String url = "http://192.168.108.2/service2020/eliminar_categoria.php?id_categoria="+et_idcategoria.getText().toString();

        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.hide();

                if (response.trim().equalsIgnoreCase("elimina")) {
                    et_id.setText("");
                    et_nombre_prod.setText("");
                    et_descripcion.setText("");
                    et_precio.setText("");
                    et_stock.setText("");
                    et_unidadmedida.setText("");
                    //et_estado.setText("");
                    Toast.makeText(getContext(), "Se ha eliminado con exito", Toast.LENGTH_SHORT).show();
                } else {
                    if (response.trim().equalsIgnoreCase("noExiste")) {
                        Toast.makeText(getContext(), "No se encuentra la categoria ", Toast.LENGTH_SHORT).show();
                        Log.i("RESPUESTA: ", "" + response);
                    } else {
                        Toast.makeText(getContext(), "No se ha Eliminado ", Toast.LENGTH_SHORT).show();
                        Log.i("RESPUESTA: ", "" + response);
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"No se ha podido conectar",Toast.LENGTH_SHORT).show();
                progressDialog.hide();
            }
        });

        MySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);

    }

    //No utilizo este método en nada por el momento
    public ArrayList<String> obtenerListaCategorias() {
//ArrayList<String> lista = new ArrayList<String>();

        lista = new ArrayList<String>();

        lista.add("Seleccione Categoria");
        for(int i=0;i<=listaCategorias.size();i++){
            lista.add(listaCategorias.get(i).getId_categoria()+" ~ "+listaCategorias.get(i).getNom_categoria());
        }
        return lista;
    }

        //return vista;
    }
