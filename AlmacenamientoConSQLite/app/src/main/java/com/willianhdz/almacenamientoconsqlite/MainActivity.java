package com.willianhdz.almacenamientoconsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText et1, et2, et3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText)findViewById(R.id.etCodigo);
        et2 = (EditText)findViewById(R.id.etDesc);
        et3 = (EditText)findViewById(R.id.etPrecio);
    }

    //Crear aqui un menu


    public void alta(View v){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String cod = et1.getText().toString();
        String descripcion = et2.getText().toString();
        String precio = et3.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("Codigo", cod);
        registro.put("descripcion", descripcion);
        registro.put("precio", precio);
        bd.insert("articulos", null, registro);
        bd.close();

        et1.setText("");
        et2.setText("");
        et3.setText("");
        Toast.makeText(this, "Se cargaron los datos del articulo", Toast.LENGTH_SHORT).show();
    }

    public void consultaporcodigo(View v){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String cod = et1.getText().toString();
        Cursor fila = bd.rawQuery("select descripcion, precio from articulos where codigo=" + cod, null);
        if (fila.moveToFirst()){
            et2.setText(fila.getString(0));
            et3.setText(fila.getString(1));
        }else{
            Toast.makeText(this, "No existe un articulo conn dicho codigo", Toast.LENGTH_SHORT).show();
            bd.close();
        }

    }

    public void consultapordescripcion(View v){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String desc = et2.getText().toString();
        Cursor fila = bd.rawQuery("select codigo, precio from articulos where descripcion='" + desc +"'", null);
        {
            if (fila.moveToFirst()){
                et1.setText(fila.getString(0));
                et2.setText(fila.getString(1));
            }else{
                Toast.makeText(this, "No existe un articulo con dicha descripcion", Toast.LENGTH_SHORT).show();
                bd.close();
            }
        }
    }

    public void bajaporcodigo(View v){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String cod = et1.getText().toString();
        int cantidad = bd.delete("articulos", "codigo=" + cod, null);
        bd.close();
        et1.setText("");
        et2.setText("");
        et3.setText("");

        if (cantidad == 1){
            Toast.makeText(this, "Se borro el articulo con dicho codigo", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "No existe un articulo con dicho codigo", Toast.LENGTH_SHORT).show();
        }
    }

    public void modificacion(View v){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String cod = et1.getText().toString();
        String descrip = et2.getText().toString();
        String prec = et3.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("codigo", cod);
        registro.put("descipcion", descrip);
        registro.put("precio", prec);

        int cant = bd.update("articulos", registro, "codigo=" + cod, null);
        bd.close();

        if (cant == 1){
            Toast.makeText(this, "Se modificaron los datos", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "No existe un articulo con el codigo ingresado", Toast.LENGTH_SHORT).show();
        }
    }
}