package com.efpaga.valorantprojectfinal.ui.agentes;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.efpaga.valorantprojectfinal.R;
import com.efpaga.valorantprojectfinal.valorantapi.models.Agent;
import com.efpaga.valorantprojectfinal.valorantapi.models.AgentAbility;
import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.Objects;


//Activity que se carga una vez se haga onClick en un elemento del recyclerview
public class AgenteActivity extends AppCompatActivity {

    private RecyclerView habilidadReciclerView;
    private ShapeableImageView imagen;
    private TextView descripcion;
    private AgentAbility[] listaHabilidades;
    private HabilidadesAdapter habilidadesAdapter;
    private Agent agente;
    private ConstraintLayout fondoAgente;
    private MediaPlayer mediaPlayer;
    private String urlVoice;

    //Recuperamos el agente que nos pasa como parametro el intent y recuperamos cada uno de los elementos de la vista para introducir
    //las caracteristicas del agente seleccionado.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agente);
        Objects.requireNonNull(getSupportActionBar()).hide();
        habilidadReciclerView = findViewById(R.id.rvHabilidades);
        agente = (Agent) getIntent().getSerializableExtra("agente");

        urlVoice = agente.getVoiceLine().getMediaList()[0].getWave();
        try {
            playAudio(urlVoice);
        } catch (IOException e) {
            e.printStackTrace();
        }


        //Fondo agente
        fondoAgente = (ConstraintLayout) findViewById(R.id.fondoAgente);
        GradientDrawable gd = new GradientDrawable(
                GradientDrawable.Orientation.TL_BR,
                new int[] {Color.parseColor("#000000"),
                        Color.parseColor("#00ffff")
                        , Color.parseColor("#ffffff"),
                        Color.parseColor("#ff0000")});



        if(agente.getBackgroundGradientColors() != null ){
            gd = new GradientDrawable(
                    GradientDrawable.Orientation.TL_BR,
                    new int[] {Color.parseColor("#" + agente.getBackgroundGradientColors()[0].substring(0,6))
                            , Color.parseColor("#" + agente.getBackgroundGradientColors()[1].substring(0,6))});
        }
        else if (agente.getDisplayName().equals("Neon")){
            gd = new GradientDrawable(
                    GradientDrawable.Orientation.TL_BR,
                    new int[] {Color.parseColor("#bd8c2c"),
                            Color.parseColor("#203469")});

        }
        else if (agente.getDisplayName().equals("Fade")){
            gd = new GradientDrawable(
                    GradientDrawable.Orientation.TL_BR,
                    new int[] {Color.parseColor("#695857"),
                            Color.parseColor("#311c29")});
        }

        gd.setCornerRadius(0f);
        fondoAgente.setBackground(gd);



        imagen = (ShapeableImageView) findViewById(R.id.idImagenAgenteInfo);
        String url = String.valueOf(agente.getDisplayIcon());
        Picasso.get().load(url).into(imagen);

        descripcion = (TextView) findViewById(R.id.tvDescription);
        descripcion.setText(agente.getDescription());

        listaHabilidades = agente.getAbilities();

        iniciaRecyclerView();

    }

    //Esta funcion sirve para poder cargar cada una de las habilidades dentro del recyclerview de habilidades, mediante un adapter al cual le pasamos
    //la lista de habilidades del agente seleccionado.
    public void iniciaRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        habilidadReciclerView.setLayoutManager(linearLayoutManager);
        habilidadesAdapter = new HabilidadesAdapter(listaHabilidades);
        habilidadReciclerView.setAdapter(habilidadesAdapter);

    }

    //Funcion para que cuando entres al personaje suene su voz
    private void playAudio(String url) throws IOException {

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        mediaPlayer.setDataSource(url);
        mediaPlayer.prepare();
        mediaPlayer.start();
    }
}
