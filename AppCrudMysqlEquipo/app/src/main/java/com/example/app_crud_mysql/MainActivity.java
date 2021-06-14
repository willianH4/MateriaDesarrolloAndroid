package com.example.app_crud_mysql;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;

import com.example.app_crud_mysql.ui.categoria.Categoria;
import com.example.app_crud_mysql.ui.dashboard.AdaptadorCategoria;
import com.example.app_crud_mysql.ui.dashboard.DashboardFragment;
import com.example.app_crud_mysql.ui.dashboard.ListviewCategoria;
import com.example.app_crud_mysql.ui.home.HomeFragment;
import com.example.app_crud_mysql.ui.producto.Producto;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;

    private static final String TAG = "MainActivity";

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_categoria, R.id.nav_producto, R.id.nav_listviewCategoria, R.id.nav_recycleviewcategoria)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        //navegador inferior
        bottomNavigationView = findViewById(R.id.bottom_navigation_view);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        bottomNavigationView.setSelectedItemId(R.id.nav_home);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
//instanciando los Fragmente para usarlos en el menu
    HomeFragment homeFragment = new HomeFragment();
    Categoria categoriaFragm = new Categoria();
    Producto productoFragment = new Producto();


    ListviewCategoria listviewcategoriaFragm = new ListviewCategoria();
    DashboardFragment dashboardFragment = new DashboardFragment();



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.nav_host_fragment, homeFragment).commit();
                return true;

            case R.id.nav_categoria:
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.nav_host_fragment, categoriaFragm).commit();
                return true;

            case R.id.nav_producto:
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.nav_host_fragment, productoFragment).commit();
                return true;

            case R.id.nav_listviewCategoria:
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.nav_host_fragment, listviewcategoriaFragm).commit();
                return true;

            case R.id.nav_recycleviewcategoria:
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.nav_host_fragment, dashboardFragment).commit();
                return true;
        }

        return false;
    }
}