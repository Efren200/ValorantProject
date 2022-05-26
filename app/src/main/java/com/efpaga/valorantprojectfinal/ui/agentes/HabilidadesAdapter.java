package com.efpaga.valorantprojectfinal.ui.agentes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.efpaga.valorantprojectfinal.R;
import com.efpaga.valorantprojectfinal.valorantapi.models.AgentAbility;
import com.squareup.picasso.Picasso;

//Adapter para poder asignar al cardview las caracteristicas de cada una de las cuatro habilidades del agente.
public class HabilidadesAdapter extends RecyclerView.Adapter<HabilidadesAdapter.ViewHolder> {

    private AgentAbility[] listaHabilidades;

    public HabilidadesAdapter(AgentAbility[] listaHabilidades){
        this.listaHabilidades = listaHabilidades;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_habilidad, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String url = String.valueOf(listaHabilidades[position].getDisplayIcon());
        Picasso.get().load(url).into(holder.imgHabilidad);

        holder.nombreHabilidad.setText(listaHabilidades[position].getDisplayName());
        holder.descripcion.setText(listaHabilidades[position].getDescription());
    }

    @Override
    public int getItemCount() {
        return listaHabilidades.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgHabilidad;
        TextView nombreHabilidad, descripcion;

        public ViewHolder(@NonNull View itemView){

            super(itemView);
            imgHabilidad = itemView.findViewById(R.id.idImagenHabilidad);
            nombreHabilidad = itemView.findViewById(R.id.lblNombreHabilidad);
            descripcion = itemView.findViewById(R.id.lblDescripcionHabilidad);
        }

    }
}
