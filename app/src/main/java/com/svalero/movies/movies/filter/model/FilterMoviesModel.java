package com.svalero.movies.movies.filter.model;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.Nullable;

import com.svalero.movies.beans.Movie;
import com.svalero.movies.beans.MoviesAPIResult;
import com.svalero.movies.movies.filter.contract.FilterMoviesContract;
import com.svalero.movies.retrofit.ApiClient;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilterMoviesModel implements FilterMoviesContract.Model {

    @Override
    public void getMoviesWS(Context context, final FilterMoviesContract.Model.OnMoviesListener onMoviesListener, String idioma) {
        ApiClient apiClient = new ApiClient(context); // Instanciar a la clase ApiClient
        final Call<MoviesAPIResult> request = apiClient.getMoviesByOriginalLanguage(idioma); // Devuelve un Call de MoviesAPIResult

        // Encolar las peticiones para ejecutarlas en un thread aparte
        request.enqueue(new Callback<MoviesAPIResult>() { // MoviesAPIResult es el objeto que se desea obtener
            @Override
            public void onResponse(@Nullable Call<MoviesAPIResult> call, @Nullable Response<MoviesAPIResult> response) {
                if (response != null && response.body() != null) {
                    onMoviesListener.resolve(new ArrayList<Movie>(response.body().getResults()));
                }
            }

            @Override
            public void onFailure(@Nullable Call<MoviesAPIResult> call, @Nullable Throwable t) {
                t.printStackTrace();
                onMoviesListener.reject(t.getLocalizedMessage());
            }
        });
    }
}
