package com.efpaga.valorantprojectfinal.ui.usuario;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.efpaga.valorantprojectfinal.R;
import com.efpaga.valorantprojectfinal.room.AppDataBase;
import com.efpaga.valorantprojectfinal.room.Titulo;
import com.efpaga.valorantprojectfinal.threads.HiloBorraTitulo;

import java.util.List;

public class TitulosFragmentDB extends Fragment implements TitulosAdapterDB.OnTituloBorraListener {

    private RecyclerView recyclerView;
    private TitulosAdapterDB titulosAdapterDB;
    private List<Titulo> listaTitulos;
    private ObjetosUsuarioListener listener;
    private AppDataBase db;

    public TitulosFragmentDB(List<Titulo> listaTitulos, ObjetosUsuarioListener listener){
        this.listaTitulos = listaTitulos;
        this.listener = listener;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        db = Room.databaseBuilder(getContext(),
                AppDataBase.class, "Usuario").build();
        View view = inflater.inflate(R.layout.fragment_objetos_usuario, container, false);

        if(listaTitulos.size() > 0){

            TextView avisoNoTitulos = (TextView) view.findViewById(R.id.noObjetosGuardados);
            avisoNoTitulos.setVisibility(View.GONE);

            recyclerView = view.findViewById(R.id.recyclerObjetos);
            recyclerView.setHasFixedSize(true);

            LinearLayoutManager layoutManager = new LinearLayoutManager((getContext()));
            recyclerView.setLayoutManager(layoutManager);

            titulosAdapterDB = new TitulosAdapterDB(listaTitulos, this);

            titulosAdapterDB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int posicion = recyclerView.getChildAdapterPosition(view);
                    if(listener != null){

                        listener.onTituloDBSeleccionado(listaTitulos.get(posicion));

                    }
                }
            });

            recyclerView.setAdapter(titulosAdapterDB);

        }
        else{
            TextView avisoNoTitulos = (TextView) view.findViewById(R.id.noObjetosGuardados);
            avisoNoTitulos.setVisibility(View.VISIBLE);
        }
        return view;
    }

    @Override
    public void OnTituloBorra(int position) throws InterruptedException {

        Titulo tituloBorra = listaTitulos.get(position);

        HiloBorraTitulo borrar = new HiloBorraTitulo(db, tituloBorra);
        borrar.start();

        listaTitulos.remove(position);
        recyclerView.removeViewAt(position);
        titulosAdapterDB.notifyItemRemoved(position);
        titulosAdapterDB.notifyItemRangeChanged(position, listaTitulos.size());


    }
}
