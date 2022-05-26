package com.efpaga.valorantprojectfinal.ui.themes;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.efpaga.valorantprojectfinal.R;
import com.efpaga.valorantprojectfinal.valorantapi.models.WeaponSkin;
import com.squareup.picasso.Picasso;

//Esta clase sirve para adaptar cada uno de los chromas de la skin seleccionada dentro de un dialogo
public class ChromasDialog extends DialogFragment {

    WeaponSkin skin;

    public ChromasDialog(WeaponSkin skin){
        this.skin = skin;
    }

    //Recuperamos cada uno de los elementos del dialogo que como maximo puede tener 4 chromas una skin, hemos creado 4 composiciones de elementos
    //iguales, guardando los elementos dentro de tres arrays diferentes, y rellenaremos los datos dependiendo de la longitud del array de chromas
    // asi puedo reutilizar codigo, ya que para las cuatro composiciones se utilizaria el mismo codigo, asi si por ejemplo solo hubiera un chroma,
    //solo se llenara de datos el la primera composicion.
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        TextView tvNombreSkin;

        AlertDialog alertDialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_cromas, null);

        TextView[] tvChromas = new TextView[]{(TextView) view.findViewById(R.id.tvChroma1), (TextView) view.findViewById(R.id.tvChroma2),
                (TextView) view.findViewById(R.id.tvChroma3), (TextView) view.findViewById(R.id.tvChroma4)};

        ImageView[] imgChromas = new ImageView[]{(ImageView) view.findViewById(R.id.imgChroma1), (ImageView) view.findViewById(R.id.imgChroma2),
                (ImageView) view.findViewById(R.id.imgChroma3), (ImageView) view.findViewById(R.id.imgChroma4)};

        ImageView[] imgSwatchs = new ImageView[]{(ImageView) view.findViewById(R.id.imgSwatch1), (ImageView) view.findViewById(R.id.imgSwatch2),
                (ImageView) view.findViewById(R.id.imgSwatch3), (ImageView) view.findViewById(R.id.imgSwatch4)};

        tvNombreSkin = (TextView) view.findViewById(R.id.tvNombreSkin);
        tvNombreSkin.setText((skin.getDisplayName()));


        for(int i = 0; i < skin.getChromas().length; i++){

            tvChromas[i].setText(skin.getChromas()[i].getDisplayName());

            String url = String.valueOf(skin.getChromas()[i].getDisplayIcon());
            Picasso.get().load(url).into(imgChromas[i]);

            String url2 = String.valueOf(skin.getChromas()[i].getSwatch());
            Picasso.get().load(url2).into(imgSwatchs[i]);
        }

        builder.setView(view);

        alertDialog = builder.create();
        //Boton para que cuando le hagamos onclick cerremos el dialogo.
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        return builder.create();


    }

}
