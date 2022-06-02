package com.efpaga.valorantprojectfinal.threads;

import com.efpaga.valorantprojectfinal.MainActivity;
import com.efpaga.valorantprojectfinal.valorantapi.ValorantApi;
import com.efpaga.valorantprojectfinal.valorantapi.models.Agent;
import com.efpaga.valorantprojectfinal.valorantapi.models.Bundles;
import com.efpaga.valorantprojectfinal.valorantapi.models.Card;
import com.efpaga.valorantprojectfinal.valorantapi.models.ContentTier;
import com.efpaga.valorantprojectfinal.valorantapi.models.Theme;
import com.efpaga.valorantprojectfinal.valorantapi.models.Title;
import com.efpaga.valorantprojectfinal.valorantapi.models.ValorantMap;
import com.efpaga.valorantprojectfinal.valorantapi.models.Weapon;
import com.efpaga.valorantprojectfinal.valorantapi.models.WeaponSkin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//Este hilo esta pensado para que en el main mientras cargue la aplicacion, este se ejecute fuera del hilo principal
//cargando todos los datos de la api dentro de la app, para que no haya congelamientos en la app.
public class HiloCargarInformacion extends Thread{
    //Creamos los arrays donde cargaremos los datos y un objeto ValorantApi que
    //es la que contiene todos los metodos para cargar los datos.
    private Agent[] listaAgentes;
    private Weapon[] listaArmas;
    private Card[] listaTarjetas;
    private Title[] listaTitulos;
    private ValorantApi valorantApi = new ValorantApi();
    private Theme[] listaTheme;
    private List<Theme> arrayListThemesValida = new ArrayList<>();
    private Theme[] listaThemesValida;
    private ContentTier[] listContentTiers;
    //Dentro del metodo run vamos a llenar cada uno de los arrays
    // con sus objetos correspondientes.
    @Override
    public void run() {
        listaAgentes = valorantApi.getAgents().getData();
        listaArmas = valorantApi.getWeapons().getData();
        listaTarjetas = valorantApi.getCards().getData();
        listaTitulos = valorantApi.getTitles().getData();
        listContentTiers = valorantApi.getContentTiers().getData();
        listaTheme = valorantApi.getThemes().getData();
        //Esto sirve para poder arreglarme una lista necesaria para la aplicación
        for (Weapon weapon: listaArmas){
            for(WeaponSkin skin : weapon.getSkins()){
                for(Theme theme: listaTheme){
                    if(skin.getThemeUuid().equals(theme.getUuid())){
                        arrayListThemesValida.add(theme);
                    }
                }
            }
        }
        Set<Theme> hashSet = new HashSet<Theme>(arrayListThemesValida);
        arrayListThemesValida.clear();
        arrayListThemesValida.addAll(hashSet);
        listaThemesValida = arrayListThemesValida.toArray(new Theme[arrayListThemesValida.size()]);
    }

    //Generamos geters de cada uno de los arrays para poder recuperar la informacion dentro de la aplicacion.
    public Agent[] getAgents(){
        return listaAgentes;
    }
    public Weapon[] getWeapons(){
        return listaArmas;
    }
    public Card[] getTarjetas(){
        return listaTarjetas;
    }
    public Title[] getTitulos(){
        return listaTitulos;
    }

    public Theme[] getThemes(){
        return listaThemesValida;
    }

    public ContentTier[] getListContentTiers(){
        return listContentTiers;
    }


}
