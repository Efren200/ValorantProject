package com.efpaga.valorantprojectfinal.ui.usuario;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.efpaga.valorantprojectfinal.R;
import com.efpaga.valorantprojectfinal.room.AppDataBase;
import com.efpaga.valorantprojectfinal.room.Tarjeta;
import com.efpaga.valorantprojectfinal.threads.HiloBorraTarjeta;
import com.efpaga.valorantprojectfinal.threads.HiloInsertarTarjeta;

import java.util.List;

public class TarjetasFragmentDB extends Fragment implements  TarjetasAdapterDB.OnTarjetaBorraListener {

    private RecyclerView recyclerView;
    private TarjetasAdapterDB tarjetasAdapterDB;
    private List<Tarjeta> listaTarjetas;
    private ObjetosUsuarioListener listener;

    private AppDataBase db;

    public TarjetasFragmentDB(List<Tarjeta> listaTarjetas, ObjetosUsuarioListener listener){
        this.listaTarjetas = listaTarjetas;
        this.listener = listener;
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        db = Room.databaseBuilder(getContext(),
                AppDataBase.class, "Usuario").build();
        View view = inflater.inflate(R.layout.fragment_objetos_usuario, container, false);

        if(listaTarjetas.size() > 0){

            TextView avisoNoTitulos = (TextView) view.findViewById(R.id.noObjetosGuardados);
            avisoNoTitulos.setVisibility(View.GONE);

            recyclerView = view.findViewById(R.id.recyclerObjetos);
            recyclerView.setHasFixedSize(true);

            LinearLayoutManager layoutManager = new LinearLayoutManager((getContext()));
            recyclerView.setLayoutManager(layoutManager);

            tarjetasAdapterDB = new TarjetasAdapterDB(listaTarjetas, this);

            tarjetasAdapterDB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int posicion = recyclerView.getChildAdapterPosition(view);
                    if(listener != null){

                        listener.onTarjetaSeleccionada(listaTarjetas.get(posicion));

                    }
                }
            });

            recyclerView.setAdapter(tarjetasAdapterDB);

        }
        else{
            TextView avisoNoTitulos = (TextView) view.findViewById(R.id.noObjetosGuardados);
            avisoNoTitulos.setVisibility(View.VISIBLE);
        }
        return view;
    }


    @Override
    public void OnTarjetaBorra(int position) throws InterruptedException {

        Tarjeta tarjetaBorra = listaTarjetas.get(position);

        HiloBorraTarjeta borrar = new HiloBorraTarjeta(db, tarjetaBorra);
        borrar.start();

        listaTarjetas.remove(position);
        recyclerView.removeViewAt(position);
        tarjetasAdapterDB.notifyItemRemoved(position);
        tarjetasAdapterDB.notifyItemRangeChanged(position, listaTarjetas.size());

    }
}
