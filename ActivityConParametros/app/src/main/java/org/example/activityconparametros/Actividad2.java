package org.example.activityconparametros;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

public class Actividad2 extends AppCompatActivity {

    private WebView webview1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad2);

        webview1 = (WebView) findViewById(R.id.webview1);
        Bundle bundle = getIntent().getExtras();
        String dato = bundle.getString("direccion");
        webview1.loadUrl("http://" + dato);

    }

    public void finalizar(View v){

        finish();
    }
}