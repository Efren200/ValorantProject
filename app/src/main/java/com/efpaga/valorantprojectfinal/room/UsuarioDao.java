package com.efpaga.valorantprojectfinal.room;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

//Interfaz de la base de datos room donde estableceremos las diferentes consultas y peticiones a la base de datos
@Dao
public interface UsuarioDao {

    @Query("SELECT * FROM Tarjeta")
    List<Tarjeta> getAllTarjetas();
    @Insert(onConflict = REPLACE)
    void insertTarjeta(Tarjeta tarjeta);
    @Delete
    void deleteTarjeta(Tarjeta tarjeta);


    @Query("SELECT * FROM Titulo")
    List<Titulo> getAllTitulos();

    @Insert(onConflict = REPLACE)
    void insertTitulo(Titulo titulo);

    @Delete
    void deleteTitulo(Titulo titulo);

}