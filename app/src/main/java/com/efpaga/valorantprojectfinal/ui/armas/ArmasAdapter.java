package com.efpaga.valorantprojectfinal.ui.armas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.efpaga.valorantprojectfinal.R;
import com.efpaga.valorantprojectfinal.valorantapi.models.Weapon;
import com.squareup.picasso.Picasso;

public class ArmasAdapter extends RecyclerView.Adapter<ArmasAdapter.ViewHolder> {

    private Weapon[] listaArmas;
    private OnArmaListener onArmaListener;

    public ArmasAdapter(Weapon[] listaArmas, OnArmaListener onArmaListener){
        this.listaArmas = listaArmas;
        this.onArmaListener = onArmaListener;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_armas, parent, false);

        return new ViewHolder(view, onArmaListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String url = String.valueOf("https://media.valorant-api.com/weapons/" + listaArmas[position].getUuid() + "/displayicon.png");
        Picasso.get().load(url).into(holder.imgArma);
        Picasso.get().load(url).into(holder.imgArmaSombra);

        holder.nombreArma.setText(listaArmas[position].getDisplayName());
    }

    @Override
    public int getItemCount() {
        return listaArmas.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imgArma;
        ImageView imgArmaSombra;
        TextView nombreArma;
        OnArmaListener onArmaListener;

        public ViewHolder(@NonNull View itemView, OnArmaListener onArmaListener){

            super(itemView);
            imgArma = itemView.findViewById(R.id.idImagenArma);
            nombreArma = itemView.findViewById(R.id.lblNombreArma);
            imgArmaSombra = itemView.findViewById(R.id.idImagenArmaSombra);
            this.onArmaListener = onArmaListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onArmaListener.onArmaClick(getAdapterPosition());
        }
    }

    public interface OnArmaListener{

        void onArmaClick(int position);

    }
}
