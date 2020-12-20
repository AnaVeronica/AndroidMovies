package com.svalero.movies.movies.lstMovies.model;

import android.os.AsyncTask;

import com.svalero.movies.beans.Movie;
import com.svalero.movies.movies.lstMovies.contract.LstMoviesContract;
import com.svalero.movies.utils.Post;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LstMoviesModel implements LstMoviesContract.Model {
    private static final String URL ="https://api.themoviedb.org/3/movie/popular?api_key=d9c4177bb1cc819d43088d25fbe2474c&language=es-ES&page=1";
    private ArrayList<Movie> lstArrayMovies;
    OnLstMoviesListener onLstMoviesListener;

    @Override
    public void getMoviesWS(final OnLstMoviesListener onLstMoviesListener) {
        // Callback
        this.onLstMoviesListener = onLstMoviesListener;
        DataApiTask dat = new DataApiTask();
        dat.execute();
    }

    /**
     * Permite traer los datos del API en 2º plano
     */
    class DataApiTask extends AsyncTask<String, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(String... strings) {
            Post post = new Post();

            try {
                JSONObject objectMovies = post.getServerDataGetObject(URL);
                JSONArray lstMovies = objectMovies.getJSONArray("results");
                lstArrayMovies = Movie.
                        getArrayListFromJSON(lstMovies);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean resp) {
            if(resp){
                if(lstArrayMovies!=null && lstArrayMovies.size()>0){
                    onLstMoviesListener.resolve(lstArrayMovies);
                }
            }else{
                onLstMoviesListener.reject("Error al traer " +
                        "los datos del Servidor.");
            }
        }
    }
}
