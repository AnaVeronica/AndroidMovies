package com.svalero.movies.movies.lstMovies.presenter;

import android.content.Context;

import com.svalero.movies.beans.Movie;
import com.svalero.movies.movies.lstMovies.contract.LstMoviesContract;
import com.svalero.movies.movies.lstMovies.model.LstMoviesModel;

import java.util.ArrayList;

public class LstMoviesPresenter implements LstMoviesContract.Presenter {

    private LstMoviesModel lstMoviesModel;
    private LstMoviesContract.View vista;

    public LstMoviesPresenter(LstMoviesContract.View vista) {
        this.vista = vista;
        this.lstMoviesModel = new LstMoviesModel();
    }

    /**
     * Obtiene la lista de películas
     */
    @Override
    public void getMovies(Context context) {
        lstMoviesModel.getMoviesWS(context, new LstMoviesContract.Model.OnLstMoviesListener() {
            @Override
            public void resolve(ArrayList<Movie> movies) {
                vista.success(movies);
            }
            @Override
            public void reject(String error) {
                vista.error(error);
            }
        });

    }
}
