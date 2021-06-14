package com.willianhdz.appinvertaryindividual;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class ConexionSQLite extends SQLiteOpenHelper {

    boolean estadoDelete = true;

    ArrayList<String> listaCategorias; //Va a representar la informacion que se va a mostrar en el combo
    ArrayList<Categoria> categoriasList;    //Entidad que representa a los datos de la tabla, en esta caso la tabla Categorias

    ArrayList<String> listaProductos; //Va a representar la informacion que se va a mostrar en el combo
    ArrayList<Producto> productosList;    //Entidad que representa a los datos de la tabla, en esta caso la tabla productos


    public ConexionSQLite (Context context) {
        //nombre de la base de datos
        super(context, "db_inventario.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //CREACION DE LA TABLA tb_categoria
        db.execSQL("create table tb_categoria(id_categoria integer primary key autoincrement ," +
                " nom_categoria varchar(50) not null, estado_categoria int(1) not null)");

        //CREACION DE LA PRIMERA TABLA tb_producto
        db.execSQL("create table tb_producto(id_producto integer primary key autoincrement ," +
                " nombre varchar(50) not null, descripcion varchar(90) not null, stock real not null , precio real not null, " +
                " unidad_de_medida varchar(20) not null, estado int(1) not null,fecha_entrada datetime not null, " +
                " categoria int(5) not null, " +
                "FOREIGN KEY (categoria) REFERENCES tb_categoria(id_categoria))");

        //CREACION DE LA TABLA tb_usuario
        db.execSQL("create table tb_usuario(id_usuario interger not null primary key unique, nombre varchar(60) not null," +
                " apellido varchar(30) not null, correo varchar(45) not null, usuario varchar(30) not null," +
                " clave varchar(150) not null, tipo int(1) not null, estado int(1) not null, pregunta varchar(60) not null," +
                " respuesta varchar(35), fecha_de_registro datetime not null )");

        //SE CREO UN  REGISTRO DE USUARIO
        //PARA ASI PUEDA INICIAR SESION SIN REGISTRARSE ES EL USUARIO MASTER
        db.execSQL("insert into tb_usuario values('1', 'Irvin','Cortez','irvincotez67@gmail.com','Administrador'," +
                " '123Irvin67','1', '1', '¿Nombre de tu primer mascota?', 'Kanojo', datetime('now', 'localtime'))");

        //Insertanto un registro
        db.execSQL("insert into tb_categoria values(1, 'Bebidas', 2)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists tb_producto");
        db.execSQL("drop table if exists tb_categoria");
        db.execSQL("drop table if exists tb_usuario");
        onCreate(db);
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String fecha1 = sdf.format(cal.getTime());


    public SQLiteDatabase bd() {
        SQLiteDatabase bd = this.getWritableDatabase();
        return bd;
    }

    public boolean InserCategorias(Categoria datos) {
        boolean estado = true;
        int resultado;
        //SQLiteDatabase bd —— this.getWritableDatabase(),
        try {
            int id_categoria = datos.getId_categoria();
            String nom_categoria = datos.getNombre();
            int estadoCategoria = datos.getEstado();

            //Cursor fila —— this.getWritableDatabase().rawQuery(”select codigo from adiculos where codigo——”’+codigo+ ’, null),
            Cursor fila = bd().rawQuery("select id_categoria from tb_categoria where id_categoria='" + id_categoria + "'", null);
            if (fila.moveToFirst() == true) {
                estado = false;
            } else {
                //estado —— (boolean)this.getWritableDatabase().insed(”datos”,”nombre, correo, telefono”, registro),
                //resultado —— (int) this.getWritableDatabase().insert(”usuarios”, "nombres, apellidos, usuario, clave,pregunta,respuesta", registro),
                String SQL = "INSERT INTO tb_categoria \n" + "(id_categoria,nom_categoria,estado_categoria)\n" + "VALUES \n" +
                        "('" + id_categoria + "', '" + nom_categoria + "', '" + estadoCategoria + "');";
                bd().execSQL(SQL);
                bd().close();

        /*
        this.getWritableDatabase().execSQL(SQL):
        this.getWritabIeDatabase(). close();

        */

                estado = true;
            }

        } catch (Exception e) {
            estado = false;
            Log.e("error.", e.toString());
        }
        return estado;
    }



    public boolean insertardatosCategorias(Categoria datos) {
        boolean estado = true;
        int resultado;
        ContentValues registro = new ContentValues();
        try {
            //registro.put(”codigo",datos.getCodigo());
            registro.put("id_categoria", datos.getId_categoria());
            registro.put("nom_categoria", datos.getNombre());
            registro.put("estado_categoria", datos.getEstado());
            /*adicione estas lineas para verificar sino existe un usuario con el mismo correo INICIO. */
            //Cursor fila —— this.getWritableDatabase().rawQuery(”select codigo from adiculos where codigo——”’+datos.getCodigo()+”’”, null),
            Cursor fila = bd().rawQuery("select id_categoria from tb_categoria where id_categoria='" + datos.getId_categoria() + "'", null);
            if (fila.moveToFirst() == true) {
                estado = false;
            } else {
                /*FIN*/
                //estado —— (boolean)this.getWritableDatabase().insed(”datos”,”nombre, correo, telefono”,registro),
                //resultado —— (int) this.getWritableDatabase().insed(”adiculos”, "codigo, descripcion,precio", registro),"
                resultado = (int) bd().insert("tb_categoria", null, registro);
                //resu/tado —— (int) this.getWritableDatabase().insert(”articulos", "codigo", registro),
                if (resultado > 0) estado = true;
                else estado = false;

            }
        } catch (Exception e) {
            estado = false;
            Log.e("error.", e.toString());

        }
        return estado;
    }


    public boolean InsertRegisterCategorias(Categoria datos) {
        boolean estado = true;
        int resultado;
        try {

            int id_categoria = datos.getId_categoria();
            String nom_categoria = datos.getNombre();
            int estado_categoria = datos.getEstado();

            //getting the current time for joining date
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String fecha1 = sdf.format(cal.getTime());

            Cursor fila = bd().rawQuery("select id_categoria from tb_categoria where id_categoria='" +
                    datos.getId_categoria() + "'", null);
            if (fila.moveToFirst() == true) {
                estado = false;

            } else {
                //estado —— (boolean)this.getWritableDatabase().insed(”datos”,”nombre, correo, telefono”, registro),
                //resultado —— (int) this.getWritableDatabase().insert(”usuarios”, "nombres, apellidos, usuario, clave,pregunta,respuesta", registro),
                String SQL = "INSERT INTO tb_categoria \n" + "(id_categoria,nom_categoria,estado_categoria)\n" + "VALUES\n" + "(?,?,?);";

                //resultado —— (int) this.getWritableDatabase().insed(”usuarios”, nombres, apellidos, usuario, clave,pregunta,respuesta”, registro),
                bd().execSQL(SQL, new String[]{String.valueOf(id_categoria), nom_categoria, String.valueOf(estado_categoria)});
                //if (resultado > 0) estado —— true,’
                //else estado —— false,
                estado = true;
            }
        } catch (Exception e) {
            estado = false;
            Log.e("error.", e.toString());
        }
        return estado;
    }

    public boolean consultaIdCategorias(Categoria datos) {
        boolean estado = true;
        int resultado;
        SQLiteDatabase bd = this.getWritableDatabase();
        try {

            int id_categoria = datos.getId_categoria();
            //Cursor fila —— bd.rawQuery(”select descripcion, precio from articulos where codigo——”’+ codigo +	', null),’
            //Cursor fila —— bd.rawQuery(”select descripcion, precio from articulos where codigo——” + codigo, null),”
            Cursor fila = bd.rawQuery("select id_categoria, nom_categoria, estado_categoria from tb_categoria where id_categoria=" + id_categoria, null);
            if (fila.moveToFirst()) {
                datos.setId_categoria(Integer.parseInt(fila.getString(0)));
                datos.setNombre(fila.getString(1));
                datos.setEstado(Integer.parseInt(fila.getString(2)));
                estado = true;
            } else {
                estado = false;
            }
            bd.close();
        } catch (Exception e) {
            estado = false;
            Log.e("error.", e.toString());
        }
        return estado;
    }
    //Otra forma de realizar una consulta. método opcional de realizar.

    public boolean consultaCategorias(Categoria datos) {
        boolean estado = true;
        int resultado;

        //SQLiteDatabase bd —— this.getWritableDatabase(),
        SQLiteDatabase bd = this.getReadableDatabase();
        try {
            String[] parametros = {String.valueOf(datos.getId_categoria())};
            String[] campos = {"id_categoria", "nom_categoria", "estado_categoria"};
            Cursor fila = bd.query("tb_categoria", campos, "id_categoria=?", parametros, null, null, null);
            //fila. moveToFirst(),
            if (fila.moveToFirst()) {
                datos.setId_categoria(Integer.parseInt(fila.getString(0)));
                datos.setNombre(fila.getString(1));
                datos.setEstado(Integer.parseInt(fila.getString(2)));
                estado = true;
            } else {
                estado = false;
            }
            fila.close();
            bd.close();
        } catch (Exception e) {
            estado = false;
            Log.e("error.", e.toString());
        }
        return estado;
    }
    //Fin del método opcional


    public boolean consultarNombreCategoria(Categoria datos) {
        boolean estado = true;
        int resultado;
        SQLiteDatabase bd = this.getWritableDatabase();
        try {
            String nombre = datos.getNombre();
            Cursor fila = bd.rawQuery("select id_categoria, nom_categoria, estado_categoria from tb_categoria" +
                    "where nom_categoria='" + nombre + "'", null);
            if (fila.moveToFirst()) {
                datos.setId_categoria(Integer.parseInt(fila.getString(0)));
                datos.setNombre(fila.getString(1));
                datos.setEstado(Integer.parseInt(fila.getString(2)));
                estado = true;
            } else {
                estado = false;
            }
            bd.close();
        } catch (Exception e) {
            estado = false;
            Log.e("error.", e.toString());
        }
        return estado;
    }

    public boolean bajaIdCategorias(final Context context, final Categoria datos) {
        //SQLiteDatabase bd —— this.getWritableDatabase(),
        estadoDelete = true;
        try {
            int id = datos.getId_categoria();
            Cursor fila = bd().rawQuery("select * from tb_categoria where id_categoria=" + id, null);
            if (fila.moveToFirst()) {
                datos.setId_categoria(Integer.parseInt(fila.getString(0)));
                datos.setNombre(fila.getString(1));
                datos.setEstado(Integer.parseInt(fila.getString(2)));
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setIcon(R.drawable.ic_borrar);
                builder.setTitle("Warning");
                builder.setMessage("¿Esta seguro de borrar el registro? \nid_categoria: " + datos.getId_categoria() + "\nNombre: " + datos.getNombre());
                builder.setCancelable(false);
                builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //String[] parametros —— {String. valueOf(datos.getCodigo())},
                        int id = datos.getId_categoria();
                        int cant = bd().delete("tb_categoria", "id_categoria=" + id, null);
                        //bd(). delete(”articulos”,”codigo——?”,parametros),
                        if (cant > 0) {
                            estadoDelete = true;
                            Toast.makeText(context, "Registro eliminado satisfactoriamente.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            estadoDelete = false;
                        }
                        bd().close();
                    }
                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            } else {
                Toast.makeText(context, "No hay resultados encontrados para la busqueda especificada.", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            estadoDelete = false;
            Log.e("Error.", e.toString());
        }
        return estadoDelete;
    }

    public boolean modificarCategorias(Categoria datos) {
        boolean estado = true;
        int resultado;
        SQLiteDatabase bd = this.getWritableDatabase();
        try {
            int id = datos.getId_categoria();
            String nombre = datos.getNombre();
            int estado_categoria= datos.getEstado();

            //String[j parametros —— {String. vaIueOf(datos.getCodigo())},
            ContentValues registro = new ContentValues();
            registro.put("id_categoria", id);
            registro.put("nom_categoria", nombre);
            registro.put("estado_categoria", estado_categoria);
            //int cant —— (int) this.getWritableDatabase().update(”adiculos”, registro, "codigo——" + codigo, null),”
            int cant = (int) bd.update("tb_categoria", registro, "id_categoria=" + id, null);
            //bd.update("articulos”,registro,"codigo——?”,parametros),’
            bd.close();
            if (cant > 0) estado = true;
            else estado = false;
        } catch (Exception e) {
            estado = false;
            Log.e("error.", e.toString());
        }
        return estado;
    }

    //lnicio del Método para crear lista de datos de la BD en el Spinner.
    //public void consultaListaAdiculos(){
    public ArrayList<Categoria> consultaListaCategorias() {
        boolean estado = false;
        //SQLiteDatabase bd —— this.getWritableDatabase(),
        SQLiteDatabase bd = this.getReadableDatabase();
        Categoria categoria = null;    //Creamos la instancia vacia.
        categoriasList = new ArrayList<Categoria>();

        try {

            Cursor fila = bd.rawQuery("select * from tb_categoria", null);
            while (fila.moveToNext()) {
                categoria = new Categoria();
                categoria.setId_categoria(fila.getInt(0));
                categoria.setNombre(fila.getString(1));
                categoria.setEstado(fila.getInt(2));

                categoriasList.add(categoria);

                Log.i("id_categoria", String.valueOf(categoria.getId_categoria()));
                Log.i("nom_categoria", categoria.getNombre().toString());
                Log.i("estado_categoria", String.valueOf(categoria.getEstado()));
            }

            obtenerListaCategorias();

        } catch (Exception e) {

        }
        return categoriasList;
    }

    public ArrayList<String> obtenerListaCategorias() {
        listaCategorias = new ArrayList<String>();
        //IistaArticulos —— new ArrayList<>(),
        listaCategorias.add("Seleccione");

        for (int i = 0; i < categoriasList.size(); i++) {
            //IistaArticulos.add(String. valueOf(articulosList.get(i).getCodigo())),
            listaCategorias.add(categoriasList.get(i).getId_categoria() + " ~ " + categoriasList.get(i).getNombre());
        }
        //bd(). close(),
        return listaCategorias;
    }
    //Fin del Spinner.


    //Inicio del Método para crear lista de datos de la BD en el ListView.

    public ArrayList<String> consultaListaCategorias1() {
        boolean estado = false;
        //SQLiteDatabase bd —— this.getWritableDatabase(),
        SQLiteDatabase bd = this.getReadableDatabase();

        Categoria categoria = null;    //Creamos la instancia vacia.
        categoriasList = new ArrayList<Categoria>();

        try {
            Cursor fila = bd.rawQuery("select * from tb_categoria", null);
            while (fila.moveToNext()) {
                categoria = new Categoria();
                categoria.setId_categoria(fila.getInt(0));
                categoria.setNombre(fila.getString(1));
                categoria.setEstado(fila.getInt(2));

                categoriasList.add(categoria);
            }

            listaCategorias = new ArrayList<String>();
            //IistaArticulos —— new ArrayList<>(),
            //IistaArticulos.add(”Seleccione"),

            for (int i = 0; i <= categoriasList.size(); i++) {
                //IistaArticulos. add(String. valueOf(adiculosList.get(i).getCodigo())),
                listaCategorias.add(categoriasList.get(i).getId_categoria() + " ~ " + categoriasList.get(i).getNombre());
            }
            //bd(). close(),
            //return IistaArticulos,’

        } catch (Exception e) {

        }
        //return articulosList,”
        return listaCategorias;

    }

    public List<Categoria> mostrarCategorias(){
        SQLiteDatabase bd = this.getReadableDatabase();
        Cursor cursor = bd.rawQuery("SELECT * FROM tb_categoria order by id_categoria desc", null);
        List<Categoria> categorias = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                categorias.add(new Categoria(cursor.getInt(0), cursor.getString(1), cursor.getInt(2)));
            }while (cursor.moveToNext());
        }
        return categorias;
    }



    // <<<<<< Metodos para consultas Productos >>>>>>>>>>>>>>>>
    public boolean InserProductos(Producto produ) {
        boolean estate = true;
        int resultado;
        //SQLiteDatabase bd —— this.getWritableDatabase(),
        try {
            int id_producto = produ.getId_producto();
            String nombre = produ.getNombre();
            String descripcion = produ.getDescripcion();
            double stock = produ.getStock();
            double precio = produ.getPrecio();
            String unidad_de_medida = produ.getUnidad_de_medida();
            int estado = produ.getEstado();
            String fecha_entrada = produ.getFecha_entrada();
            int categoria = produ.getCategoria();


            //Cursor fila —— this.getWritableDatabase().rawQuery(”select codigo from adiculos where codigo——”’+codigo+ ’, null),
            Cursor fila = bd().rawQuery("select id_producto from tb_producto where id_producto='" + id_producto + "'", null);
            if (fila.moveToFirst() == true) {
                estate = false;
            } else {
                //estado —— (boolean)this.getWritableDatabase().insed(”datos”,”nombre, correo, telefono”, registro),
                //resultado —— (int) this.getWritableDatabase().insert(”usuarios”, "nombres, apellidos, usuario, clave,pregunta,respuesta", registro),
                String SQL = "INSERT INTO tb_producto \n" + "(id_producto,nombre,descripcion,stock,precio,unidad_de_medida,estado,fecha_entrada,categoria)\n" + "VALUES \n" +
                        "('" + id_producto + "', '" + nombre + "', '" + descripcion + "', '" + stock + "', '" + precio + "', '" + unidad_de_medida + "', '" + estado + "', '" + categoria + "', '" + fecha_entrada + "');";
                bd().execSQL(SQL);
                bd().close();

        /*
        this.getWritableDatabase().execSQL(SQL):
        this.getWritabIeDatabase(). close();

        */

                estate = true;
            }

        } catch (Exception e) {
            estate = false;
            Log.e("error.", e.toString());
        }
        return estate;
    }



    public boolean insertardatosProducto(Producto produ) {
        boolean estado = true;
        int resultado;
        ContentValues registro = new ContentValues();
        try {
            //registro.put(”codigo",datos.getCodigo());
            registro.put("id_producto", produ.getId_producto());
            registro.put("nombre", produ.getNombre());
            registro.put("descripcion", produ.getDescripcion());
            registro.put("stock", produ.getStock());
            registro.put("precio", produ.getPrecio());
            registro.put("unidad_de_medida", produ.getUnidad_de_medida());
            registro.put("estado", produ.getEstado());
            registro.put("fecha_entrada", produ.getFecha_entrada());
            registro.put("categoria", produ.getCategoria());

            /*adicione estas lineas para verificar sino existe un usuario con el mismo correo INICIO. */
            //Cursor fila —— this.getWritableDatabase().rawQuery(”select codigo from adiculos where codigo——”’+datos.getCodigo()+”’”, null),
            Cursor fila = bd().rawQuery("select id_producto from tb_producto where id_producto='" + produ.getId_producto() + "'", null);
            if (fila.moveToFirst() == true) {
                estado = false;
            } else {
                resultado = (int) bd().insert("tb_producto", null, registro);
                //resu/tado —— (int) this.getWritableDatabase().insert(”articulos", "codigo", registro),
                if (resultado > 0) estado = true;
                else estado = false;

            }
        } catch (Exception e) {
            estado = false;
            Log.e("error.", e.toString());

        }
        return estado;
    }


    public boolean InsertRegisterProducto(Producto produ) {
        boolean estate = true;
        int resultado;
        try {

            int id_producto = produ.getId_producto();
            String nombre = produ.getNombre();
            String descripcion = produ.getDescripcion();
            double stock = produ.getStock();
            double precio = produ.getPrecio();
            String unidad_de_medida = produ.getUnidad_de_medida();
            int estado = produ.getEstado();
            String fecha_entrada = produ.getFecha_entrada();
            int categoria = produ.getCategoria();

            //getting the current time for joining date
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String fecha1 = sdf.format(cal.getTime());

            Cursor fila = bd().rawQuery("select id_producto from tb_producto where id_producto='" +
                    produ.getId_producto() + "'", null);
            if (fila.moveToFirst() == true) {
                estate = false;

            } else {
                //estado —— (boolean)this.getWritableDatabase().insed(”datos”,”nombre, correo, telefono”, registro),
                //resultado —— (int) this.getWritableDatabase().insert(”usuarios”, "nombres, apellidos, usuario, clave,pregunta,respuesta", registro),
                String SQL = "INSERT INTO tb_producto \n" + "(id_producto,nombre,descripcion,stock,precio,unidad_de_medida,estado,fecha_entrada,categoria)\n" + "VALUES\n" + "(?,?,?,?,?,?,?,?,?);";

                //resultado —— (int) this.getWritableDatabase().insed(”usuarios”, nombres, apellidos, usuario, clave,pregunta,respuesta”, registro),
                bd().execSQL(SQL, new String[]{String.valueOf(id_producto), nombre, descripcion, String.valueOf(stock), String.valueOf(precio),
                unidad_de_medida, String.valueOf(unidad_de_medida), String.valueOf(estado), fecha_entrada, String.valueOf(categoria)
                });
                //if (resultado > 0) estado —— true,’
                //else estado —— false,
                estate = true;
            }
        } catch (Exception e) {
            estate = false;
            Log.e("error.", e.toString());
        }
        return estate;
    }

    public boolean consultaIdProducto(Producto produ) {
        boolean estado = true;
        int resultado;
        SQLiteDatabase bd = this.getWritableDatabase();
        try {

            int id_producto = produ.getId_producto();
            //Cursor fila —— bd.rawQuery(”select descripcion, precio from articulos where codigo——”’+ codigo +	', null),’
            //Cursor fila —— bd.rawQuery(”select descripcion, precio from articulos where codigo——” + codigo, null),”
            Cursor fila = bd.rawQuery("select id_producto,nombre,descripcion,stock,precio,unidad_de_medida,estado,fecha_entrada,categoria, " +
                    "from tb_producto where id_producto=" + id_producto, null);
            if (fila.moveToFirst()) {
                produ.setId_producto(Integer.parseInt(fila.getString(0)));
                produ.setNombre(fila.getString(1));
                produ.setDescripcion(fila.getString(2));
                produ.setStock(Double.parseDouble(fila.getString(3)));
                produ.setPrecio(Double.parseDouble(fila.getString(4)));
                produ.setUnidad_de_medida(fila.getString(5));
                produ.setEstado(Integer.parseInt(fila.getString(6)));
                produ.setFecha_entrada(fila.getString(7));
                produ.setCategoria(Integer.parseInt(fila.getString(8)));
                estado = true;
            } else {
                estado = false;
            }
            bd.close();
        } catch (Exception e) {
            estado = false;
            Log.e("error.", e.toString());
        }
        return estado;
    }
    //Otra forma de realizar una consulta. método opcional de realizar.

    public boolean consultaProducto(Producto produ) {
        boolean estado = true;
        int resultado;

        //SQLiteDatabase bd —— this.getWritableDatabase(),
        SQLiteDatabase bd = this.getReadableDatabase();
        try {
            String[] parametros = {String.valueOf(produ.getId_producto())};
            String[] campos = {"id_producto", "nombre", "descripcion", "stock", "precio", "unidad_de_medida", "estado", "fecha_entrada", "categoria"};
            Cursor fila = bd.query("tb_producto", campos, "id_producto=?", parametros, null, null, null);
            //fila. moveToFirst(),
            if (fila.moveToFirst()) {
                produ.setId_producto(Integer.parseInt(fila.getString(0)));
                produ.setNombre(fila.getString(1));
                produ.setDescripcion(fila.getString(2));
                produ.setStock(Double.parseDouble(fila.getString(3)));
                produ.setPrecio(Double.parseDouble(fila.getString(4)));
                produ.setUnidad_de_medida(fila.getString(5));
                produ.setEstado(Integer.parseInt(fila.getString(6)));
                produ.setFecha_entrada(fila.getString(7));
                produ.setCategoria(Integer.parseInt(fila.getString(8)));
                estado = true;
            } else {
                estado = false;
            }
            fila.close();
            bd.close();
        } catch (Exception e) {
            estado = false;
            Log.e("error.", e.toString());
        }
        return estado;
    }
    //Fin del método opcional


    public boolean consultarNombrePro(Producto produ) {
        boolean estado = true;
        int resultado;
        SQLiteDatabase bd = this.getWritableDatabase();
        try {
            String nombre = produ.getNombre();
            Cursor fila = bd.rawQuery("select id_producto, nombre, descripcion, stock, precio, unidad_de_medida, estado, " +
                    "fecha_entrada, categoria from tb_producto" +
                    "where nombre='" + nombre + "'", null);
            if (fila.moveToFirst()) {
                produ.setId_producto(Integer.parseInt(fila.getString(0)));
                produ.setNombre(fila.getString(1));
                produ.setDescripcion(fila.getString(2));
                produ.setStock(Double.parseDouble(fila.getString(3)));
                produ.setPrecio(Double.parseDouble(fila.getString(4)));
                produ.setUnidad_de_medida(fila.getString(5));
                produ.setEstado(Integer.parseInt(fila.getString(6)));
                produ.setFecha_entrada(fila.getString(7));
                produ.setCategoria(Integer.parseInt(fila.getString(8)));
                estado = true;
            } else {
                estado = false;
            }
            bd.close();
        } catch (Exception e) {
            estado = false;
            Log.e("error.", e.toString());
        }
        return estado;
    }

    public boolean bajaIdProducto(final Context context, final Producto produ) {
        //SQLiteDatabase bd —— this.getWritableDatabase(),
        estadoDelete = true;
        try {
            int id = produ.getId_producto();
            Cursor fila = bd().rawQuery("select * from tb_producto where id_producto=" + id, null);
            if (fila.moveToFirst()) {
                produ.setId_producto(Integer.parseInt(fila.getString(0)));
                produ.setNombre(fila.getString(1));
                produ.setDescripcion(fila.getString(2));
                produ.setStock(Double.parseDouble(fila.getString(3)));
                produ.setPrecio(Double.parseDouble(fila.getString(4)));
                produ.setUnidad_de_medida(fila.getString(5));
                produ.setEstado(Integer.parseInt(fila.getString(6)));
                produ.setFecha_entrada(fila.getString(7));
                produ.setCategoria(Integer.parseInt(fila.getString(8)));
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setIcon(R.drawable.ic_borrar);
                builder.setTitle("Warning");
                builder.setMessage("¿Esta seguro de borrar el registro? \nid_producto: " + produ.getId_producto() + "\nNombre: " + produ.getNombre());
                builder.setCancelable(false);
                builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //String[] parametros —— {String. valueOf(datos.getCodigo())},
                        int id = produ.getId_producto();
                        int cant = bd().delete("tb_producto", "id_producto=" + id, null);
                        //bd(). delete(”articulos”,”codigo——?”,parametros),
                        if (cant > 0) {
                            estadoDelete = true;
                            Toast.makeText(context, "Registro eliminado satisfactoriamente.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            estadoDelete = false;
                        }
                        bd().close();
                    }
                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            } else {
                Toast.makeText(context, "No hay resultados encontrados para la busqueda especificada.", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            estadoDelete = false;
            Log.e("Error.", e.toString());
        }
        return estadoDelete;
    }

    public boolean modificarProducto(Producto produ) {
        boolean estado = true;
        int resultado;
        SQLiteDatabase bd = this.getWritableDatabase();
        try {
            int id_producto = produ.getId_producto();
            String nombre = produ.getNombre();
            String descripcion = produ.getDescripcion();
            double stock = produ.getStock();
            double precio = produ.getPrecio();
            String unidad_de_medida = produ.getUnidad_de_medida();
            int estado_producto = produ.getEstado();
            String fecha_entrada = produ.getFecha_entrada();
            int categoria = produ.getCategoria();

            //String[j parametros —— {String. vaIueOf(datos.getCodigo())},

            ContentValues registro = new ContentValues();
            registro.put("id_producto", produ.getId_producto());
            registro.put("nombre", produ.getNombre());
            registro.put("descripcion", produ.getDescripcion());
            registro.put("stock", produ.getStock());
            registro.put("precio", produ.getPrecio());
            registro.put("unidad_de_medida", produ.getUnidad_de_medida());
            registro.put("estado", produ.getEstado());
            registro.put("fecha_entrada", produ.getFecha_entrada());
            registro.put("categoria", produ.getCategoria());
            //int cant —— (int) this.getWritableDatabase().update(”adiculos”, registro, "codigo——" + codigo, null),”
            int cant = (int) bd.update("tb_producto", registro, "id_producto=" + id_producto, null);
            //bd.update("articulos”,registro,"codigo——?”,parametros),’
            bd.close();
            if (cant > 0) estado = true;
            else estado = false;
        } catch (Exception e) {
            estado = false;
            Log.e("error.", e.toString());
        }
        return estado;
    }

    //lnicio del Método para crear lista de datos de la BD en el Spinner.
    //public void consultaListaProducto(){
    public ArrayList<Producto> consultaListaProducto() {
        boolean estado = false;
        //SQLiteDatabase bd —— this.getWritableDatabase(),
        SQLiteDatabase bd = this.getReadableDatabase();
        Producto producto = null;    //Creamos la instancia vacia.
        productosList = new ArrayList<Producto>();

        try {

            Cursor fila = bd.rawQuery("select * from tb_producto", null);
            while (fila.moveToNext()) {
                producto = new Producto();
                producto.setId_producto(Integer.parseInt(fila.getString(0)));
                producto.setNombre(fila.getString(1));
                producto.setDescripcion(fila.getString(2));
                producto.setStock(Double.parseDouble(fila.getString(3)));
                producto.setPrecio(Double.parseDouble(fila.getString(4)));
                producto.setUnidad_de_medida(fila.getString(5));
                producto.setEstado(Integer.parseInt(fila.getString(6)));
                producto.setFecha_entrada(fila.getString(7));
                producto.setCategoria(Integer.parseInt(fila.getString(8)));

                productosList.add(producto);

                Log.i("id_producto", String.valueOf(producto.getId_producto()));
                Log.i("nombre", producto.getNombre().toString());
                Log.i("descripcion", producto.getDescripcion().toString());
                Log.i("stock", String.valueOf(producto.getStock()));
                Log.i("precio", String.valueOf(producto.getPrecio()));
                Log.i("unidad_de_medida", producto.getUnidad_de_medida().toString());
                Log.i("estado", String.valueOf(producto.getEstado()));
                Log.i("fecha_entrada", producto.getFecha_entrada().toString());
                Log.i("categoria", String.valueOf(producto.getCategoria()));
            }

            obtenerListaProductos();

        } catch (Exception e) {

        }
        return productosList;
    }

    public ArrayList<String> obtenerListaProductos() {
        listaProductos = new ArrayList<String>();
        //IistaArticulos —— new ArrayList<>(),
        listaProductos.add("Seleccione");

        for (int i = 0; i < categoriasList.size(); i++) {
            //IistaArticulos.add(String. valueOf(articulosList.get(i).getCodigo())),
            listaProductos.add(productosList.get(i).getId_producto() + " ~ " + productosList.get(i).getNombre());
        }
        //bd(). close(),
        return listaProductos;
    }
    //Fin del Spinner.


    //Inicio del Método para crear lista de datos de la BD en el ListView.
    public ArrayList<String> consultaListaProductos1() {
        boolean estado = false;
        //SQLiteDatabase bd —— this.getWritableDatabase(),
        SQLiteDatabase bd = this.getReadableDatabase();

        Producto producto = null;    //Creamos la instancia vacia.
        categoriasList = new ArrayList<Categoria>();

        try {
            Cursor fila = bd.rawQuery("select * from tb_categoria", null);
            while (fila.moveToNext()) {
                producto = new Producto();
                producto.setId_producto(Integer.parseInt(fila.getString(0)));
                producto.setNombre(fila.getString(1));
                producto.setDescripcion(fila.getString(2));
                producto.setStock(Double.parseDouble(fila.getString(3)));
                producto.setPrecio(Double.parseDouble(fila.getString(4)));
                producto.setUnidad_de_medida(fila.getString(5));
                producto.setEstado(Integer.parseInt(fila.getString(6)));
                producto.setFecha_entrada(fila.getString(7));
                producto.setCategoria(Integer.parseInt(fila.getString(8)));

                productosList.add(producto);
            }

            listaProductos = new ArrayList<String>();
            //IistaArticulos —— new ArrayList<>(),
            //IistaArticulos.add(”Seleccione"),

            for (int i = 0; i <= productosList.size(); i++) {
                //IistaArticulos. add(String. valueOf(adiculosList.get(i).getCodigo())),
                listaProductos.add(productosList.get(i).getId_producto() + " ~ " + productosList.get(i).getNombre());
            }
            //bd(). close(),
            //return IistaArticulos,’

        } catch (Exception e) {

        }
        //return articulosList,”
        return listaProductos;

    }

    public List<Producto> mostrarProductos(){
        SQLiteDatabase bd = this.getReadableDatabase();
        Cursor cursor = bd.rawQuery("SELECT * FROM tb_producto order by id_producto desc", null);
        List<Producto> productos = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                productos.add(new Producto(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getDouble(3),
                        cursor.getDouble(4), cursor.getString(5), cursor.getInt(6), cursor.getString(7), cursor.getInt(8)));
            }while (cursor.moveToNext());
        }
        return productos;
    }
}
