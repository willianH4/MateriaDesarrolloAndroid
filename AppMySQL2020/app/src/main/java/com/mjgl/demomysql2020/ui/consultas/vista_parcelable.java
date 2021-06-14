package com.mjgl.demomysql2020.ui.consultas;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Parcelable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.mjgl.demomysql2020.MySingleton;
import com.mjgl.demomysql2020.R;
import com.mjgl.demomysql2020.Setting_VAR;
import com.mjgl.demomysql2020.dialogytoast;
import com.mjgl.demomysql2020.dto_catprod;
import com.mjgl.demomysql2020.dto_productos;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class vista_parcelable extends Fragment {

    String cod_producto, nom_producto, des_producto;
    private TextView tv_NameCategoria, tv_fecha;
    private EditText et_codigoproducto, et_nombreproducto, et_descripcion, et_stock, et_precio, et_idcategoria;
    private Button btnActualizar, btnEliminar;
    private ImageView iv_back;
    private TextView textViewBack;

    ProgressDialog progressDialog;
    AlertDialog.Builder dialogo;

    dto_productos dto = new dto_productos();

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vista_parcelable, container, false);

        tv_NameCategoria = view.findViewById(R.id.tv_NameCategoria);

        et_codigoproducto = view.findViewById(R.id.et_codigoproducto);
        tv_fecha = view.findViewById(R.id.tv_fecha);

        et_nombreproducto = view.findViewById(R.id.et_nombreproducto);
        et_descripcion = view.findViewById(R.id.et_descripcion);
        et_stock = view.findViewById(R.id.et_stock);
        et_precio = view.findViewById(R.id. et_precio);
        et_idcategoria = view.findViewById(R.id.et_idcategoria);

        btnActualizar = view.findViewById(R.id.btnActualizar);
        btnEliminar = view.findViewById(R.id.btnEliminar);

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
            //Quise implementarlo con Parcelable, pero esta algo más complicada la cosa, luego lo implementare así, es que ya me dio sueño y apenas son la una de la madrugada.
            //Tengo que aceptar que ya no soy el mismo de antes, como cuando fui alumno que llegaba a las cuatro de la madrugada...  :-(
            //dto_catprod dto = data.getParcelable("valores");

            /*A continuación recibo la data del item (cardView seleccionado) para mostrar en los input (edittext)*/
            String name_cat = data.getString("name_cat");
            tv_NameCategoria.setText(name_cat);

            String id_prod = data.getString("id_prod");
            et_codigoproducto.setText(id_prod);

            String name_prod = data.getString("name_prod");
            et_nombreproducto.setText(name_prod);

            String des_prod = data.getString("des_prod");
            et_descripcion.setText(des_prod);

            String stock = data.getString("stock");
            et_stock.setText(stock);

            String precio = data.getString("precio");
            et_precio.setText(precio);

            String id_cat = data.getString("id_cat");
            et_idcategoria.setText(id_cat);

            String fecha = data.getString("fecha");
            tv_fecha.setText(fecha);

            //Muestro parte de la info que se recibe en la pestaña Logcat
            System.out.println("Data Recibida");
            Log.i("name_cat", name_cat);
            Log.i("name_prod", name_prod);
            Log.i("Prof. Gámez", "ITCA-FEPADE");

        }

        //Estos líneas comentariadas las ignoran, fuerón pruebas que realice, al final no las ocupe.
        /*dto.setId_producto(et_codigoproducto.getText().toString());
        dto.setNom_producto(et_nombreproducto.getText().toString());
        dto.setDes_producto(et_descripcion.getText().toString());
        dto.setStock(Double.parseDouble(et_stock.getText().toString()));
        dto.setPrecio(Double.parseDouble(et_precio.getText().toString()));
        updateProductos(getContext(), dto);*/

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProductos(getContext(), et_codigoproducto.getText().toString(),
                        et_nombreproducto.getText().toString(),
                        et_descripcion.getText().toString(),
                        et_stock.getText().toString(),
                        et_precio.getText().toString());
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showToast(v, "Seguro que desea eliminar");
                //instancia.ToastPersonalizado(getContext(), inflater, v, "Colocar mensaje acá.");
                deleteProductos(getContext(), et_codigoproducto.getText().toString());
            }
        });


        return view;

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


    public void showToast(View view, String message) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout, (ViewGroup) view.findViewById(R.id.toast_root));

        TextView toastText = layout.findViewById(R.id.toast_text);
        ImageView toastImage = layout.findViewById(R.id.toast_image);

        toastText.setText(message);
        toastImage.setImageResource(R.drawable.ic_delete);

        Toast toast = new Toast(getContext());
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);

        toast.show();
    }




    //Método que permite actualizar un registro de producto en la base de datos.
    //public void updateProductos(final Context context, final dto_productos datos){
    public void updateProductos(final Context context, final String id, final String nombre, final String descripcion, final String stock, final String precio){
        /*Toast.makeText(context, "Id: " + id
                + "\nNombre Producto:" + nombre
                + "\nDescripcion Producto: " + descripcion
                + "\nStock: " + stock
                + "\nPrecio: " + precio, Toast.LENGTH_SHORT).show();*/

        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Espere por favor, Estamos trabajando en su petición en el servidor");
        progressDialog.show();

        String url = Setting_VAR.URL_actualizar_producto;

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @SuppressLint("ResourceType")
                    @Override
                    public void onResponse(String response) {
                        try {
                            //Creamos un objeto JSONObject para poder acceder a los atributos (campos) del objeto. Esperando que todo
                            JSONObject respuestaJSON = new JSONObject(response.toString());                 //Creo un JSONObject a partir del StringBuilder pasado a cadena

                            //Accedemos al vector de resultados
                            String resultJSON = respuestaJSON.getString("estado");   // estado es el nombre del campo en el JSON
                            String result_msj = respuestaJSON.getString("mensaje");   // estado es el nombre del campo en el JSON

                            if (resultJSON.equals("1")) {

                                Toast toast = Toast.makeText(context, ""+result_msj, Toast.LENGTH_LONG);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();

                            } else if (resultJSON.equals("2")) {
                                Toast toast = Toast.makeText(context, ""+result_msj, Toast.LENGTH_LONG);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();
                            }

                            progressDialog.dismiss();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(context, "Algo salio mal con la conexión al servidor. \nRevise su conexión a Internet.", Toast.LENGTH_LONG).show();
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("Content-Type", "application/json; charset=utf-8");
                map.put("Accept", "application/json");
                //Este dato lo envio para saber que registro se debe actualizar en la base de datos.
                map.put("id", id);
                //A continuación los datos que se debe actualizar en la base de datos.
                map.put("nombre_producto", nombre);
                map.put("descripcion_producto", descripcion);
                map.put("stock_producto", stock);
                map.put("precio_producto", precio);
                return map;
            }
        };

        MySingleton.getInstance(context).addToRequestQueue(stringRequest);

    }


    //Método que permite eliminar un regisgro de producto en la base de datos.
    public void deleteProductos(final Context context, final String id_producto){
        dialogo = new AlertDialog.Builder(context);
        dialogo.setIcon(R.drawable.ic_delete);
        dialogo.setTitle("¡¡¡Advertencia!!!");
        dialogo.setMessage("¿Realmente desea borrar el registro?\n" +
                "Id Producto: "+id_producto);
        dialogo.setCancelable(false);

        dialogo.setPositiveButton("Aplicar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo, int id) {
                progressDialog = new ProgressDialog(context);
                progressDialog.setCancelable(false);
                progressDialog.setMessage("Espere por favor, Estamos trabajando en el servidor");
                progressDialog.show();

                String url  = Setting_VAR.URL_eliminar_producto;

                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //Creamos un objeto JSONObject para poder acceder a los atributos (campos) del objeto.
                            JSONObject respuestaJSON = new JSONObject(response.toString());         //Creo un JSONObject a partir del StringBuilder pasado a cadena
                            String resultJSON = respuestaJSON.getString("estado");            // estado es el nombre del campo en el JSON
                            String result_msj = respuestaJSON.getString("mensaje");           // estado es el nombre del campo en el JSON
                            if (resultJSON.equals("1")) {
                                Toast toast = Toast.makeText(context, result_msj, Toast.LENGTH_LONG);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();

                                back_search();

                            } else if (resultJSON.equals("2")) {
                                Toast toast = Toast.makeText(context, "--> Nothing." +
                                        "\n" + result_msj, Toast.LENGTH_LONG);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        // Hiding the progress dialog after all task complete.
                        progressDialog.dismiss();
                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Hiding the progress dialog after all task complete.
                        progressDialog.dismiss();
                        Toast.makeText(context, "Algo salio mal. Intente mas tarde\n" +
                                "Verifique su acceso a Internet.", Toast.LENGTH_LONG).show();
                    }
                }) {
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("Content-Type", "application/json; charset=utf-8");
                        map.put("Accept", "application/json");
                        map.put("id", id_producto);
                        return map;
                    }
                };

                MySingleton.getInstance(context).addToRequestQueue(request);
            }
        });

        dialogo.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo, int id) {
                Toast toast = Toast.makeText(context, "Operación Cancelada.", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });

        //AlertDialog dialogo = builder.create();
        dialogo.show();

    }

}














//Autor: Prof. Manuel de Jesús Gámez López