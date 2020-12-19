package com.svalero.movies.movies.filter.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.movies.R;
import com.svalero.movies.beans.Movie;
import com.svalero.movies.movies.adapter.MovieAdapter;
import com.svalero.movies.movies.filter.contract.FilterMoviesContract;
import com.svalero.movies.movies.filter.presenter.FilterMoviesPresenter;
import com.svalero.movies.movies.lstMovies.view.LstMoviesActivity;

import java.util.ArrayList;

public class FilterMoviesActivity extends AppCompatActivity implements FilterMoviesContract.View {
    //filtrar por original_language
    private RecyclerView recycler;
    private RecyclerView.LayoutManager lManager;
    private FilterMoviesPresenter filterMoviesPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_movies);

        Intent i = this.getIntent();
        String idioma = i.getStringExtra("idioma");


        filterMoviesPresenter = new FilterMoviesPresenter(this, idioma);
        filterMoviesPresenter.getMovies(idioma);
    }

    @Override
    public void success(ArrayList<Movie> movies) {
        // Obtener el Recycler
        recycler = findViewById(R.id.recyclerMovies);
        //recycler.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        // Crear un nuevo adaptador
        MovieAdapter adapter = new MovieAdapter(movies);
        recycler.setAdapter(adapter);
    }

    @Override
    public void error(String mensaje) {
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
    }

}
