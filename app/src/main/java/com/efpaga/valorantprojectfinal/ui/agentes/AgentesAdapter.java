package com.efpaga.valorantprojectfinal.ui.agentes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.efpaga.valorantprojectfinal.R;
import com.efpaga.valorantprojectfinal.valorantapi.models.Agent;
import com.squareup.picasso.Picasso;

//Classe encargada de adaptarlos elementos de la lista dentro del recycler view
public class AgentesAdapter extends RecyclerView.Adapter<AgentesAdapter.ViewHolder> {

    private Agent[] listaAgentes;
    private OnAgenteListener onAgenteListener;

    public AgentesAdapter(Agent[] listaAgentes, OnAgenteListener onAgenteListener){
        this.listaAgentes = listaAgentes;
        this.onAgenteListener = onAgenteListener;

    }


    //Recogemos el cardview que corresponde a un elemento de la lista y tambien le pasamos el listener como parametro para poder utilizar la funcion.
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_agentes, parent, false);

        return new ViewHolder(view, onAgenteListener);
    }

    //En el onbindViewHolder asignamos las caracteristicas de cada uno de los elementos a los elementos del cardview.
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String url = String.valueOf(listaAgentes[position].getDisplayIcon());
        Picasso.get().load(url).into(holder.imgAgente);

        holder.nombreAgente.setText(listaAgentes[position].getDisplayName());
    }

    @Override
    public int getItemCount() {
        return listaAgentes.length;
    }

    //Classe donde podremos obtener cada uno de los elementos del cardview y poder agenciar un listener a cada uno de los elementos.
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imgAgente;
        TextView nombreAgente;
        OnAgenteListener onAgenteListener;

        public ViewHolder(@NonNull View itemView, OnAgenteListener onAgenteListener){

            super(itemView);
            imgAgente = itemView.findViewById(R.id.idImagenAgente);
            nombreAgente = itemView.findViewById(R.id.lblNombreAgente);
            this.onAgenteListener = onAgenteListener;

            itemView.setOnClickListener(this);
        }

        //El metodo onclick de la clase on clicklistener utilizara la funcion creada en el listener pasandole la posicion del agente seleccionados
        @Override
        public void onClick(View view) {
            onAgenteListener.onAgenteClick(getAdapterPosition());
        }
    }

    //Interface para crear la funcion onCLick dond e le pasaremos como parametro la posicion del elemento.
    public interface OnAgenteListener{

        void onAgenteClick(int position);

    }
}
