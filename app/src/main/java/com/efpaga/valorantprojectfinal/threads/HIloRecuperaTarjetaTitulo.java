package com.efpaga.valorantprojectfinal.threads;

import com.efpaga.valorantprojectfinal.room.AppDataBase;
import com.efpaga.valorantprojectfinal.room.Tarjeta;
import com.efpaga.valorantprojectfinal.room.Titulo;

import java.util.List;

//Hilo encargado de cargar dentro del apartado usuario todos los datos de dentro de la base de datos
public class HIloRecuperaTarjetaTitulo extends Thread{

    private AppDataBase db;
    private List<Tarjeta> listaTarjetasBD;
    private List<Titulo> listaTitulosBD;

    //En el constructor solo le pasaremos la base de datos.
    public HIloRecuperaTarjetaTitulo(AppDataBase db) {
        this.db = db;
    }


    //Cargamos en los elementos list todos los objetos cargados dentro de la base de datos
    @Override
    public void run() {
        listaTarjetasBD = db.userDao().getAllTarjetas();
        listaTitulosBD = db.userDao().getAllTitulos();
    }

    //Generamos getters para recuperar los datos dentro del apartado de usuario y mostrarlos.
    public List<Tarjeta> getTarjetas(){
        return listaTarjetasBD;
    }

    public List<Titulo> getTitulos(){
        return listaTitulosBD;
    }
}
