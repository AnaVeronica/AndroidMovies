package com.svalero.movies.movies.filter.contract;

import android.content.Context;

import com.svalero.movies.beans.Movie;

import java.util.ArrayList;

public interface FilterMoviesContract {

    interface View {
        void success(ArrayList<Movie> movies);
        void error(String message);
    }

    interface Presenter {
        void getMovies(Context context, String idioma);
    }

    interface Model {
        void getMoviesWS(Context context, OnMoviesListener onMoviesListener, String idioma);

        interface OnMoviesListener{
            void resolve(ArrayList<Movie> movies);
            void reject(String error);
        }
    }

}

