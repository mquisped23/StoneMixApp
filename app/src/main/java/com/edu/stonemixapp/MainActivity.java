package com.edu.stonemixapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Ponemos un thread para que la pantalla de carga dure 2 segundos
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //hacemos que despues de cargar la pantalla de carga se muestre la pantalla normal
        setTheme(R.style.Theme_StoneMixApp);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}