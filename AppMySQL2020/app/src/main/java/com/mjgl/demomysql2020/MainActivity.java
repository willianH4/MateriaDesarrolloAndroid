package com.mjgl.demomysql2020;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.FragmentTransaction;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.mjgl.demomysql2020.ui.categorias.Categorias;
import com.mjgl.demomysql2020.ui.consultas.busqueda_porcatetogias;
import com.mjgl.demomysql2020.ui.home.HomeFragment;
import com.mjgl.demomysql2020.ui.productos.Productos;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentManager;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    //Este objeto es la cola que usará la aplicación
    //private RequestQueue mRequestQueue;
    //RequestQueue requestQueue = Volley.newRequestQueue(this);

    private AppBarConfiguration mAppBarConfiguration;
    private DrawerLayout drawer;
    private NavigationView navigationView;

    Dialog myDialog;
    ProgressDialog progressDialog;
    AlertDialog.Builder dialogo;

    dialogytoast instancia = new dialogytoast();


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            new android.app.AlertDialog.Builder(this)
                    .setIcon(R.drawable.ic_close)
                    .setTitle("Advertencia")
                    .setMessage("¿Realmente desea salir?")
                    .setNegativeButton(android.R.string.cancel, null)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {//un listener que al pulsar, cierre la aplicacion
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .show();
            return true;
        }
        //para las demas cosas, se reenvia el evento al listener habitual
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_left));
        toolbar.setTitleTextColor(getResources().getColor(R.color.mycolor_blanco));
        toolbar.setTitleMargin(0, 0, 0, 0);
        toolbar.setSubtitle("CRUD");
        getSupportActionBar().setTitle("MYSQL");

        //Función para evitar la rotación de la pantalla del CELULAR.
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //y esto para pantalla completa (oculta incluso la barra de estado)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        FloatingActionButton fab = findViewById(R.id.fab);
        myDialog = new Dialog(this);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        //DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer = findViewById(R.id.drawer_layout);
        //NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }



        // However, if we're being restored from a previous state,
        // then we don't need to do anything and should return or else
        // we could end up with overlapping fragments.
        //Sin embargo, si nos recuperan de un estado anterior,
        //entonces no necesitamos hacer nada y deberíamos regresar o de lo contrario
        //podríamos terminar con fragmentos superpuestos.
        //if (savedInstanceState != null) {
        //   return;
        //}


        /*
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_productos, R.id.nav_categorias, R.id.nav_busquedaporcategorias)
                .setDrawerLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /*
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
     */


    @SuppressLint("WrongConstant")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.nav_home){
            getSupportFragmentManager().beginTransaction().addToBackStack(null);
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new HomeFragment()).commit();

        }else if(id == R.id.nav_categorias){
            //getSupportFragmentManager().getFragments().clear();
            //removeOtherAddedFragments(MainActivity.this, new HomeFragment());
            //getSupportFragmentManager().beginTransaction().remove(new HomeFragment()).commit();
            //getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentById(R.id.nav_host_fragment)).commit();
            getSupportFragmentManager().beginTransaction().addToBackStack(null);
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new Categorias()).commit();

        }else if(id == R.id.nav_productos){
            getSupportFragmentManager().beginTransaction().addToBackStack(null);
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new Productos()).commit();

        }else if(id == R.id.nav_busquedaporcategorias){
            getSupportFragmentManager().beginTransaction().addToBackStack(null);
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new busqueda_porcatetogias()).commit();

        }else  if (id == R.id.nav_acercade) {
            //Toast.makeText(getApplicationContext(), "Colon", Toast.LENGTH_SHORT).show();
            dialogAbout(getApplicationContext());

        }

        //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    public void dialogAbout(final Context context) {
        //dialogo1 = new AlertDialog.Builder(context);
        //myDialog = new Dialog(context);
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