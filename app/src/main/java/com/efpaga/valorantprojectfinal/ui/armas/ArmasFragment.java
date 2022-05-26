package com.efpaga.valorantprojectfinal.ui.armas;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.efpaga.valorantprojectfinal.MainActivity;
import com.efpaga.valorantprojectfinal.R;
import com.efpaga.valorantprojectfinal.databinding.FragmentArmasBinding;
import com.efpaga.valorantprojectfinal.valorantapi.models.Weapon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


//Realizamos el mismo formato que con los agentes adaptando al recyclerview de la vista el adapter, situando cada una de las armas dentro de
//cada card view correspondiente. En el momento que hagamos onclick en una arma, cargaremos la activity de las skins de la arma.
public class ArmasFragment extends Fragment implements ArmasAdapter.OnArmaListener{

    private FragmentArmasBinding binding;
    private Weapon[] listaArmas;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_armas, container, false);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Rellenamos la lista de armas a partir de los datos cargados en el hilo ejecutado dentro del hilo principal.
        listaArmas = MainActivity.hiloCargarInformacion.getWeapons();
        listaArmas = removeElementUsingCollection(listaArmas, listaArmas.length-1);

        final ArmasAdapter adapter = new ArmasAdapter(listaArmas, this);

        final RecyclerView recycler = (RecyclerView) view.findViewById(R.id.recyclerArmas);
        recycler.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recycler.setHasFixedSize(true);
        recycler.setAdapter(adapter);
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onArmaClick(int position) {
        Intent intent = new Intent(this.getActivity(), StatsActivity.class);
        intent.putExtra("idArma", listaArmas[position].getUuid());
        startActivity(intent);
    }

    public Weapon[] removeElementUsingCollection( Weapon[] arr, int index ){
        List<Weapon> tempList = new ArrayList<Weapon>(Arrays.asList(arr));
        tempList.remove(index);
        return tempList.toArray(new Weapon[0]);
    }


}