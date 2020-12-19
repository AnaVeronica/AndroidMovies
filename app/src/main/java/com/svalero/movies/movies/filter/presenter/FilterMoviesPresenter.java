package com.svalero.movies.movies.filter.presenter;

import com.svalero.movies.beans.Movie;
import com.svalero.movies.movies.filter.contract.FilterMoviesContract;
import com.svalero.movies.movies.filter.model.FilterMoviesModel;

import java.util.ArrayList;


public class FilterMoviesPresenter implements FilterMoviesContract.Presenter {

    private FilterMoviesModel filterMoviesModel;
    private FilterMoviesContract.View vista;
    private String idioma;

    public FilterMoviesPresenter(FilterMoviesContract.View vista, String idioma) {
        this.vista = vista;
        this.idioma = idioma;
        this.filterMoviesModel = new FilterMoviesModel(idioma);
    }

    @Override
    public void getMovies(String idioma) {
        filterMoviesModel.getMoviesWS(new FilterMoviesContract.Model.OnMoviesListener() {
            @Override
            public void resolve(ArrayList<Movie> movies) {
                vista.success(movies);
            }

            @Override
            public void reject(String error) {
                vista.error("Fallo al traer los datos");
            }
        },idioma);
    }
}
