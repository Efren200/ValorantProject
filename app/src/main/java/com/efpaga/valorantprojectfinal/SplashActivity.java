package com.efpaga.valorantprojectfinal;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

//Esta activity sirve para crear la animacion de mostrar el logo para que quede mas profesional la aplicacion
//Se utiliza creando un estilo de activity y luego en el manifest cambiando el tema de la activity.
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
