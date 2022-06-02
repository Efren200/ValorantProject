package com.efpaga.valorantprojectfinal.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//Clase tarjeta, compuesta por cuatro columnas
@Entity
public class Tarjeta {
    //Ponemos PrimaryKey para indicarle a la base de datos room que es la clave primaria
    @PrimaryKey
    @NonNull public String id;
    //El columnInfo es para establecer un nombre a la columna de la base de datos
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "displayIcon")
    public String displayIcon;
    @ColumnInfo(name = "wideArt")
    public String wideArt;
    @ColumnInfo(name = "largeArt")
    public String largeArt;

    //Constructor para poder crear un objeto tarjeta a partir de los objetos leeidos con la api que recoje los json.
    public Tarjeta(String id, String name, String displayIcon, String wideArt, String largeArt) {
        this.id = id;
        this.name = name;
        this.displayIcon = displayIcon;
        this.wideArt = wideArt;
        this.largeArt = largeArt;
    }
}