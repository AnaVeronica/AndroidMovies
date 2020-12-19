package com.svalero.movies.movies.lstMovies.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;
import com.svalero.movies.R;

public class DescriptMoviesActivity extends AppCompatActivity  {

    private TextView titulo;
    private TextView sipnosis;
    private ImageView imagen;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descript_movies);

        titulo = findViewById(R.id.txtTitulo);
        sipnosis = findViewById(R.id.txtSipnosis);
        imagen = findViewById(R.id.imgMovie);

        Intent intent = getIntent();
        String title = intent.getStringExtra("titulo");
        String sip = intent.getStringExtra("sipnosis");
        String img = intent.getStringExtra("imagen");

        titulo.setText(title);
        sipnosis.setText(sip);

        Picasso.get().load("https://image.tmdb.org/t/p/w500" + img).into(imagen);

    }


}
