package com.efpaga.valorantprojectfinal.ui.titulos;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.efpaga.valorantprojectfinal.MainActivity;
import com.efpaga.valorantprojectfinal.R;
import com.efpaga.valorantprojectfinal.room.AppDataBase;
import com.efpaga.valorantprojectfinal.room.Titulo;
import com.efpaga.valorantprojectfinal.threads.HIloRecuperaTarjetaTitulo;
import com.efpaga.valorantprojectfinal.threads.HiloInsertarTitulo;
import com.efpaga.valorantprojectfinal.valorantapi.models.Card;
import com.efpaga.valorantprojectfinal.valorantapi.models.Title;

import java.util.ArrayList;
import java.util.List;

//En esta clase hacemos lo mismo que en las tarjetas pero ahora con los titulos.
public class TitulosFragment extends Fragment implements TitulosAdapter.OnTituloListener{

    private Title[] listaTitulos;
    private Title[] listaAuxiliar;
    private AppDataBase db;
    private View rootView;
    private TitulosAdapter adapter;
    private RecyclerView recycler;
    private EditText buscador;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_titulos, container, false);

        listaTitulos = MainActivity.hiloCargarInformacion.getTitulos();
        listaAuxiliar = MainActivity.hiloCargarInformacion.getTitulos();

        //Buscador
        buscador = rootView.findViewById(R.id.buscadorTitulos);

        buscador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscador.setText("");
            }
        });
        buscador.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                listaAuxiliar = filter(editable.toString());
            }
        });


        db = Room.databaseBuilder(getContext(),
                AppDataBase.class, "Usuario").build();

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new TitulosAdapter(listaAuxiliar, this, db);

        recycler = (RecyclerView) rootView.findViewById(R.id.recyclerTitulos);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler.setHasFixedSize(true);
        recycler.setAdapter(adapter);

    }

    @Override
    public void OnTituloGuarda(int position) throws InterruptedException {


        Titulo titulo = new Titulo(listaAuxiliar[position].getUuid(), listaAuxiliar[position].getDisplayName()
                , listaAuxiliar[position].getTitleText());

        HiloInsertarTitulo insertar = new HiloInsertarTitulo(db, titulo);

        insertar.start();
    }

    private Title[] filter(String text){
        ArrayList<Title> filteredTitleList = new ArrayList<>();

        for(Title title : listaTitulos){
            if(title.getDisplayName().toLowerCase().contains(text.toLowerCase())){
                filteredTitleList.add(title);
            }
        }

        adapter.filterList(filteredTitleList);

        return filteredTitleList.toArray(new Title[filteredTitleList.size()]);
    }
}
