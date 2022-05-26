package com.efpaga.valorantprojectfinal.ui.usuario;

import com.efpaga.valorantprojectfinal.room.Tarjeta;
import com.efpaga.valorantprojectfinal.room.Titulo;
import com.efpaga.valorantprojectfinal.valorantapi.models.WeaponSkin;

public interface ObjetosUsuarioListener {

    void onTituloDBSeleccionado(Titulo titulo);

    void onTarjetaSeleccionada(Tarjeta tarjeta);
}
