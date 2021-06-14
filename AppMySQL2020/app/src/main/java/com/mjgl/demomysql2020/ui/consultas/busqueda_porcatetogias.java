package com.mjgl.demomysql2020.ui.consultas;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.mjgl.demomysql2020.MySingleton;
import com.mjgl.demomysql2020.R;
import com.mjgl.demomysql2020.Setting_VAR;
import com.mjgl.demomysql2020.dto_categorias;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class busqueda_porcatetogias extends Fragment {

    private Spinner sp_fk_categoria;
    private Button btnBuscar;

    String idcategoria = "";
    String nombrecategoria = "";
    int conta = 0;

    ArrayList<String> lista = null;
    ArrayList<dto_categorias> listaCategorias;               //Va a representar la información que se va a mostrar en el combo

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_busqueda_porcatetogias, container, false);

        btnBuscar = view.findViewById(R.id.btnBuscar);
        sp_fk_categoria = view.findViewById(R.id.sp_fk_categoria);

        sp_fk_categoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(conta>=1 && sp_fk_categoria.getSelectedItemPosition()>0){
                    String item_spinner= sp_fk_categoria.getSelectedItem().toString();
                    //Hago una busqueda en la cadena seleccionada en el spinner para separar el idcategoria y el nombre de la categoria
                    //Esto es necesario, debido a que lo que debe enviarse a guardar a la base de datos es únicamente el idcategoria.
                    String s[] = item_spinner.split("~");
                    //Dato ID CATEGORIA
                    idcategoria = s[0].trim();        //Con trim elimino espacios al inicio y final de la cadena para enviar limpio el ID CATEGORIA.
                    //Dato NOMBRE DE LA CATEGORIA
                    nombrecategoria = s[1].trim();

                    /*Toast toast = Toast.makeText(getContext(), "Id cat: " + idcategoria + "\n" + "Nombre Categoria: "+nombrecategoria, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();*/

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

        //Llamo al método para que muestre los datos de la busqueda al carga la actividad.
        fk_categorias(getContext());


        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (sp_fk_categoria.getSelectedItemPosition()>0){
                    // Execute a transaction, replacing any existing fragment
                    // with this one inside the frame.
                    vista_consulta1 vista = new vista_consulta1();
                    Bundle data = new Bundle();
                    data.putString("id_categoria", idcategoria);
                    data.putString("nombre_categoria", nombrecategoria);
                    vista.setArguments(data);

                    //FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.nav_host_fragment, vista,"details");
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    //ft.addToBackStack(null);  //opcional, si quieres agregarlo a la pila
                    ft.commit();

                }else{

                    Toast toast = Toast.makeText(getContext(), "Debe seleccionar una categoria.", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();

                }



            }
        });

        return view;


    }



    //public ArrayList<dto_categorias> fk_categorias(final Context context){
    public void fk_categorias(final Context context){
        listaCategorias = new ArrayList<dto_categorias>();
        lista = new ArrayList<String>();
        lista.add("Seleccione Categoria");

        String url  = Setting_VAR.URL_consultaAllCategorias;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            int totalEncontrados = array.length();
                            //Toast.makeText(context, "Total: "+totalEncontrados, Toast.LENGTH_SHORT).show();

                            dto_categorias obj_categorias = null;
                            //dto_categorias obj_categorias = new dto_categorias();

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject categoriasObject = array.getJSONObject(i);
                                int id_categoria = categoriasObject.getInt("id_categoria");
                                String nombre_categoria = categoriasObject.getString("nom_categoria");
                                int estado_categoria = Integer.parseInt(categoriasObject.getString("estado_categoria"));
                                //Encapsulo registro por registro encontrado dentro del objeto de manera temporal
                                obj_categorias = new dto_categorias(id_categoria, nombre_categoria, estado_categoria);
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
                                Log.i("Id Categoria", String.valueOf(obj_categorias.getId_categoria()));
                                Log.i("Nombre Categoria", obj_categorias.getNom_categoria());
                                Log.i("Estado Categoria", String.valueOf(obj_categorias.getEstado_categoria()));
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


}























//Autor: Prof. Manuel de Jesús Gámez López