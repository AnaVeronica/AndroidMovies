package com.svalero.movies.movies.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.svalero.movies.movies.lstMovies.view.DescriptMoviesActivity;

import java.util.ArrayList;

/**
 * Compone e integra los datos en los elementos visuales que corresponde.
 * Junto con el RecyclerView se encarga de mostrar los datos de forma elegante en la pantalla
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private ArrayList<Movie> lstMovies;

    public MovieAdapter(ArrayList<Movie> lstMovies) {
        this.lstMovies = lstMovies;
    }

    //Le he quitado lo estático a la clase MovieViewHolder
    /*Tantos elementos como objetos quiera mostrar en la fila*/
    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        public ImageView img; //0x565854
        public TextView titulo; // 0x457855
        public TextView sipnosis; // 0x487889

        public MovieViewHolder(View v){
            super(v);
            img = v.findViewById(R.id.imgMovie);
            titulo = v.findViewById(R.id.txtTitulo);
            sipnosis = v.findViewById(R.id.txtSipnosis);

        }

    }

    /**
     * Crea la estructura en la que hay que insertar datos de la fila
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_movie, parent, false);

        /*Devuelve la dirección de memoria de los 3 elementos por fila para que sea
        más rápido acceder a los elementos y manipularlos (Sólo sucede una vez)*/
        return new MovieViewHolder(v);
    }

    /**
     * Pinta los datos correspondientes en cada fila
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        // holder.img
        final Movie movie = lstMovies.get(position);

        holder.titulo.setText(movie.getTitulo());

        Picasso.get().load("https://image.tmdb.org/t/p/w500" + movie.getImage()).into(holder.img);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DescriptMoviesActivity.class);
                intent.putExtra("titulo", movie.getTitulo());
                intent.putExtra("imagen", movie.getImage());
                intent.putExtra("sipnosis", movie.getSinopsis());
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return lstMovies.size();
    }

    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }

}
