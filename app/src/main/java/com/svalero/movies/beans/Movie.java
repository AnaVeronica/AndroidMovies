package com.svalero.movies.beans;

import com.google.gson.annotations.SerializedName;

public class Movie {
    private int id;
    @SerializedName("title")
    private String titulo;
    @SerializedName("overview")
    private String sinopsis;
    @SerializedName("poster_path")
    private String image;
    @SerializedName("original_language")
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
}
