package com.svalero.movies.movies.lstMovies.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.movies.R;
import com.svalero.movies.beans.Movie;
import com.svalero.movies.movies.adapter.MovieAdapter;
import com.svalero.movies.movies.lstMovies.contract.LstMoviesContract;
import com.svalero.movies.movies.lstMovies.presenter.LstMoviesPresenter;

import java.util.ArrayList;

public class LstMoviesActivity extends AppCompatActivity implements LstMoviesContract.View {

    private RecyclerView recycler;
    private LstMoviesPresenter lstMoviesPresenter;
    private RecyclerView.LayoutManager lManager;
    private MovieAdapter.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lst_movies);

        lstMoviesPresenter = new LstMoviesPresenter(this);
        lstMoviesPresenter.getMovies();
    }

    @Override
    public void success(ArrayList<Movie> movies) {
        // Obtener el Recycler
        recycler = (RecyclerView) findViewById(R.id.recyclerMovies);
        recycler.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        // 1º) Tipo Lista
        // 2º) Tipo Grid
        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        // Crear un nuevo adaptador
        setOnClickListener();
        MovieAdapter adapter = new MovieAdapter(movies, this, listener);
        recycler.setAdapter(adapter);
    }

    private void setOnClickListener() {
        listener = new MovieAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(), DescriptMoviesActivity.class);
                startActivity(intent);
            }
        };
    }


    @Override
    public void error(String message) {
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
    }
}
