package com.efpaga.valorantprojectfinal.ui.themes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.efpaga.valorantprojectfinal.R;
import com.efpaga.valorantprojectfinal.valorantapi.models.Card;
import com.efpaga.valorantprojectfinal.valorantapi.models.Theme;

import java.util.ArrayList;


public class ThemesAdapter extends RecyclerView.Adapter<ThemesAdapter.ViewHolder> {

    private Theme[] listaTheme;
    private OnThemeListener onThemeListener;
    private boolean rgx = false;
    private boolean glitchpop = false;


    public ThemesAdapter(Theme[] listaTheme, ThemesAdapter.OnThemeListener onThemeListener){
        this.listaTheme = listaTheme;
        this.onThemeListener = onThemeListener;

    }

    @NonNull
    @Override
    public ThemesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_theme, parent, false);

        return new ThemesAdapter.ViewHolder(view, onThemeListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ThemesAdapter.ViewHolder holder, int position) {
        if(listaTheme[position].getDisplayName().equals("RGX 11z Pro") && !rgx){
            holder.nombreTheme.setText(listaTheme[position].getDisplayName() + ": 1");
            rgx = true;
        }
        else if(listaTheme[position].getDisplayName().equals("RGX 11z Pro") && rgx){
            holder.nombreTheme.setText(listaTheme[position].getDisplayName() + ": 2");
            rgx = false;
        }
        else if(listaTheme[position].getDisplayName().equals("Glitchpop") && !glitchpop){
            holder.nombreTheme.setText(listaTheme[position].getDisplayName() + ": 1");
            glitchpop = true;
        }
        else if(listaTheme[position].getDisplayName().equals("Glitchpop") && glitchpop){
            holder.nombreTheme.setText(listaTheme[position].getDisplayName() + ": 2");
            glitchpop = false;
        }
        else{
            holder.nombreTheme.setText(listaTheme[position].getDisplayName());
        }

    }

    @Override
    public int getItemCount() {
        return listaTheme.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView nombreTheme;
        ThemesAdapter.OnThemeListener onThemeListener;

        public ViewHolder(@NonNull View itemView, ThemesAdapter.OnThemeListener onThemeListener){

            super(itemView);
            nombreTheme = itemView.findViewById(R.id.nombreTheme);
            this.onThemeListener = onThemeListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onThemeListener.onThemeClick(getAdapterPosition());
        }
    }

    public interface OnThemeListener{

        void onThemeClick(int position);

    }

    public Theme[] filterList(ArrayList<Theme> filteredList){

        listaTheme = filteredList.toArray(new Theme[filteredList.size()]);
        notifyDataSetChanged();

        return listaTheme;
    }

}
