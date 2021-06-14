package com.willianhdz.appinvertaryindividual;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Modal {
    Dialog myDialog;
    AlertDialog.Builder dialogo;
    boolean validalnput = false;
    String id_categoria;
    String nombre;
    String estado;

    SQLiteDatabase bd = null;
    Categoria datos = new Categoria();

    public void Search(final Context context)
    {
        myDialog = new Dialog(context);
        myDialog.setContentView(R.layout.ventana1);
        myDialog.setTitle("Search");
        myDialog.setCancelable(false);
        final ConexionSQLite conexion = new ConexionSQLite(context);
        final EditText et_id = (EditText)myDialog.findViewById(R.id.et_id);
        Button btn_buscar = (Button)myDialog.findViewById(R.id.btn_buscar);
        TextView tv_close = (TextView)myDialog.findViewById(R.id.tv_close);
        tv_close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                myDialog.dismiss();
            }

        });

        btn_buscar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (et_id.getText().toString().length() == 0) {
                    validalnput = false;
                    et_id.setError("Campo obligatorio");

                } else {
                    validalnput = true;
                }

                if (validalnput) {

                    String id = et_id.getText().toString();
                    datos.setId_categoria(Integer.parseInt(id));

                    if (conexion.consultaIdCategorias(datos)) {
                        id_categoria = String.valueOf(datos.getId_categoria());
                        nombre = datos.getNombre();
                        estado = String.valueOf(datos.getEstado());
                        String action;
                        Intent intent = new Intent(context, MainActivity.class);
                        intent.putExtra("senal", "1");
                        intent.putExtra("id_categoria", id);
                        intent.putExtra("nombre", nombre);
                        intent.putExtra("estado", estado);
                        context.startActivity(intent);

                        myDialog.dismiss();

                    } else {
                        Toast.makeText(context, "No se han encontrado resultados para la busqueda especificada.",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, "No ha especificado lo que desea buscar.",
                            Toast.LENGTH_SHORT).show();

                }
            }
        });

        myDialog.getWindow().setBackgroundDrawable
                (new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();

    }

}

