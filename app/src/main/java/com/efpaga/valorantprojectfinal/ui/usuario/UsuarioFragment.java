package com.efpaga.valorantprojectfinal.ui.usuario;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

import com.efpaga.valorantprojectfinal.R;
import com.efpaga.valorantprojectfinal.room.AppDataBase;
import com.efpaga.valorantprojectfinal.room.Tarjeta;
import com.efpaga.valorantprojectfinal.room.Titulo;
import com.efpaga.valorantprojectfinal.threads.HIloRecuperaTarjetaTitulo;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.List;

//Esta classe contiene un contenedor de fragments para que con un botom navigation view podamos visualizar tanto las tarjetas como los titulos
//que hemos guardado dentro de la base de datos. Tenemos un listener que nos proporciona dos metodos los cuales sirven para poder guardar en un
//shared preferences el titulo seleccionado, la tarjeta seleccionada y el nombre de usuario local.
//Los metodos de adaptacion de los objetos en el fragment es igual que en las otras clases, lo unico nuevo es que le proporcionamos un boton a la derecha
//de cada uno de los objetos del recyclerview para que cuando le hagas onclick puedas eliminarlo de la base de datos mediante un hilo.
public class UsuarioFragment extends Fragment implements NavigationBarView.OnItemSelectedListener, ObjetosUsuarioListener {

    private EditText editaNombre;
    private ImageButton confirma;
    private AppDataBase db;
    private NavigationView navigationView;
    private BottomNavigationView bottomNavigationView;
    private List<Tarjeta> listaTarjetasBD;
    private List<Titulo> listaTitulosBD;
    public TextView tituloUsuario;
    public ImageView imagenUsuario;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_usuario, container, false);

        editaNombre = rootView.findViewById(R.id.etNombreUsuario);
        confirma = rootView.findViewById(R.id.btnGuardaNombre);
        db = Room.databaseBuilder(getContext(),
                AppDataBase.class, "Usuario").build();
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView nombreUsuario = (TextView) headerView.findViewById(R.id.tvNombreUsuario);
        tituloUsuario = (TextView) headerView.findViewById(R.id.tvTituloGuardado);
        imagenUsuario = (ImageView) headerView.findViewById(R.id.ivTarjetaGuardada);

        editaNombre.setText(nombreUsuario.getText());

        confirma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(editaNombre.getText().toString().equals("")){
                    Toast.makeText(getContext(), "No has introducido el nombre de tu cuenta", Toast.LENGTH_LONG).show();
                }
                else{

                    SharedPreferences preferences = getActivity().getSharedPreferences("usuario", Context.MODE_PRIVATE);
                    String nombre = editaNombre.getText().toString();

                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("nombreUsuario", nombre);

                    nombreUsuario.setText(nombre);
                    editor.commit();

                    Toast.makeText(getContext(), "Nombre de cuenta guardado", Toast.LENGTH_LONG).show();
                }
            }
        });
        bottomNavigationView = (BottomNavigationView) view.findViewById(R.id.menu_navigation_usuario);
        bottomNavigationView.setOnItemSelectedListener(this);

        HIloRecuperaTarjetaTitulo hilo = new HIloRecuperaTarjetaTitulo(db);
        hilo.start();

        try {
            hilo.join();
            listaTarjetasBD = hilo.getTarjetas();
            listaTitulosBD = hilo.getTitulos();

            getParentFragmentManager().beginTransaction().add(R.id.contenedorFragmentsUsuario,
                    new TarjetasFragmentDB(listaTarjetasBD, this)).commit();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment = null;

        switch (item.getItemId()){

            case R.id.navigation_tarjetas:

                fragment = new TarjetasFragmentDB(listaTarjetasBD, this);
                break;

            case R.id.navigation_titulos:

                fragment = new TitulosFragmentDB(listaTitulosBD, this);
                break;
        }

        if(fragment != null){
            getParentFragmentManager().beginTransaction().replace(R.id.contenedorFragmentsUsuario, fragment).commit();

        }

        return true;
    }

    @Override
    public void onTituloDBSeleccionado(Titulo titulo) {
        SharedPreferences preferences = getActivity().getSharedPreferences("usuario", Context.MODE_PRIVATE);
        String nombreTitulo = titulo.titleText;

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("nombreTitulo", nombreTitulo);

        tituloUsuario.setText(nombreTitulo);
        editor.commit();

    }

    @Override
    public void onTarjetaSeleccionada(Tarjeta tarjeta) {
        SharedPreferences preferences = getActivity().getSharedPreferences("usuario", Context.MODE_PRIVATE);
        String urlImagen = tarjeta.largeArt;
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("urlImagen", urlImagen);

        Picasso.get().load(urlImagen).into(imagenUsuario);
        editor.commit();
    }
}
