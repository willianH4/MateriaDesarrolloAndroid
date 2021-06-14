package com.willianhdz.crudmysql.ui.categorias;

import android.app.ProgressDialog;
import android.location.GnssAntennaInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.willianhdz.crudmysql.Dtos.Categorias;
import com.willianhdz.crudmysql.Dtos.VolleySingleton;
import com.willianhdz.crudmysql.R;
import com.willianhdz.crudmysql.adapter.CategoriaAdapter;
import com.willianhdz.crudmysql.MySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ConsultarRecyclerviewCategorias extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

   //private OnFragmentInteractionListener mListener;

    RecyclerView recyclerCategorias;
    ArrayList<Categorias> listaCategorias;

    ProgressDialog progress;

   // RequestQueue resquest;
    JsonObjectRequest jsonObjectRequest;

    public ConsultarRecyclerviewCategorias() {
        // Required empty public constructor
    }

    public static ConsultarRecyclerviewCategorias newInstance(String param1, String param2) {
        ConsultarRecyclerviewCategorias fragment = new ConsultarRecyclerviewCategorias();
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
        View vista = inflater.inflate(R.layout.fragment_consultar_recyclerview_categorias, container, false);

        listaCategorias = new ArrayList<>();

        recyclerCategorias = (RecyclerView) vista.findViewById(R.id.idRecycler);
        recyclerCategorias.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerCategorias.setHasFixedSize(true);

       // resquest = Volley.newRequestQueue(getContext());

        cargarService();

        return vista;
    }

    private void cargarService(){

        progress = new ProgressDialog(getContext());
        progress.setMessage("Consultando...");
        progress.show();

        String url = "http://192.168.108.2/service2020/buscar_all_categoria.php";

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url, (String) null,this,this);
        //resquest.add(jsonObjectRequest);
        VolleySingleton.getIntanciaVolley(getContext()).addToRequestQueue(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(), "Error de conexion "+error.toString(), Toast.LENGTH_LONG).show();
        System.out.println();
        Log.d("Error ",error.toString());
        progress.hide();
    }

    @Override
    public void onResponse(JSONObject response) {
        Categorias categorias = null;

        JSONArray json = response.optJSONArray("categorias");
        try {

        for (int i = 0; i < json.length(); i++) {
            categorias = new Categorias();
            JSONObject jsonObject = null;
            jsonObject = json.getJSONObject(i);

            categorias.setId_categoria(jsonObject.optInt("id_categoria"));
            categorias.setNom_categoria(jsonObject.optString("nom_categoria"));
            categorias.setEstado_categoria(jsonObject.optInt("estado_categoria"));

            listaCategorias.add(categorias);
        }

        progress.hide();
            CategoriaAdapter adapter = new CategoriaAdapter(listaCategorias);
            recyclerCategorias.setAdapter(adapter);

        } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getContext(), "Error en la conexion " +
                        " "+response, Toast.LENGTH_LONG).show();
                progress.hide();
            }
        }
    }