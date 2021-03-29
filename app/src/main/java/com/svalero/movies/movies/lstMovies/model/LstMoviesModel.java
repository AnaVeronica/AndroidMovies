package com.svalero.movies.movies.lstMovies.model;

import android.content.Context;

import androidx.annotation.Nullable;

import com.svalero.movies.beans.Movie;
import com.svalero.movies.beans.MoviesAPIResult;
import com.svalero.movies.movies.lstMovies.contract.LstMoviesContract;
import com.svalero.movies.retrofit.ApiClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LstMoviesModel implements LstMoviesContract.Model {

    private static final String TAG = LstMoviesModel.class.getSimpleName();

    @Override
    public void getMoviesWS(Context context, final OnLstMoviesListener onLstMoviesListener) {
        ApiClient apiClient = new ApiClient(context); // Instanciar a la clase ApiClient
        final Call<MoviesAPIResult> request = apiClient.getMovies(); // Devuelve un Call de MoviesAPIResult

        // Encolar las peticiones para ejecutarlas en un thread aparte
        request.enqueue(new Callback<MoviesAPIResult>() { // MoviesAPIResult es el objeto que se desea obtener
            @Override
            public void onResponse(@Nullable Call<MoviesAPIResult> call, @Nullable Response<MoviesAPIResult> response) {
                if (response != null && response.body() != null) {
                    onLstMoviesListener.resolve(new ArrayList<Movie>(response.body().getResults()));
                }
            }

            @Override
            public void onFailure(@Nullable Call<MoviesAPIResult> call, @Nullable Throwable t) {
                t.printStackTrace();
                onLstMoviesListener.reject(t.getLocalizedMessage());
            }
        });
    }

}
