package com.mjgl.demomysql2020;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class dialogytoast {

    Dialog myDialog;
    androidx.appcompat.app.AlertDialog.Builder dialogo;
    AlertDialog.Builder dialogo1;


    public void ToastPersonalizado(Context context, LayoutInflater inflater, View view, String message) {
        //LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout, (ViewGroup) view.findViewById(R.id.toast_root));

        TextView toastText = layout.findViewById(R.id.toast_text);
        ImageView toastImage = layout.findViewById(R.id.toast_image);

        toastText.setText(message);
        toastImage.setImageResource(R.drawable.ic_delete);

        Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);

        toast.show();
    }






    public void dialogConfirm(final Context context){
        String mensaje = "¿Clic en Aceptar para Confirmar Accion?";
        //final android.app.AlertDialog.Builder mBuilder = new android.app.AlertDialog.Builder(context);
        //dialogo = new androidx.appcompat.app.AlertDialog.Builder(context);
        dialogo = new androidx.appcompat.app.AlertDialog.Builder(context);
        //dialogo = new AlertDialog.Builder(context);
        dialogo.setIcon(R.drawable.ic_delete);
        dialogo.setTitle("Warning");
        dialogo.setMessage(mensaje);
        dialogo.setCancelable(true);

        dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo, int id) {
                //Acciones a ejecutar al dar clic en eliminar
                Toast.makeText(context, "Clic Aceptar.", Toast.LENGTH_LONG).show();
                //showToast1(context, "Registro eliminado correctamente");
            }
        });

        dialogo.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo, int id) {
                Toast.makeText(context, "Clic Cancelar.", Toast.LENGTH_LONG).show();
                //showToast1(context, "Operacion cancelada correctamente");
            }
        });
        dialogo.show();
    }






    public void dialogConfirm1(final Context context) {
        new androidx.appcompat.app.AlertDialog.Builder(context)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Warning")
                .setMessage("¿Clic en Aceptar para Confirmar Accion?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Intentamos activarlo con el siguiente intent.
                        Toast.makeText(context, "Clic en Boton SI.", Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Salimos de la app
                        Toast.makeText(context, "Clic en Boton NO.", Toast.LENGTH_LONG).show();
                    }
                })
                .show();
    }




    public void dialogConfirm2(final Context context){

        new AlertDialog.Builder(context)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Salir")
                .setMessage("¿Esta seguro?\n\nClic en ok para confirmar")
                .setNegativeButton(android.R.string.cancel, null)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {//un listener que al pulsar, cierre la aplicacion
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "Clic en Aceptar", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();

    }



    public void dialogConfirm3(final Context context){

        dialogo1 = new AlertDialog.Builder(context);
        dialogo1.setIcon(android.R.drawable.ic_dialog_alert);
        //dialog.setTitle("COCESNA.");
        dialogo1.setTitle("INFORMACION");
        dialogo1.setMessage("Contenido del mensaje");
        dialogo1.setCancelable(false);

        dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo, int id) {
                Toast toast = Toast.makeText(context, "Aceptado.", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });

        //dialogo1.setNegativeButton("Cancelar", null);
        dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo, int id) {
                Toast toast = Toast.makeText(context, "Cancelado.", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });

        dialogo1.show();
    }



    public void dialogConfirm4(final Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        //builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setIcon(R.drawable.ic_error);
        builder.setMessage(R.string.mensaje);
        builder.setCancelable(false);
        builder.setNeutralButton(R.string.ok, null);
        builder.setTitle(R.string.error);
        builder.create().show();
    }



    public void dialogConfirm5(final Context context) {
        myDialog = new Dialog(context);
        myDialog.setContentView(R.layout.confirmcustom);

        ImageView ivClose = (ImageView)myDialog.findViewById(R.id.ivClose);
        Button btnAccept = (Button)myDialog.findViewById(R.id.btnAccept);

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Clic en Aceptar.", Toast.LENGTH_SHORT).show();
            }
        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }



    public void dialogConfirm6(final Context context, String mensaje) {
        //dialogo1 = new AlertDialog.Builder(context);

        myDialog = new Dialog(context);
        myDialog.setContentView(R.layout.confirmcustom1);
        myDialog.setCancelable(false);

        TextView tv_mensaje = (TextView) myDialog.findViewById(R.id.tv_mensaje);
        tv_mensaje.setText(mensaje);

        ImageView ivClose = (ImageView)myDialog.findViewById(R.id.ivClose);
        Button btnAccept = (Button)myDialog.findViewById(R.id.btnAccept);
        Button btnCancel = myDialog.findViewById(R.id.btnCancel);

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Clic en Aceptar.", Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(context, MainActivity.class);
                //context.startActivity(intent);
                //startActivity(intent);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }



    public void dialogAbout(final Context context) {
        //dialogo1 = new AlertDialog.Builder(context);
        myDialog = new Dialog(context);
        myDialog.setContentView(R.layout.dialog_autor);
        myDialog.setCancelable(false);

        ImageView BtnCerrarAutor = myDialog.findViewById(R.id.BtnCerrarAutor);

        BtnCerrarAutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }



}










//Autor: Prof. Manuel de Jesús Gámez López