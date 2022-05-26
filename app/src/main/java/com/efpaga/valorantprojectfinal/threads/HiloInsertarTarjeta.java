package com.efpaga.valorantprojectfinal.threads;


import com.efpaga.valorantprojectfinal.room.AppDataBase;
import com.efpaga.valorantprojectfinal.room.Tarjeta;

//Hilo para poder insertar una tarjeta dentro de la base de datos fuera del hilo principal.
public class HiloInsertarTarjeta extends Thread{

    private AppDataBase db;
    private Tarjeta tarjeta;

    public HiloInsertarTarjeta(AppDataBase db, Tarjeta tarjeta) {
        this.db = db;
        this.tarjeta = tarjeta;
    }

    @Override
    public void run() {

        db.userDao().insertTarjeta(tarjeta);
    }
}
