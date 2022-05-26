package com.efpaga.valorantprojectfinal.ui.themes;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.efpaga.valorantprojectfinal.MainActivity;
import com.efpaga.valorantprojectfinal.R;
import com.efpaga.valorantprojectfinal.ui.agentes.HabilidadesAdapter;
import com.efpaga.valorantprojectfinal.valorantapi.models.ContentTier;
import com.efpaga.valorantprojectfinal.valorantapi.models.Theme;
import com.efpaga.valorantprojectfinal.valorantapi.models.Weapon;
import com.efpaga.valorantprojectfinal.valorantapi.models.WeaponSkin;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

//Esta clase es donde realizaremos la gestion de la skins, gestionadas mediante un bottom navigation view para clasificarlas segun el numero de chromas que tiene la skin.
public class SkinsActivity extends AppCompatActivity{

    private Theme theme;
    private ArrayList<WeaponSkin> skinList = new ArrayList<>();
    private ConstraintLayout fondoSkin;
    private ContentTier contentTierSkin;
    private RecyclerView recyclerView;
    private SkinsAdapter skinsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skins);
        Objects.requireNonNull(getSupportActionBar()).hide();

        //Recogemos el id del arma que ha sido seleccionada, y luego la buscamos dentro de la lista donde estan todas.
        theme = (Theme) getIntent().getSerializableExtra("theme");
        recyclerView = findViewById(R.id.recyclerSkins);

        for (Weapon weapon: MainActivity.hiloCargarInformacion.getWeapons()){
            for(WeaponSkin skin : weapon.getSkins()){
                if(skin.getThemeUuid().equals(theme.getUuid())){
                    skinList.add(skin);
                }
            }
        }

        for( ContentTier contentTier: MainActivity.hiloCargarInformacion.getListContentTiers()){
            if(contentTier.getUuid().equals(skinList.get(0).getContentTierUuid())){
                contentTierSkin = contentTier;
            }
        }
        fondoSkin = (ConstraintLayout) findViewById(R.id.fondoSkin);
        GradientDrawable gd = new GradientDrawable();
        if(contentTierSkin != null){
            gd = new GradientDrawable(
                    GradientDrawable.Orientation.TL_BR,
                    new int[] {Color.parseColor("#" + contentTierSkin.getHighlightColor().substring(0,6))
                            , Color.parseColor("#ffffff")});
        }
        else{
            gd = new GradientDrawable(
                    GradientDrawable.Orientation.TL_BR,
                    new int[] {Color.parseColor("#ff4654")
                            , Color.parseColor("#ffffff")});
        }


        gd.setCornerRadius(0f);
        fondoSkin.setBackground(gd);

        iniciaRecyclerView();

    }
    public void iniciaRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        skinsAdapter = new SkinsAdapter(skinList);
        recyclerView.setAdapter(skinsAdapter);

    }

}


