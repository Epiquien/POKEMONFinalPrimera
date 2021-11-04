package com.example.finalunopokemons.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalunopokemons.Clases.Pokemon;
import com.example.finalunopokemons.DetallePokemonActivity;
import com.example.finalunopokemons.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PalabraViewHolder>{
    List<Pokemon> pokemons;

    public PokemonAdapter(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    @Override
    public PalabraViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_pokemon_layout, parent, false);

        return new PalabraViewHolder(view);
    }

    @Override
    public void onBindViewHolder( PokemonAdapter.PalabraViewHolder holder, int position) {
        View view= holder.itemView;

        Button  btnVerDetallePokemon = holder.itemView.findViewById(R.id.btnVerDetallePokemon);
        Pokemon pokemon= pokemons.get(position);
        TextView tvNombre = holder.itemView.findViewById(R.id.tvNombrePokemon);
        TextView tvTipo = holder.itemView.findViewById(R.id.tvTipoPokemon);
        ImageView imagenPokemon =holder.itemView.findViewById(R.id.imgPokemon);

        Picasso.get().load(pokemon.getImagen()).into(imagenPokemon);

        tvNombre.setText(pokemon.nombre);
        tvTipo.setText(pokemon.tipo);

        btnVerDetallePokemon.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(holder.itemView.getContext(), DetallePokemonActivity.class);
            //view.startActivity(intent);
            holder.itemView.getContext().startActivity(intent);
        }
});


    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }

    public class  PalabraViewHolder extends RecyclerView.ViewHolder {
    public PalabraViewHolder( View itemView) {
        super(itemView);
    }
}
}
