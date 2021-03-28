package com.svalero.movies.movies.lstMovies.presenter;

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
    public void getMovies() {
        lstMoviesModel.getMoviesWS(new LstMoviesContract.Model.OnLstMoviesListener() {

            /**
             * Si es correcto le pasa a la vista los datos
             * @param movies
             */
            @Override
            public void resolve(ArrayList<Movie> movies) {
                vista.success(movies);
            }

            /**
             * Si algo ha fallado notifica el error
             * @param error
             */
            @Override
            public void reject(String error) {
                vista.error(error);
            }
        });
    }
}
