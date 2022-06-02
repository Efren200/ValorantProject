package com.efpaga.valorantprojectfinal.threads;

import com.efpaga.valorantprojectfinal.room.AppDataBase;
import com.efpaga.valorantprojectfinal.room.Titulo;

//Este hilo sirve para poder eliminar un titulo  de la base de datos fuera del hilo principal.
public class HiloBorraTitulo extends Thread{
    private AppDataBase db;
    private Titulo titulo;
    //En el constructor le pasamos la base de datos y el titulo a eliminar
    public HiloBorraTitulo(AppDataBase db, Titulo titulo) {
        this.db = db;
        this.titulo = titulo;
    }
    //En el metodo run ejecutaremos la funcion de delete titulo para eliminarlo de la base de datos.
    @Override
    public void run() {
        db.userDao().deleteTitulo(titulo);
    }
}
