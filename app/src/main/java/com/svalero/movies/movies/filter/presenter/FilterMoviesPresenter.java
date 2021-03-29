package com.svalero.movies.movies.filter.presenter;

import android.content.Context;

import com.svalero.movies.beans.Movie;
import com.svalero.movies.movies.filter.contract.FilterMoviesContract;
import com.svalero.movies.movies.filter.model.FilterMoviesModel;

import java.util.ArrayList;


public class FilterMoviesPresenter implements FilterMoviesContract.Presenter {

    private FilterMoviesModel filterMoviesModel;
    private FilterMoviesContract.View vista;

    public FilterMoviesPresenter(FilterMoviesContract.View vista) {
        this.vista = vista;
        this.filterMoviesModel = new FilterMoviesModel();
    }

    @Override
    public void getMovies(Context context, String idioma) {
        filterMoviesModel.getMoviesWS(context, new FilterMoviesContract.Model.OnMoviesListener() {
            @Override
            public void resolve(ArrayList<Movie> movies) {
                vista.success(movies);
            }

            @Override
            public void reject(String error) {
                vista.error(error);
            }
        },idioma);
    }
}
