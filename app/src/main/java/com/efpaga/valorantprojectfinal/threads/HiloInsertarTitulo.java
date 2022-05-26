package com.efpaga.valorantprojectfinal.threads;

import com.efpaga.valorantprojectfinal.room.AppDataBase;
import com.efpaga.valorantprojectfinal.room.Tarjeta;
import com.efpaga.valorantprojectfinal.room.Titulo;

//HIlo para poder insertar un titulo dentro de la base de datos fuera del hilo principal.
public class HiloInsertarTitulo extends Thread{

    private AppDataBase db;
    private Titulo titulo;

    public HiloInsertarTitulo(AppDataBase db, Titulo titulo) {
        this.db = db;
        this.titulo = titulo;
    }

    @Override
    public void run() {

        db.userDao().insertTitulo(titulo);
    }
}
