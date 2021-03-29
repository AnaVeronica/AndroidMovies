package com.svalero.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.svalero.movies.movies.lstMovies.view.LstMoviesActivity;

public class MainActivity extends AppCompatActivity {

    private static final int SCREEN = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Lanzar un proceso en 2º plano que se active a los 3sg
        final Handler handler = new Handler();
        handler.postDelayed(
                new Runnable() { // Interface
                    @Override
                    public void run() {
                        // Cargar la 2ª pantalla
                        Intent intent = new Intent(
                                getBaseContext(), LstMoviesActivity.class);
                        startActivity(intent);
                    }
                }
                , 3000
        );
    }
}