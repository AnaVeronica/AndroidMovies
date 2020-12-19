package com.svalero.movies.movies.filter.model;

import android.os.AsyncTask;

import com.svalero.movies.beans.Movie;
import com.svalero.movies.movies.filter.contract.FilterMoviesContract;
import com.svalero.movies.utils.Post;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class FilterMoviesModel implements FilterMoviesContract.Model {
    private static final String URL = "https://api.themoviedb.org/3/movie/popular?api_key=d9c4177bb1cc819d43088d25fbe2474c&language=";

    private ArrayList<Movie> lstArrayMovies;
    private String idioma;
    OnMoviesListener onMoviesListener;

    public FilterMoviesModel(String idioma) {
        this.idioma = idioma;
    }

    @Override
    public void getMoviesWS(OnMoviesListener onMoviesListener, String idioma) {
        this.onMoviesListener = onMoviesListener;
        this.idioma = idioma;

        DataApiTask data = new DataApiTask(idioma);
        data.execute();
    }

    /**
     * Permite traer los datos del API en 2º plano
     */
    class DataApiTask extends AsyncTask<String, Integer, Boolean> {

        private String idioma;

        public DataApiTask(String idioma) {
            this.idioma = idioma;
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            Post post = new Post();

            try {
                JSONObject objectMovies = post.getServerDataGetObject(URL + idioma);
                JSONArray lstMovies = objectMovies.getJSONArray("results");
                lstArrayMovies = Movie.getFilterArrayListFromJSON(lstMovies);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return true;
        }
        @Override
        protected void onPostExecute(Boolean resp) {
            if(resp){
                if(lstArrayMovies!=null && lstArrayMovies.size()>0){
                    onMoviesListener.resolve(lstArrayMovies);
                }
            }else{
                onMoviesListener.reject("Error al traer " +
                        "los datos del Servidor.");
            }
        }
    }
}
