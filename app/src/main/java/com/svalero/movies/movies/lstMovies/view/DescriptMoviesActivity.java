package com.svalero.movies.movies.lstMovies.view;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.movies.R;
import com.svalero.movies.beans.Movie;
import com.svalero.movies.movies.adapter.DescriptMovieAdapter;
import com.svalero.movies.movies.lstMovies.contract.LstMoviesContract;
import com.svalero.movies.movies.lstMovies.presenter.LstMoviesPresenter;

import java.util.ArrayList;

public class DescriptMoviesActivity extends AppCompatActivity implements LstMoviesContract.View {

    private RecyclerView recycler;
    private LstMoviesPresenter lstMoviesPresenter;
    private RecyclerView.LayoutManager dManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descript_movies);

        lstMoviesPresenter = new LstMoviesPresenter(this);
        lstMoviesPresenter.getMovies();

    }

    @Override
    public void success(ArrayList<Movie> movies) {
        // Crear un nuevo adaptador
        /*DescriptMovieAdapter adapter = new DescriptMovieAdapter(movies);
        recycler.setAdapter(adapter);*/
    }

    @Override
    public void error(String message) {
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
    }
}
