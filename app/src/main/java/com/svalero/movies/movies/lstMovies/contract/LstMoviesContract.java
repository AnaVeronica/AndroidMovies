package com.svalero.movies.movies.lstMovies.contract;

import com.svalero.movies.beans.Movie;

import java.util.ArrayList;

public interface LstMoviesContract {
    interface View {
        void success(ArrayList<Movie> movies);
        void error(String message);
    }

    interface Presenter {
        void getMovies();
    }

    interface Model {
        /*Manda el Callback, camino de retorno*/
        void getMoviesWS(OnLstMoviesListener onLstMoviesListener);
        /*Programación Reactiva (Callback)*/
        interface OnLstMoviesListener{
            void resolve(ArrayList<Movie> movies);
            void reject(String error);
        }
    }

}
