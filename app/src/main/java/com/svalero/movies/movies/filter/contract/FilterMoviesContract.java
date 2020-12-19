package com.svalero.movies.movies.filter.contract;

import com.svalero.movies.beans.Movie;
import com.svalero.movies.movies.lstMovies.contract.LstMoviesContract;

import java.util.ArrayList;

public interface FilterMoviesContract {

    interface View {
        void success(ArrayList<Movie> movies);
        void error(String mensaje);
    }

    interface Presenter {
        void getMovies(String idioma);
    }

    interface Model {
        void getMoviesWS(OnMoviesListener onMoviesListener, String idioma);

        interface OnMoviesListener{
            void resolve(ArrayList<Movie> movies);
            void reject(String error);
        }
    }

}

