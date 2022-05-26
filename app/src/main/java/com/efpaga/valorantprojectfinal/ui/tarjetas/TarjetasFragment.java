package com.efpaga.valorantprojectfinal.ui.tarjetas;

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
import com.efpaga.valorantprojectfinal.room.Tarjeta;
import com.efpaga.valorantprojectfinal.threads.HiloInsertarTarjeta;
import com.efpaga.valorantprojectfinal.valorantapi.models.Card;
import com.efpaga.valorantprojectfinal.valorantapi.models.Theme;

import java.util.ArrayList;
import java.util.Locale;


//Esta clase sirve para mostrar en el fragment las tarjetas cargadas del json de la api.
//La peculiaridad es que en esta podremos guardar las tarjetas dentro de la base de datos si hacemos onclick en  el boton derecho del cardview.
public class TarjetasFragment extends Fragment implements TarjetasAdapter.OnTarjetaListener{

    private Card[] listaTarjetas;
    private Card[] listaTarjetasAuxiliar;
    private AppDataBase db;
    private View rootView;
    private TarjetasAdapter adapter;
    private RecyclerView recycler;
    private EditText buscador;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_tarjetas, container, false);
        listaTarjetas = MainActivity.hiloCargarInformacion.getTarjetas();
        listaTarjetasAuxiliar = MainActivity.hiloCargarInformacion.getTarjetas();
        //Buscador
        buscador = rootView.findViewById(R.id.buscadorTarjetas);

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
                listaTarjetasAuxiliar = filter(editable.toString());
            }
        });


        db = Room.databaseBuilder(getContext(),
                AppDataBase.class, "Usuario").build();
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new TarjetasAdapter(listaTarjetasAuxiliar, this, db);
        recycler = (RecyclerView) rootView.findViewById(R.id.recyclerTarjetas);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler.setHasFixedSize(true);
        recycler.setAdapter(adapter);

    }

    //En el momento que hacemos onclick creamos un objeto tarjeta con las caracteristicas de la tarjeta a la que corresponde y con un hilo
    //insertaremos dicha tarjeta en la base de datos.
    @Override
    public void OnTarjetaGuarda(int position) throws InterruptedException {

        Tarjeta tarjeta = new Tarjeta(listaTarjetasAuxiliar[position].getUuid(), listaTarjetasAuxiliar[position].getDisplayName(),
                listaTarjetasAuxiliar[position].getDisplayIcon(), listaTarjetasAuxiliar[position].getWideArt(),
                listaTarjetasAuxiliar[position].getLargeArt());


        HiloInsertarTarjeta insertar = new HiloInsertarTarjeta(db, tarjeta);

        insertar.start();
    }


    private Card[] filter(String text){
        ArrayList<Card> filteredCardList = new ArrayList<>();

        for(Card card : listaTarjetas){
            if(card.getDisplayName().toLowerCase().contains(text.toLowerCase())){
                filteredCardList.add(card);
            }
        }
        adapter.filterList(filteredCardList);
        return filteredCardList.toArray(new Card[filteredCardList.size()]);
    }



}
