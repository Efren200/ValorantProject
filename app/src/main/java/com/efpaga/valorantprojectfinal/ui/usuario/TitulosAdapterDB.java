package com.efpaga.valorantprojectfinal.ui.usuario;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.efpaga.valorantprojectfinal.R;
import com.efpaga.valorantprojectfinal.room.Titulo;

import java.util.List;

public class TitulosAdapterDB extends RecyclerView.Adapter<TitulosAdapterDB.ViewHolderDatos> implements View.OnClickListener{

    private List<Titulo> listaTitulos;
    private Context context;

    private View.OnClickListener listener;
    private OnTituloBorraListener onTarjetaBorraListener;

    public TitulosAdapterDB(List<Titulo> listaTitulos, OnTituloBorraListener onTarjetaBorraListener){
        this.listaTitulos = listaTitulos;
        this.onTarjetaBorraListener = onTarjetaBorraListener;
    }

    @Override
    public void onClick(View view) {
        if(listener != null){
            listener.onClick(view);
        }
    }
    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_titulo_database, null, false);

        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);

        view.setOnClickListener(this);

        return new ViewHolderDatos(view, onTarjetaBorraListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {

        holder.asignarDatosTituloDB(listaTitulos.get(position));
    }

    @Override
    public int getItemCount() {
        return listaTitulos.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder  implements View.OnClickListener{

        CardView cv_titulo;
        TextView nombreTitulo;
        ImageButton botonElimina;
        OnTituloBorraListener onTarjetaBorraListener;

        public ViewHolderDatos(@NonNull View itemView, OnTituloBorraListener onTarjetaBorraListener){
            super(itemView);

            cv_titulo = itemView.findViewById(R.id.cvTituloDataBase);
            nombreTitulo = itemView.findViewById(R.id.lblNombreTituloDatabase);
            botonElimina = itemView.findViewById(R.id.botonEliminaTitulo);
            this.onTarjetaBorraListener = onTarjetaBorraListener;

            botonElimina.setOnClickListener(this);


        }

        public void asignarDatosTituloDB(Titulo titulo){


            nombreTitulo.setText(titulo.name);
        }

        @Override
        public void onClick(View view) {

            try {
                onTarjetaBorraListener.OnTituloBorra(getAdapterPosition());
                Toast.makeText(view.getContext(), nombreTitulo.getText() + " eliminado de la base de datos", Toast.LENGTH_LONG).show();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public interface OnTituloBorraListener {

        void OnTituloBorra(int position) throws InterruptedException;
    }


}
