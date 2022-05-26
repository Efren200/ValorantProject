package com.efpaga.valorantprojectfinal.ui.agentes;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.efpaga.valorantprojectfinal.MainActivity;
import com.efpaga.valorantprojectfinal.R;
import com.efpaga.valorantprojectfinal.valorantapi.models.Agent;

//La clase AgentesFragment es al encargada de cargar a los ajentes dentro del fragment principal del Navigation Drawer
public class AgentesFragment extends Fragment implements AgentesAdapter.OnAgenteListener{

    private Agent[] listaAgentes;

    //En el onCreateView vamos a recuperar la vista del fragment de agentes para devolverla, y tambien cargamos los agentes dentro
    //del array mediande el hilo de la main activity encargado de cargar los datos.
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_agentes, container, false);

        listaAgentes = MainActivity.hiloCargarInformacion.getAgents();
        return rootView;
    }

    //En el onViewCreated, generamos el adapter pasandole la lista de agentes cargados y el listener que proviene del mismo para poder
    //establecer una accion cuando hagamos onclick encima del cardview del agente.

    //Tambien recuperamos el recyclerview donde mostraremos los cardview y le pondremos el adapter
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final AgentesAdapter adapter = new AgentesAdapter(listaAgentes, this);

        final RecyclerView recycler = (RecyclerView) view.findViewById(R.id.recyclerAgentes);
        recycler.setLayoutManager(new GridLayoutManager(getContext(), 1));
        recycler.setHasFixedSize(true);
        recycler.setAdapter(adapter);
    }


    //Esta funcion proviene del listener, la cual como el nombre indica, en el momento que se haga onclick en el cardview haremos un intent a la
    //classe donde mostraremos las caracteristicas y habilidades de el agente seleccionado ya que lo pasaremos como parametro.
    @Override
    public void onAgenteClick(int position) {
        Intent intent = new Intent(this.getActivity(), AgenteActivity.class);
        intent.putExtra("agente", listaAgentes[position]);
        startActivity(intent);

    }
}