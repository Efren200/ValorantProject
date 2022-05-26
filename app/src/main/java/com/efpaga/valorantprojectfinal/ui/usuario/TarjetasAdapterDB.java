package com.efpaga.valorantprojectfinal.ui.usuario;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.efpaga.valorantprojectfinal.R;
import com.efpaga.valorantprojectfinal.room.Tarjeta;
import com.efpaga.valorantprojectfinal.ui.tarjetas.TarjetasAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TarjetasAdapterDB extends RecyclerView.Adapter<TarjetasAdapterDB.ViewHolderDatos> implements View.OnClickListener {

    private List<Tarjeta> listaTarjetas;
    private Context context;
    private View.OnClickListener listener;
    private OnTarjetaBorraListener onTarjetaBorraListener;

    public TarjetasAdapterDB(List<Tarjeta> listaTarjetas, OnTarjetaBorraListener onTarjetaBorraListener){
        this.listaTarjetas = listaTarjetas;
        this.onTarjetaBorraListener = onTarjetaBorraListener;

    }
    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tarjeta_database, null, false);

        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);

        view.setOnClickListener(this);

        return new ViewHolderDatos(view, onTarjetaBorraListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {

        holder.asignarDatosTarjetaDB(listaTarjetas.get(position));
    }



    @Override
    public int getItemCount() {
        return listaTarjetas.size();
    }

    @Override
    public void onClick(View view) {
        if(listener != null){
            listener.onClick(view);
        }
    }


    public class ViewHolderDatos extends RecyclerView.ViewHolder  implements View.OnClickListener{

        CardView cvTarjeta;
        TextView nombreTarjeta;
        ImageView imagenTarjeta;
        ImageButton botonElimina;
        OnTarjetaBorraListener onTarjetaBorraListener;

        public ViewHolderDatos(@NonNull View itemView, OnTarjetaBorraListener onTarjetaBorraListener){
            super(itemView);

            cvTarjeta = itemView.findViewById(R.id.cvTarjetaDataBase);
            nombreTarjeta = itemView.findViewById(R.id.lblNombreTarjetaDatabase);
            imagenTarjeta = itemView.findViewById(R.id.idImagenTarjetaDatabase);
            botonElimina = itemView.findViewById(R.id.botoneliminaTarjeta);
            this.onTarjetaBorraListener = onTarjetaBorraListener;

            botonElimina.setOnClickListener(this);


        }

        public void asignarDatosTarjetaDB(Tarjeta tarjeta){
            String url = String.valueOf(tarjeta.displayIcon);
            Picasso.get().load(url).into(imagenTarjeta);
            nombreTarjeta.setText(tarjeta.name);
        }

        @Override
        public void onClick(View view) {

            try {
                onTarjetaBorraListener.OnTarjetaBorra(getAdapterPosition());
                Toast.makeText(view.getContext(), nombreTarjeta.getText() + " eliminada de la base de datos", Toast.LENGTH_LONG).show();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public interface OnTarjetaBorraListener{

        void OnTarjetaBorra(int position) throws InterruptedException;
    }
}
