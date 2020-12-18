package com.svalero.movies.movies.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.svalero.movies.R;
import com.svalero.movies.beans.Movie;

import java.util.ArrayList;

public class DescriptMovieAdapter {




    /*private Context context;

    public DescriptMovieAdapter(Context context) {

    }

    public class DescriptMovieViewHolder extends RecyclerView.ViewHolder {
        public ImageView img; //0x565854
        public TextView titulo; // 0x457855
        public TextView sipnosis; // 0x487889

        public DescriptMovieViewHolder(View v){
            super(v);
            img = v.findViewById(R.id.imgMovie);
            titulo = v.findViewById(R.id.txtTitulo);
            sipnosis = v.findViewById(R.id.txtSipnosis);
        }
    }

    @NonNull
    @Override
    public DescriptMovieAdapter.DescriptMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_descript_movies, parent, false);

        *//*Devuelve la dirección de memoria de los 3 elementos por fila para que sea
        más rápido acceder a los elementos y manipularlos (Sólo sucede una vez)*//*
        return new DescriptMovieViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DescriptMovieAdapter.DescriptMovieViewHolder holder, int position) {
        // holder.img
        Movie movie = lstMovies.get(position);

        holder.titulo.setText(movie.getTitulo());
        holder.sipnosis.setText(movie.getSinopsis());

        //holder.boton.setOnClickListener
        Picasso.with(context).load("https://image.tmdb.org/t/p/w500" + movie.getImage()).into(holder.img);

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    */
}
