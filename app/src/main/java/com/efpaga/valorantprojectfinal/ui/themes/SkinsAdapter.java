package com.efpaga.valorantprojectfinal.ui.themes;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.efpaga.valorantprojectfinal.R;
import com.efpaga.valorantprojectfinal.valorantapi.models.WeaponSkin;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

//Adapter para poder asignar al cardview los datos corresponientes a la skin
public class SkinsAdapter  extends RecyclerView.Adapter<SkinsAdapter.ViewHolderDatos> implements View.OnClickListener{

    private ArrayList<WeaponSkin> listaSkins;
    private Context context;
    private RecyclerView recyclerView;

    public SkinsAdapter(ArrayList<WeaponSkin> listaSkins){
        this.listaSkins = listaSkins;
    }


    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();

        recyclerView = parent.findViewById(R.id.recyclerSkins);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_skins, null, false);

        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);

        view.setOnClickListener(this);

        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.asignarDatosSkin(listaSkins.get(position));
    }

    @Override
    public int getItemCount() {

        return listaSkins.size();
    }


    @Override
    public void onClick(View view) {


        int posicion = recyclerView.getChildAdapterPosition(view);
        Toast.makeText(context, listaSkins.get(posicion).getDisplayName(), Toast.LENGTH_LONG).show();
        FragmentActivity activity = (FragmentActivity) (context);
        FragmentManager fm = activity.getSupportFragmentManager();
        ChromasDialog chromasDialog = new ChromasDialog(listaSkins.get(posicion));
        chromasDialog.show(fm, "tagPersonalizado");
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        CardView cv_arma;
        TextView nombreSkin;
        ImageView imagenSkin;
        ImageView imagenplay;
        String urlVideo;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);

            cv_arma = itemView.findViewById(R.id.cvArmas);
            nombreSkin = itemView.findViewById(R.id.lblNombreSkin);
            imagenSkin = itemView.findViewById(R.id.idImagenSkin);
            imagenplay = itemView.findViewById(R.id.idBotonVideo);

        }

        //Esta funcion es para asignar a los elementos recuperados los datos de la skin.
        public void asignarDatosSkin(WeaponSkin skin){

            if(skin.getDisplayIcon() != null){
                String url = String.valueOf(skin.getDisplayIcon());
                Picasso.get().load(url).into(imagenSkin);
            }
            else{
                String url = String.valueOf(skin.getChromas()[0].getDisplayIcon());
                Picasso.get().load(url).into(imagenSkin);
            }


            nombreSkin.setText(skin.getDisplayName());

            if(skin.getLevels()[skin.getLevels().length-1].getStreamedVideo() != null){
                urlVideo = skin.getLevels()[skin.getLevels().length-1].getStreamedVideo();
                imagenplay.setVisibility(View.VISIBLE);
                imagenplay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, VideoActivity.class);
                        intent.putExtra("url", urlVideo);
                        context.startActivity(intent);
                    }
                });
                System.out.println(urlVideo);
            }

        }

    }
}
