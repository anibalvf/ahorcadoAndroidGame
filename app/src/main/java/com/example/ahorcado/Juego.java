package com.example.ahorcado;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Juego extends AppCompatActivity {




    int intentos = 0;
    ImageView im;
    boolean terminado = false;
    String  palabras[] = new String[]{"avion","madera","tejado","pajaro","hielo","teclado","avispa","casa","delfin","iglesia",
    "mar","tiburon","jirafa","medusa","movil","television",};
    int[] ids = new int[]{R.drawable.horca0,R.drawable.horca0,R.drawable.horca1,R.drawable.horca2,
                          R.drawable.horca3,R.drawable.horca4,R.drawable.horca5,R.drawable.horca6,
                          R.drawable.horca7,R.drawable.horca8,R.drawable.horca9};

    String palabraAleatoria;
    TextView textoPantalla;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        Bundle datos = this.getIntent().getExtras();


        int variableNumeroJugadoresMain = datos.getInt("jugadoresMenu");
        if (variableNumeroJugadoresMain==1){
            String variablepalabraMistery = datos.getString("palabraSegundoJugador");
            palabraAleatoria=variablepalabraMistery;
        }else{
            Random r1 = new Random();
            int pala= r1.nextInt(palabras.length);
            palabraAleatoria = palabras[pala];
        }
        textoPantalla= (TextView)findViewById(R.id.textoDescubrir);
        ponerPalabraMistery();
        textoPantalla.setLetterSpacing(0.35f);
        im = findViewById(R.id.fotosahorcado);
    }

    //Metodo privado para poner "_" en cada letra de la palabra al inicar la partida
    private void ponerPalabraMistery(){
        String text="";
        for (int i=0;i<palabraAleatoria.length();i++){
            text=text+"_";
        }
        textoPantalla.setText(text);
    }




    public void pulsarLetra(View v){
        Button b = (Button) v;
        String letra = b.getText().toString();

        if (!terminado && intentos<=10) {
            if (estaLaLetra(letra,b)) {
                comprobarGanado();
            } else {
                  restarIntento();
            }
        }
    }

    public void restarIntento(){
            if (intentos ==0 ){
                im.setVisibility(View.VISIBLE);
            }
            intentos++;
            if (ids.length>intentos) {
                im.setImageResource(ids[intentos]);
            }

        if (intentos== ids.length-1){
            terminado=true;
            Toast.makeText(this, "Has muerto, la palabra es "+palabraAleatoria, Toast.LENGTH_SHORT).show();
        }
    }


     public void comprobarGanado(){

        if (textoPantalla.getText().toString().equalsIgnoreCase(palabraAleatoria)){
            Toast.makeText(this, "GANADO", Toast.LENGTH_SHORT).show();
            terminado= true;
        }
     }


    //Metodo para comprobar si esta la letra, en el caso de que este la cambiara en la pantalla
    public boolean estaLaLetra(String letra,Button b){
        boolean res = false;
        String nuevo,cadena;
        String tpan =textoPantalla.getText().toString(); //seleccionamos el texto de la pantalla actual

        for (int i=0; i<palabraAleatoria.length();i++){ // recorremos la palabra misteriosa entera

            cadena = Character.toString(palabraAleatoria.charAt(i)); // separamos cada caracter y lo transformamos a String para ser mas facil la comparacion

            if (letra.equalsIgnoreCase(cadena)){// comparamos la letra que selecciona el jugador con la posicion extraida de el char de arriba si esta la reemplaza en pantalla
                nuevo = new StringBuilder(tpan).replace(i, i+1, letra).toString();
                textoPantalla.setText(nuevo);
                tpan = nuevo;
                res=true;
            }
        }
        //por ultimo voy a añadir que se ponga el boton de color y se desabilite en funcion de si esta o no
        if (res==true){
            b.setBackgroundColor(Color.parseColor("#8800ff00"));
        }else{
            b.setBackgroundColor(Color.parseColor("#88ff0000"));
        }
        b.setEnabled(false);



        return res;
    }




}