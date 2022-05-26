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

    //El @query es para indicar que consulta es y debajo lo que nos va a devolver y el nombre de la funcion.
    @Query("SELECT * FROM Tarjeta")
    List<Tarjeta> getAllTarjetas();

    @Query("SELECT * FROM Tarjeta WHERE id = :id")
    Tarjeta getTarjetaBYId(String id);

    //El @update es para indicar que es una actualización de un objeto concreto, el cual le pasaremos directamente a la funcion.
    @Update
    void updateTarjetas(Tarjeta tarjeta);

    //EL @insert es para indicar que es una iserción dentro de la base de datos. El onConflict sirve para que en el momento que detecte que
    //ese objeto ya esta en la base de datos lo reemplaze con los nuevos atributos.
    @Insert(onConflict = REPLACE)
    void insertTarjeta(Tarjeta tarjeta);

    //EL @delete es para indicar a la base de datos que con esa funcion va a eliminar el objeto pasado como parametro de la misma.
    @Delete
    void deleteTarjeta(Tarjeta tarjeta);

    @Query("SELECT * FROM Titulo")
    List<Titulo> getAllTitulos();

    @Query("SELECT * FROM Titulo WHERE id = :id")
    Titulo getTituloBYId(String id);

    @Update
    void updateTitulo(Titulo titulo);

    @Insert(onConflict = REPLACE)
    void insertTitulo(Titulo titulo);

    @Delete
    void deleteTitulo(Titulo titulo);

}