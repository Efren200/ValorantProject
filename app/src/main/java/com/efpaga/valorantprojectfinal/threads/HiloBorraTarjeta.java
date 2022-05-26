package com.efpaga.valorantprojectfinal.threads;

import com.efpaga.valorantprojectfinal.room.AppDataBase;
import com.efpaga.valorantprojectfinal.room.Tarjeta;
import com.efpaga.valorantprojectfinal.room.Titulo;

//Este hilo sirve para poder eliminar una tarjeta de la base de datos fuera del hilo principal.
public class HiloBorraTarjeta extends Thread{
    private AppDataBase db;
    private Tarjeta tarjeta;

    //En el constructor le pasamos la base de datos y la tarjeta a eliminar
    public HiloBorraTarjeta(AppDataBase db, Tarjeta tarjeta) {
        this.db = db;
        this.tarjeta = tarjeta;
    }

    //En el metodo run ejecutaremos la funcion de delete tarjeta para eliminarla de la base de datos.
    @Override
    public void run() {

        db.userDao().deleteTarjeta(tarjeta);
    }
}
