package com.efpaga.valorantprojectfinal.ui.themes;

import android.content.Intent;
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

import com.efpaga.valorantprojectfinal.MainActivity;
import com.efpaga.valorantprojectfinal.R;
import com.efpaga.valorantprojectfinal.ui.agentes.AgenteActivity;
import com.efpaga.valorantprojectfinal.valorantapi.models.Card;
import com.efpaga.valorantprojectfinal.valorantapi.models.Theme;
import com.efpaga.valorantprojectfinal.valorantapi.models.Weapon;
import com.efpaga.valorantprojectfinal.valorantapi.models.WeaponSkin;

import java.util.ArrayList;
import java.util.List;

//Realizamos lo mismo que con los agentes y las armas, cargamos la lista con el hilo de la classe main. Adaptaremos en el recyclerview los elementos con el
//adapter y cuando se haga onclick en un cardview pues cargaremos una activity.
public class ThemesFragment extends Fragment implements ThemesAdapter.OnThemeListener{

    private Theme[] listaThemesValida;
    private Theme[] listaThemesValidaAuxiliar;
    private EditText buscador;
    private ThemesAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_themes, container, false);
        listaThemesValida = MainActivity.hiloCargarInformacion.getThemes();
        listaThemesValidaAuxiliar = MainActivity.hiloCargarInformacion.getThemes();
        //Buscador
        buscador = rootView.findViewById(R.id.buscadorThemes);

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
                listaThemesValidaAuxiliar = filter(editable.toString());
            }
        });
        return rootView;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new ThemesAdapter(listaThemesValidaAuxiliar, this);

        final RecyclerView recycler = (RecyclerView) view.findViewById(R.id.recyclerBundles);
        recycler.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recycler.setHasFixedSize(true);
        recycler.setAdapter(adapter);
    }

    @Override
    public void onThemeClick(int position) {
        Intent intent = new Intent(this.getActivity(), SkinsActivity.class);
        intent.putExtra("theme", listaThemesValidaAuxiliar[position]);
        startActivity(intent);
    }


    private Theme[] filter(String text){
        ArrayList<Theme> filteredThemeList = new ArrayList<>();

        for(Theme theme : listaThemesValida){
            if(theme.getDisplayName().toLowerCase().contains(text.toLowerCase())){
                filteredThemeList.add(theme);
            }
        }

        adapter.filterList(filteredThemeList);

        return filteredThemeList.toArray(new Theme[filteredThemeList.size()]);
    }
}