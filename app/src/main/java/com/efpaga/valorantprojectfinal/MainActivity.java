package com.efpaga.valorantprojectfinal;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.efpaga.valorantprojectfinal.room.AppDataBase;
import com.efpaga.valorantprojectfinal.threads.HIloRecuperaTarjetaTitulo;
import com.efpaga.valorantprojectfinal.threads.HiloCargarInformacion;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.efpaga.valorantprojectfinal.databinding.ActivityMainBinding;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private NavigationView navigationViewNombre;

    public static HiloCargarInformacion hiloCargarInformacion;
    public static HIloRecuperaTarjetaTitulo recuperaTarjetaTitulo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Cargamos toda la informacion cuando se accede a esta, para que tengamos todos los datos
        hiloCargarInformacion = new HiloCargarInformacion();
        hiloCargarInformacion.start();
        try {
            hiloCargarInformacion.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        setSupportActionBar(binding.appBarMain.toolbar);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_agentes, R.id.nav_armas, R.id.nav_skins, R.id.nav_tarjetas, R.id.nav_titulos, R.id.nav_usuario)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        //Recogemos los elementos del nav_header para poderlos modificar desde el apartado de usuario
        navigationViewNombre = (NavigationView) this.findViewById(R.id.nav_view);
        View headerView = navigationViewNombre.getHeaderView(0);
        TextView nombreUsuario = (TextView) headerView.findViewById(R.id.tvNombreUsuario);
        TextView tituloUsuario = (TextView) headerView.findViewById(R.id.tvTituloGuardado);
        ImageView imagenUsuario = (ImageView) headerView.findViewById(R.id.ivTarjetaGuardada);

        //Mediante el shared preferences guardo los datos que ha elegido. Si es la primera vez que se entra a la aplicacion tiene unos valores por defecto.
        SharedPreferences preferences = this.getSharedPreferences("usuario", Context.MODE_PRIVATE);
        nombreUsuario.setText(preferences.getString("nombreUsuario", "NombreUsuario"));
        tituloUsuario.setText(preferences.getString("nombreTitulo", "NombreTitulo"));
        String urlImagen = preferences.getString("urlImagen", "");

        if(!urlImagen.equals("")){
            Picasso.get().load(urlImagen).into(imagenUsuario);
        }
        else{

            System.out.println("Es nulooo");
        }





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}