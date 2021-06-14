package com.willianhdz.appinvertaryindividual;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;

public class Productos extends AppCompatActivity {

    private Spinner sp_categoria;
    private EditText et_id, et_nombre, et_descripicion, et_stock, et_precio, et_unidad, et_estado, et_fecha;
    private Button btn_guardar, btn_consultar1, btn_consultar2, btn_eliminar, btn_actualizar;

    //ArrayList<String>categoriasList;
    //ArrayList<Categoria>categoiasList;

    ConexionSQLite conexion = new ConexionSQLite(this);
    Producto datos = new Producto();

    //Para menu/////////////////////
    private FloatingActionMenu menu;
    private FloatingActionButton item1, item2, item3, item4, item5;

    //instancia de la clase modal
    modal_Toast_Custom modal = new modal_Toast_Custom();
    //Hasata aca ////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        et_id = (EditText) findViewById(R.id.et_id);
        et_nombre = (EditText) findViewById(R.id.et_nombre);
        et_descripicion = (EditText) findViewById(R.id.et_descripcion);
        et_stock = (EditText) findViewById(R.id.et_stock);
        et_precio = (EditText) findViewById(R.id.et_precio);
        et_unidad = (EditText) findViewById(R.id.et_unidad);
        et_estado = (EditText) findViewById(R.id.et_estado);
        et_fecha = (EditText) findViewById(R.id.et_fecha);
        sp_categoria = (Spinner) findViewById(R.id.sp_categoria);

        btn_guardar = (Button) findViewById(R.id.btn_guardar);
        btn_consultar1 = (Button) findViewById(R.id.btn_consultar1);
        btn_consultar2 = (Button) findViewById(R.id.btn_consultar2);
        btn_eliminar = (Button) findViewById(R.id.btn_eliminar);
        btn_actualizar = (Button) findViewById(R.id.btn_actualizar);
        /*
        tv_cod = (TextView)findViewById(R.id.tv_cod);
        tv_descripcion = (TextView)findViewById(R.id.tv_descripcion);
        tv_precio = (TextView)findViewById(R.id.tv_precio);
*/

        //Inicia bloque para pruebas del menu/
        menu = findViewById(R.id.menu_fab);
        item1 = findViewById(R.id.item1);
        item2 = findViewById(R.id.item2);
        item3 = findViewById(R.id.item3);
        item4 = findViewById(R.id.item4);
        item5 = findViewById(R.id.item5);

        menu.setOnMenuToggleListener(new FloatingActionMenu.OnMenuToggleListener() {
            @Override
            public void onMenuToggle(boolean opened) {
                if (opened) {
                    Toast.makeText(Productos.this, "Menu abierto", Toast.LENGTH_SHORT);
                } else {
                    Toast.makeText(Productos.this, "Menu cerrado", Toast.LENGTH_SHORT);
                }
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (menu.isOpened()) {
                    menu.close(true);
                }
            }
        });

        item1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View view) {
                finishAffinity();
            }
        });

        item2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modal.dialogAbout(Productos.this);
            }
        });

        item3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Productos.this, MainActivity.class);
                startActivity(intent);

                modal.dialogConfirmCustom2(Productos.this);
            }
        });

        item4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Productos.this, MainActivity.class);
                startActivity(intent);

                modal.dialogConfirmCustom2(Productos.this);
            }
        });

        item5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Productos.this, MainActivity.class);
                startActivity(intent);

                modal.dialogConfirmCustom2(Productos.this);
            }
        });
        //Finaliza bloque para preuba de l menu


        //conexion. obtenerListaArticulos(),
        conexion.consultaListaCategorias();

        //ArrayAdapter<CharSequence> adaptador —— new ArrayAdapter(this, android. R.layout. simple spinner item, IistaArticulos);
        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, conexion.obtenerListaCategorias());
        sp_categoria.setAdapter(adaptador);


        sp_categoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            /*
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position,
                                       long I) {

                if (position != 0) {
                    tv_cod.setText("id_categoria: " + conexion.consultaListaCategorias().get(position -
                            1).getId_categoria());
                    tv_descripcion.setText("nombre: " + conexion.consultaListaCategorias().get(position - 1).getNombre());
                    tv_precio.setText("Estado: " + String.valueOf(conexion.consultaListaCategorias().get(position - 1).getEstado()));

                } else {
                    tv_cod.setText("Codigo: ");
                    tv_descripcion.setText("Descripcion: ");
                    tv_precio.setText("Precio: ");

                }
            }
*/

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });

    }

    boolean inputEt = false;
    boolean inputEd = false;
    boolean input1 = false;
    boolean input2 = false;
    boolean input3 = false;
    boolean input4 = false;
    boolean input5 = false;
    boolean input6 = false;
    int resultadoInsert = 0;

    Modal ventanas = new Modal();
    ConexionSQLite cone = new ConexionSQLite(this);
    Producto pro = new Producto();
    AlertDialog.Builder dialogo;


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            new android.app.AlertDialog.Builder(this)
                    .setIcon(R.drawable.ic_close)
                    .setTitle("Warning")
                    .setMessage("¿Realmente desea salir?")
                    .setNegativeButton(android.R.string.cancel, null)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {//un listener que at pulsar, cierre la aplicacion

                        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            /*lntent intent —— new Intent(DashboardLuces.this, luces control sms.class), startActivity(intent),’”/
        //MainActivity.this.finishAffinity(),
        //finish()*/

                            finishAffinity();
                        }
                    })
                    .show();

            // Si el listener devuelve true, significa que el evento esta procesado, y nadie debe hacer nada mas
            return true;
        }
/*
        //para las demas cosas, se reenvia el evento at listener habitual
        return super.onKeyDown(keyCode, event);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.hola));
        toolbar.setTitleTextColor(getResources().getColor(R.color.mycolor1));
        toolbar.setTitleMargin(0, 0, 0, 0);
        toolbar.setSubtitle("Examen practico");
        toolbar.setSubtitleTextColor(getResources().getColor(R.color.mycolor));
        toolbar.setTitle("By Group");
        setSupportActionBar(toolbar);

        //y esto para pantalla completa (oculta incluso la barra de estado)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                confirmacion();
            }
        });

*/

        String senal = "";
        String id = "";
        String nombre = "";
        String descripcion = "";
        String stock = "";
        String precio = "";
        String unidad_medida = "";
        String estado = "";
        String cat = "";
        String fecha_entrada = "";

        try {
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                id = bundle.getString("id_producto");
                senal = bundle.getString("senal");
                nombre = bundle.getString("nombre");
                descripcion = bundle.getString("descripcion");
                stock = bundle.getString("stock");
                precio = bundle.getString("precio");
                unidad_medida = bundle.getString("unidad_de_medida");
                estado = bundle.getString("estado");
                cat = bundle.getString("categoria");
                fecha_entrada = bundle.getString("fecha_entrada");
                if (senal.equals("1")) {
                    et_id.setText(id);
                    et_nombre.setText(nombre);
                    et_descripicion.setText(descripcion);
                    et_stock.setText(stock);
                    et_precio.setText(precio);
                    et_unidad.setText(unidad_medida);
                    et_estado.setText(estado);
                    sp_categoria.setId(Integer.parseInt(cat));
                    et_fecha.setText(fecha_entrada);
                    //finish(),
                }
            }
        } catch (Exception e) {

        }
        return false;
    }

    private void confirmacion() {
        String mensaje = "¿Realmente desea salir?";
        dialogo = new AlertDialog.Builder(Productos.this);
        dialogo.setIcon(R.drawable.ic_close);
        dialogo.setTitle("Warning");
        dialogo.setMessage(mensaje);
        dialogo.setCancelable(false);
        dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo, int id) {
        /*lntent intent —— new Intent(DashboardLuces.this, luces control sms. class), startActivity(intent),”/
        //DashboardLuces.this. finishAffinity(), */
                Productos.this.finish();
            }
        });
        dialogo.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo, int id) {
                //Toast. makeText(getApplicationContext(), "Operacion Cancelada.", Toast. LENGTH LONG). show(),’
            }
        });
        dialogo.show();
    }


    public boolean alta(View v) {

        int idCombo = (int) sp_categoria.getSelectedItemId();
        boolean estado = true;
        int resultado;
        ContentValues registro = new ContentValues();

        if (et_id.getText().toString().length() == 0) {
            et_id.setError("Campo obligatorio");
            inputEt = false;
        } else {
            inputEt = true;
        }
        if (et_nombre.getText().toString().length() == 0) {
            et_nombre.setError("Campo obligatorio");
            inputEd = false;
        } else {
            inputEd = true;
        }
        if (et_descripicion.getText().toString().length() == 0) {
            et_descripicion.setError("Campo obligatorio");
            input1 = false;
        } else {
            input1 = true;
        }
        if (et_stock.getText().toString().length() == 0) {
            et_stock.setError("Campo obligatorio");
            input2 = false;
        } else {
            input2 = true;
        }
        if (et_precio.getText().toString().length() == 0) {
            et_precio.setError("Campo obligatorio");
            input3 = false;
        } else {
            input3 = true;
        }
        if (et_unidad.getText().toString().length() == 0) {
            et_unidad.setError("Campo obligatorio");
            input4 = false;
        } else {
            input4 = true;
        }
        if (et_estado.getText().toString().length() == 0) {
            et_estado.setError("Campo obligatorio");
            input5 = false;
        } else {
            input5 = true;
        }
        if (et_fecha.getText().toString().length() == 0) {
            et_fecha.setError("Campo obligatorio");
            input6 = false;
        } else {
            input6 = true;
        }
        if (inputEt && inputEd && input1 && input2 && input3 && input4 && input5 && input6 && idCombo != 0) {
          /*  try {
                datos.setId_producto(Integer.parseInt(et_id.getText().toString()));
                datos.setNombre(et_nombre.getText().toString());
                datos.setDescripcion(et_descripicion.getText().toString());
                datos.setStock(Double.parseDouble(et_stock.getText().toString()));
                datos.setPrecio(Double.parseDouble(et_precio.getText().toString()));
                datos.setUnidad_de_medida(et_unidad.getText().toString());
                datos.setEstado(Integer.parseInt(et_estado.getText().toString()));
                datos.setFecha_entrada(et_fecha.getText().toString());
                if (idCombo != 0){
                    datos.setCategoria(Integer.parseInt(sp_categoria.toString()));
                }
                //if(conexion.insertardatos(datos))(
                //if(conexion.InsertRegister(datos)){

                if(conexion.InserProductos(datos)){

                    Toast.makeText(this, "Registro agregado satisfactoriamente!",
                            Toast.LENGTH_SHORT).show();
                    limpiarDatos();
                }else {
                    Toast.makeText(getApplicationContext(), "Error. Ya existe un registro\n" +
                            " id producto: " + et_id.getText().toString(), LENGTH_LONG).show();
                    limpiarDatos();
                }

            }catch (Exception e) {
                Toast.makeText(this, "ERROR. Ya existe.", Toast.LENGTH_SHORT).show();
            }
        }

           */

            try {
                Log.i("TAMAÑO", conexion.categoriasList.size() + "");
                Log.i("id combo", idCombo + "");
                Log.i("id combo - 1", (idCombo - 1) + "");//se resta 1 ya que se quiere obtener la posicion de la lista, no del combo
                int idCategoria = conexion.categoriasList.get(idCombo - 1).getId_categoria();
                Log.i("id CATEGORIA", idCategoria + "");
                //registro.put(”codigo",datos.getCodigo());
                registro.put("id_producto", pro.getId_producto());
                registro.put("nombre", pro.getNombre());
                registro.put("descripcion", pro.getDescripcion());
                registro.put("stock", pro.getStock());
                registro.put("precio", pro.getPrecio());
                registro.put("unidad_de_medida", pro.getUnidad_de_medida());
                registro.put("estado", pro.getEstado());
                registro.put("fecha_entrada", pro.getFecha_entrada());
                registro.put("categoria", idCategoria);

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return estado;
    }



    public void mensaje (String mensaje) {
        Toast.makeText(this, "" + mensaje, Toast.LENGTH_SHORT).show();
    }

    public void limpiarDatos() {
        et_id.setText(null);
        et_nombre.setText(null);
        et_descripicion.setText(null);
        et_precio.setText(null);
        et_stock.setText(null);
        et_estado.setText(null);
        et_unidad.setText(null);
        et_fecha.setText(null);
        sp_categoria.setAdapter(null);
    }

    public void consultaporcodigo(View v) {
        if(et_id.getText().toString().length()==0){
            et_id.setError("Campo obligatorio");
            inputEt = false;
        }else {
            inputEt = true;
        }
        if(inputEt){
            String id = et_id.getText().toString();
            datos.setId_producto(Integer.parseInt(id));
            //if(conexion.consultaCodigo(datos)){
            if(conexion.consultaProducto(datos)){
                et_nombre.setText(datos.getNombre());
                et_descripicion.setText(""+datos.getDescripcion());
                et_stock.setText(""+datos.getStock());
                et_precio.setText(""+datos.getPrecio());
                et_unidad.setText(""+datos.getUnidad_de_medida());
                et_estado.setText(""+datos.getEstado());
                et_fecha.setText(""+datos.getFecha_entrada());
                //Toast. makeText(this, "Se encontro uno", Toast. LENGTH SHORT). show(),
            }else {
                Toast.makeText(this, "No existe un articulo con dicho id",
                        Toast.LENGTH_SHORT).show();
                limpiarDatos();
            }
        }else {
            Toast.makeText(this, "Ingrese el ID del producto a buscar.",
                    Toast.LENGTH_SHORT).show();
        }}

    public void consultapordescripcion(View v){
        if(et_nombre.getText().toString().length()==0){
            et_nombre.setError("Campo obligatorio");
            inputEd = false;
        }else {
            inputEd = true;
        }if(inputEd){
            String nombre = et_nombre.getText().toString();
            datos.setNombre(nombre);
            if(conexion.consultarNombrePro(datos)){
                et_id.setText(""+datos.getId_producto());
                et_nombre.setText(datos.getNombre());
                et_descripicion.setText(""+datos.getDescripcion());
                et_stock.setText(""+datos.getStock());
                et_precio.setText(""+datos.getPrecio());
                et_unidad.setText(""+datos.getUnidad_de_medida());
                et_estado.setText(""+datos.getEstado());
                et_fecha.setText(""+datos.getFecha_entrada());
                //Toast. makeText(this, "Se encontro uno", Toast. LENGTH SHORT). show(),
            }else {
                Toast.makeText(this, "No existe un articulo con dicho nombre",
                        Toast.LENGTH_SHORT).show();
                limpiarDatos();
            }

        }else {
            Toast.makeText(this, "Ingrese nombre del producto a buscar.",
                    Toast.LENGTH_SHORT).show();
        }
    }


    public void bajaporcodigo(View v) {
        if(et_id.getText().toString().length()==0){
            et_id.setError("campo obligatorio");
            inputEt = false;
        }else {
            inputEt = true;
        }
        if(inputEt){
            String cod = et_id.getText().toString();
            datos.setId_producto(Integer.parseInt(cod));
            if(conexion.bajaIdProducto(Productos.this,datos)){
                //Toast. makeText(this, "Registro eliminado satisfactoriamente.", Toast. LENG TH SHORT). show(),
                limpiarDatos();
            }else {
                Toast.makeText(this, "No existe un articulo con dicho ID.",
                        Toast.LENGTH_SHORT).show();
                limpiarDatos();
            }
        }
    }


    public void modificacion(View v) {
        if (et_id.getText().toString().length() == 0) {
            et_id.setError("campo obligatorio");
            inputEt = false;
        } else {
            inputEt = true;

        }
        if (inputEt) {

            String cod = et_id.getText().toString();
            String nombre = et_nombre.getText().toString();
            String descripcion = et_descripicion.getText().toString();
            double stock = Integer.parseInt(et_stock.getText().toString());
            double precio = Double.parseDouble(et_precio.getText().toString());
            String unidad = et_unidad.getText().toString();
            int estado = Integer.parseInt(et_estado.getText().toString());
            int categoria = Integer.parseInt(sp_categoria.toString());
            String fecha = et_fecha.getText().toString();

            datos.setId_producto(Integer.parseInt(cod));
            datos.setNombre(nombre);
            datos.setDescripcion(descripcion);
            datos.setStock(Double.parseDouble(String.valueOf(stock)));
            datos.setPrecio(Double.parseDouble(String.valueOf(precio)));
            datos.setUnidad_de_medida(unidad);
            datos.setEstado(Integer.parseInt(String.valueOf(estado)));
            datos.setCategoria(Integer.parseInt(String.valueOf(categoria)));
            datos.setFecha_entrada(fecha);
            datos.setCategoria(categoria);

            if (conexion.modificarProducto(datos)) {
                Toast.makeText(this, "Registro Modificado Correctamente.",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "No se han encontrado resultados para la busqueda especificada.",
                        Toast.LENGTH_SHORT).show();

            }
        }
    }
}
