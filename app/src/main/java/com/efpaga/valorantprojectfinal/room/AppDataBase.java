package com.efpaga.valorantprojectfinal.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

//Base de datos room la cual tiene solamente dos tablas, la de tarjeta y titulo.
@Database(entities = {
        Tarjeta.class, Titulo.class
},
        version = 1)

//Clase AppDataBase que extiende de room con un contructor para poder acceder a las peticiones de la base de datos
public abstract class AppDataBase extends RoomDatabase {
    public abstract UsuarioDao userDao();
}
