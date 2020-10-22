package com.example.ahorcado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void menuSeleccion (View v){
        int segundoJudagor = 0;
        int idPulsado = v.getId();
        Intent intent = null;
        EditText ed = (EditText)findViewById(R.id.palabraJugador);

        if (idPulsado == R.id.unJugador){
            intent = new Intent(this,Juego.class);
            intent.putExtra("jugadoresMenu",segundoJudagor);
            startActivity(intent);
        }else if (idPulsado == R.id.dosjugadores){
            String palabraMistery = ed.getText().toString();

            if (!palabraMistery.isEmpty()) {
                segundoJudagor = 1;
                intent = new Intent(this, Juego.class);
                intent.putExtra("jugadoresMenu", segundoJudagor);
                intent.putExtra("palabraSegundoJugador", palabraMistery);
                startActivity(intent);
            }else{
                Toast.makeText(this, "Introduce una palabra valida", Toast.LENGTH_SHORT).show();
            }
        }



    }



}