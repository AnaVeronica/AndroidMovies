package com.svalero.movies.movies.filter.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.movies.R;
import com.svalero.movies.beans.Movie;
import com.svalero.movies.movies.adapter.MovieAdapter;
import com.svalero.movies.movies.filter.contract.FilterMoviesContract;
import com.svalero.movies.movies.filter.presenter.FilterMoviesPresenter;

import java.util.ArrayList;

public class FilterMoviesActivity extends AppCompatActivity implements FilterMoviesContract.View {
    private RecyclerView recycler;
    private RecyclerView.LayoutManager lManager;
    private FilterMoviesPresenter filterMoviesPresenter;
    String idioma;

    private View layoutError;
    private TextView textViewError;
    private Button buttonRetry;
    private ProgressBar progressBarLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_movies);

        Intent i = this.getIntent();
        idioma = i.getStringExtra("idioma");

        // Obtener el Recycler y el layout y sus componentes en caso de error
        recycler = findViewById(R.id.recyclerFilterMovies);
        layoutError = findViewById(R.id.activity_filter_productos_layout_error);
        textViewError = findViewById(R.id.activity_filter_productos_txt_error);
        buttonRetry = findViewById(R.id.activity_filter_productos_button_retry);
        progressBarLoading = findViewById(R.id.activity_filter_productos_progressbar_loading);

        progressBarLoading.setVisibility(View.VISIBLE);


        filterMoviesPresenter = new FilterMoviesPresenter(this);
        filterMoviesPresenter.getMovies(this, idioma);

        setRetry();
    }

    private void setRetry() {
        buttonRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBarLoading.setVisibility(View.VISIBLE);
                hideError();

                filterMoviesPresenter.getMovies(FilterMoviesActivity.this, idioma);
            }
        });
    }

    @Override
    public void success(ArrayList<Movie> movies) {
        showDataInRecyclerView(movies);
    }

    private void showDataInRecyclerView(ArrayList<Movie> movies) {
        progressBarLoading.setVisibility(View.GONE);

        recycler.setVisibility(View.VISIBLE);
        layoutError.setVisibility(View.GONE);

        recycler.setHasFixedSize(true);

        // Administrador para LinearLayout tipo lista
        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        // Crear un nuevo adaptador
        MovieAdapter adapter = new MovieAdapter(movies);
        recycler.setAdapter(adapter);
    }

    @Override
    public void error(String mensaje) {
        showError(mensaje);
    }

    private void showError(String message) {
        progressBarLoading.setVisibility(View.GONE);

        recycler.setVisibility(View.GONE);
        layoutError.setVisibility(View.VISIBLE);

        textViewError.setText(message);
    }

    private void hideError() {
        layoutError.setVisibility(View.GONE);
    }

}
