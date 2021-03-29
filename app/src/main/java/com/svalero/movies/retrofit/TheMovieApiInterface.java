package com.svalero.movies.retrofit;

import com.svalero.movies.beans.MoviesAPIResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TheMovieApiInterface {

    @GET("movie/popular?api_key=d9c4177bb1cc819d43088d25fbe2474c&language=es-ES&page=1")
    //Devuelve una clase con los elementos igual que aparecen en el JSON respuesta que viene de la API
    Call<MoviesAPIResult> getMovies();

    @GET("movie/popular?api_key=d9c4177bb1cc819d43088d25fbe2474c&language=es-ES")
    Call<MoviesAPIResult> getMoviesByOriginalLanguage(@Query("with_original_language") String language);
}
