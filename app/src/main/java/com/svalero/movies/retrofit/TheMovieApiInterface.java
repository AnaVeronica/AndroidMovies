package com.svalero.movies.retrofit;

import com.svalero.movies.beans.MoviesAPIResult;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TheMovieApiInterface {

    @GET("movie/popular?api_key=d9c4177bb1cc819d43088d25fbe2474c&language=en-US&page=1")
    //Devuelve una clase con los elementos igual que aparecen en el JSON respuesta que viene de la API
    Call<MoviesAPIResult> getMovies();
}
