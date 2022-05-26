package com.efpaga.valorantprojectfinal.ui.tarjetas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.efpaga.valorantprojectfinal.R;
import com.efpaga.valorantprojectfinal.room.AppDataBase;
import com.efpaga.valorantprojectfinal.room.Tarjeta;
import com.efpaga.valorantprojectfinal.threads.HIloRecuperaTarjetaTitulo;
import com.efpaga.valorantprojectfinal.valorantapi.models.Card;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TarjetasAdapter extends RecyclerView.Adapter<TarjetasAdapter.ViewHolder> {

    private Card[] listaTarjetas;
    private OnTarjetaListener onTarjetaListener;
    private AppDataBase db;


    public TarjetasAdapter(Card[] listaTarjetas, OnTarjetaListener onTarjetaListener, AppDataBase db){
        this.listaTarjetas = listaTarjetas;
        this.onTarjetaListener = onTarjetaListener;
        this.db = db;
    }


    public void setListaTarjetas(Card[] listaTarjetas) {
        this.listaTarjetas = listaTarjetas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_tarjetas, parent, false);

        return new ViewHolder(view, onTarjetaListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String url = String.valueOf(listaTarjetas[position].getDisplayIcon());
        Picasso.get().load(url).into(holder.imgTarjeta);

        holder.nombreTarjeta.setText(listaTarjetas[position].getDisplayName());

        List<Tarjeta> listaTarjetasDB = new ArrayList<>();
        HIloRecuperaTarjetaTitulo hilo = new HIloRecuperaTarjetaTitulo(db);
        hilo.start();

        try {
            hilo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        listaTarjetasDB = hilo.getTarjetas();

        for(Tarjeta tarjeta : listaTarjetasDB){
            if(listaTarjetas[position].getDisplayName().equals(tarjeta.name)){
                holder.botonGuarda.setVisibility(View.INVISIBLE);
            }
        }

    }

    @Override
    public int getItemCount() {
        return listaTarjetas.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imgTarjeta;
        TextView nombreTarjeta;
        ImageButton botonGuarda;
        OnTarjetaListener onTarjetaListener;


        public ViewHolder(@NonNull View itemView, OnTarjetaListener onTarjetaListener){

            super(itemView);
            imgTarjeta = itemView.findViewById(R.id.idImagenTarjeta);
            nombreTarjeta = itemView.findViewById(R.id.lblNombreTarjeta);
            botonGuarda = itemView.findViewById((R.id.botonguardaTarjeta));
            this.onTarjetaListener = onTarjetaListener;

            botonGuarda.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            try {
                onTarjetaListener.OnTarjetaGuarda(getAdapterPosition());
                botonGuarda.setVisibility(View.INVISIBLE);
                Toast.makeText(view.getContext(), nombreTarjeta.getText() + " guardada en la base de datos", Toast.LENGTH_LONG).show();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public interface OnTarjetaListener{

        void OnTarjetaGuarda(int position) throws InterruptedException;
    }

    public Card[] filterList(ArrayList<Card> filteredList){

        listaTarjetas = filteredList.toArray(new Card[filteredList.size()]);
        notifyDataSetChanged();

        return listaTarjetas;
    }
}
