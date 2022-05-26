package com.efpaga.valorantprojectfinal.ui.titulos;

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
import com.efpaga.valorantprojectfinal.room.Titulo;
import com.efpaga.valorantprojectfinal.threads.HIloRecuperaTarjetaTitulo;
import com.efpaga.valorantprojectfinal.valorantapi.models.Card;
import com.efpaga.valorantprojectfinal.valorantapi.models.Title;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TitulosAdapter extends RecyclerView.Adapter<TitulosAdapter.ViewHolder> {

    private Title[] listaTitulos;
    private OnTituloListener onTituloListener;
    private AppDataBase db;


    public TitulosAdapter(Title[] listaTitulos,OnTituloListener onTituloListener, AppDataBase db){
        this.listaTitulos = listaTitulos;
        this.onTituloListener = onTituloListener;
        this.db = db;
    }


    public void setListaTitulos(Title[] listaTitulos) {
        this.listaTitulos = listaTitulos;
    }

    @NonNull
    @Override
    public TitulosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_titulos, parent, false);

        return new TitulosAdapter.ViewHolder(view, onTituloListener);
    }

    @Override
    public void onBindViewHolder(@NonNull TitulosAdapter.ViewHolder holder, int position) {

        holder.nombreTitulo.setText(listaTitulos[position].getDisplayName());

        List<Titulo> listaTitulosDB = new ArrayList<>();
        HIloRecuperaTarjetaTitulo hilo = new HIloRecuperaTarjetaTitulo(db);
        hilo.start();

        try {
            hilo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        listaTitulosDB = hilo.getTitulos();

        for(Titulo titulo : listaTitulosDB){
            if(listaTitulos[position].getDisplayName().equals(titulo.name)){
                holder.botonGuarda.setVisibility(View.INVISIBLE);
            }
        }


    }

    @Override
    public int getItemCount() {
        return listaTitulos.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView nombreTitulo;
        ImageButton botonGuarda;
        OnTituloListener onTituloListener;


        public ViewHolder(@NonNull View itemView, OnTituloListener onTituloListener){

            super(itemView);
            nombreTitulo = itemView.findViewById(R.id.lblNombreTitulo);
            botonGuarda = itemView.findViewById((R.id.botonguardaTitulo));
            this.onTituloListener = onTituloListener;

            botonGuarda.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            try {
                onTituloListener.OnTituloGuarda(getAdapterPosition());
                botonGuarda.setVisibility(View.INVISIBLE);
                Toast.makeText(view.getContext(), nombreTitulo.getText() + " guardado en la base de datos", Toast.LENGTH_LONG).show();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public interface OnTituloListener{

        void OnTituloGuarda(int position) throws InterruptedException;
    }

    public Title[] filterList(ArrayList<Title> filteredList){

        listaTitulos = filteredList.toArray(new Title[filteredList.size()]);
        notifyDataSetChanged();

        return listaTitulos;
    }
}
