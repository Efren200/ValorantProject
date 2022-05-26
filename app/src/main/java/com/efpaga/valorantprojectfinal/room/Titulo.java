package com.efpaga.valorantprojectfinal.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//Clase titulo, compuesta por tres columnas
@Entity
public class Titulo {
    //Ponemos PrimaryKey para indicarle a la base de datos room que es la clave primaria
    @PrimaryKey
    @NonNull
    public String id;
    //El columnInfo es para establecer un nombre a la columna de la base de datos
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "titleText")
    public String titleText;

    //Constructor para poder crear un objeto titulo a partir de los objetos leeidos con la api que recoje los json.
    public Titulo(@NonNull String id, String name, String titleText) {
        this.id = id;
        this.name = name;
        this.titleText = titleText;
    }
}
