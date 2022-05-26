package com.efpaga.valorantprojectfinal.ui.armas;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.efpaga.valorantprojectfinal.MainActivity;
import com.efpaga.valorantprojectfinal.R;
import com.efpaga.valorantprojectfinal.valorantapi.models.Weapon;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class StatsActivity extends AppCompatActivity {

    private Weapon arma;
    private String idArma;
    private TextView categoriaArma, balasArma, precioArma, recargaArma;
    private ImageView imgArma;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        Objects.requireNonNull(getSupportActionBar()).hide();

        idArma = (String) getIntent().getStringExtra("idArma");
        for (Weapon weapon : MainActivity.hiloCargarInformacion.getWeapons()) {
            if (weapon.getUuid().equals(idArma)) {
                arma = weapon;
            }
        }

        imgArma = (ImageView) findViewById(R.id.imgStatsArma);
        String urlImg = String.valueOf(arma.getShopData().getNewImage());
        Picasso.get().load(urlImg).into(imgArma);

        categoriaArma = (TextView) findViewById(R.id.lblStatCategoria);
        String ctg = "Categoria: " + arma.getShopData().getCategoryText();
        categoriaArma.setText(ctg);

        balasArma = (TextView) findViewById(R.id.lblStatBalas);
        String balas = "Cargador: " + arma.getWeaponStats().getMagazineSize();
        balasArma.setText(balas);

        precioArma = (TextView) findViewById(R.id.lblStatPrecio);
        String precio = "Precio: " + arma.getShopData().getCost();
        precioArma.setText(precio);

        recargaArma = (TextView) findViewById(R.id.lblStatRecarga);
        String recarga = "Tiempo de recarga: " + arma.getWeaponStats().getReloadTimeSeconds() + " segundos";
        recargaArma.setText(recarga);

        TextView[] tvCabeza = new TextView[]{(TextView) findViewById(R.id.lblCabDist1), (TextView) findViewById(R.id.lblCabDist2),
                findViewById(R.id.lblCabDist3), (TextView) findViewById(R.id.lblCabDmg1),
                findViewById(R.id.lblCabDmg2), (TextView) findViewById(R.id.lblCabDmg3)};

        TextView[] tvCuerpo = new TextView[]{(TextView) findViewById(R.id.lblCuerpoDist1), (TextView) findViewById(R.id.lblCuerpoDist2),
                (TextView) findViewById(R.id.lblCuerpoDist3), (TextView) findViewById(R.id.lblCuerpoDmg1),
                (TextView) findViewById(R.id.lblCuerpoDmg2), (TextView) findViewById(R.id.lblCuerpoDmg3)};

        TextView[] tvPierna = new TextView[]{(TextView) findViewById(R.id.lblPiernaDist1), (TextView) findViewById(R.id.lblPiernaDist2),
                (TextView) findViewById(R.id.lblPiernaDist3), (TextView) findViewById(R.id.lblPiernaDmg1),
                (TextView) findViewById(R.id.lblPiernaDmg2), (TextView) findViewById(R.id.lblPiernaDmg3)};

        for(int i = 0; i < arma.getWeaponStats().getDamageRanges().length; i++){
            String distancia = Math.round(arma.getWeaponStats().getDamageRanges()[i].getRangeStartMeters()) +
                    "-" + Math.round(arma.getWeaponStats().getDamageRanges()[i].getRangeEndMeters());
            tvCabeza[i].setText(distancia);
            tvCuerpo[i].setText(distancia);
            tvPierna[i].setText(distancia);

            String dmgCabeza = "" + Math.round(arma.getWeaponStats().getDamageRanges()[i].getHeadDamage());
            String dmgCuerpo = "" + Math.round(arma.getWeaponStats().getDamageRanges()[i].getBodyDamage());
            String dmgPierna = "" + Math.round(arma.getWeaponStats().getDamageRanges()[i].getLegDamage());
            tvCabeza[i+3].setText(dmgCabeza);
            tvCuerpo[i+3].setText(dmgCuerpo);
            tvPierna[i+3].setText(dmgPierna);
        }

    }


}
