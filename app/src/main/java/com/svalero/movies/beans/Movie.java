package com.svalero.movies.beans;

import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Movie {
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String OVERVIEW = "overview";
    private static final String POSTER_PATH = "poster_path";
    private static final String ORIGINAL_LANGUAGE = "original_language";

    private int id;
    @SerializedName(TITLE)
    private String titulo;
    @SerializedName(OVERVIEW)
    private String sinopsis;
    @SerializedName(POSTER_PATH)
    private String image;
    @SerializedName(ORIGINAL_LANGUAGE)
    private String idioma;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getSinopsis() {
        return sinopsis;
    }
    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }


    public static ArrayList<Movie> getFilterArrayListFromJSON(JSONArray lstMovies){
        ArrayList<Movie> lista = null;
        try {
            if(lstMovies!=null && lstMovies.length() > 0 ){
                lista = new ArrayList<Movie>();
            }
            for (int i = 0; i < lstMovies.length(); i++) {
                JSONObject json_data = lstMovies.getJSONObject(i);
                Movie movie = new Movie();

                movie.setId(json_data.getInt(ID));
                movie.setTitulo(json_data.getString(TITLE));
                movie.setSinopsis(json_data.getString(OVERVIEW));
                movie.setImage(json_data.getString(POSTER_PATH));
                movie.setIdioma(json_data.getString(ORIGINAL_LANGUAGE));

                lista.add(movie);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
