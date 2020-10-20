package com.example.ahorcado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void menuSeleccion (View v){
        int idPulsado = v.getId();
        Intent intent = null;

        if (idPulsado == R.id.test){
            intent = new Intent(this,Juego.class);
        }

        startActivity(intent);

    }



}