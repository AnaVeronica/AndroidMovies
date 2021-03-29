package com.svalero.movies.retrofit;

import android.content.Context;

import com.svalero.movies.BuildConfig;
import com.svalero.movies.beans.MoviesAPIResult;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private Retrofit retrofit;
    private Context context;

    public ApiClient(Context context) {
        this.context = context;

        // Crear el objeto Retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.URL_BASE) // URL Base
                .addConverterFactory(GsonConverterFactory.create()) // Parsea automáticamente de JSON al Bean
                .build();
    }

    // METODOS PARA LLAMAR AL BACKEND
    public Call<MoviesAPIResult> getMovies() {
        TheMovieApiInterface service = retrofit.create(TheMovieApiInterface.class);
        return service.getMovies();
    }

    public Call<MoviesAPIResult> getMoviesByOriginalLanguage(String language) {
        TheMovieApiInterface service = retrofit.create(TheMovieApiInterface.class);
        return service.getMoviesByOriginalLanguage(language);
    }
}
