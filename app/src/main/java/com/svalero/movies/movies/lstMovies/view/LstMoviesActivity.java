package com.svalero.movies.movies.lstMovies.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.movies.R;
import com.svalero.movies.beans.Movie;
import com.svalero.movies.movies.adapter.MovieAdapter;
import com.svalero.movies.movies.filter.view.FilterMoviesActivity;
import com.svalero.movies.movies.lstMovies.contract.LstMoviesContract;
import com.svalero.movies.movies.lstMovies.presenter.LstMoviesPresenter;

import java.util.ArrayList;

public class LstMoviesActivity extends AppCompatActivity implements LstMoviesContract.View {

    private static final String ESPANOL = "Español"; // es
    private static final String INGLES = "Inglés"; // en
    private static final String ALEMAN = "Alemán"; // de
    private static final String FRANCES = "Francés"; // fr
    private static final String COREANO = "Coreano"; // ko

    private RecyclerView recycler;
    private LstMoviesPresenter lstMoviesPresenter;
    private RecyclerView.LayoutManager lManager;
    private MovieAdapter.RecyclerViewClickListener listener;

    private View layoutError;
    private TextView textViewError;
    private Button buttonRetry;
    private Button button;
    private ProgressBar progressBarLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lst_movies);


        // Obtener el Recycler y el layout y sus componentes en caso de error
        recycler = findViewById(R.id.recyclerMovies);
        layoutError = findViewById(R.id.activity_lst_movies_layout_error);
        textViewError = findViewById(R.id.activity_lst_movies_txt_error);
        buttonRetry = findViewById(R.id.activity_lst_movies_button_retry);
        button = findViewById(R.id.submitButton);
        progressBarLoading = findViewById(R.id.activity_lst_movies_progressbar_loading);

        progressBarLoading.setVisibility(View.VISIBLE);

        cargarSpinner();

        lstMoviesPresenter = new LstMoviesPresenter(this);
        lstMoviesPresenter.getMovies(this);


        setRetry();
    }

    private void setRetry() {
        buttonRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBarLoading.setVisibility(View.VISIBLE);
                hideError();

                lstMoviesPresenter.getMovies(LstMoviesActivity.this);
            }
        });
    }

    @Override
    public void success(ArrayList<Movie> movies) {
        showDataInRecyclerView(movies);

        // Llama al evento al hacer click en la película
        setOnClickListener();
    }

    private void showDataInRecyclerView(ArrayList<Movie> movies) {
        progressBarLoading.setVisibility(View.GONE);

        recycler.setVisibility(View.VISIBLE);
        layoutError.setVisibility(View.GONE);

        recycler.setHasFixedSize(true);

        // Administrador para LinearLayout tipo lista
        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        // Crear un nuevo adaptador
        MovieAdapter adapter = new MovieAdapter(movies);
        recycler.setAdapter(adapter);
    }

    /**
     * Al hacer click en la película cambia a la pantalla donde aparece la descripción
     */
    private void setOnClickListener() {
        listener = new MovieAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(), DescriptMoviesActivity.class);
                startActivity(intent);
            }
        };
    }

    @Override
    public void error(String message) {
        showError(message);
    }

    private void showError(String message) {
        progressBarLoading.setVisibility(View.GONE);

        recycler.setVisibility(View.GONE);
        layoutError.setVisibility(View.VISIBLE);

        textViewError.setText(message);
    }

    private void hideError() {
        layoutError.setVisibility(View.GONE);
    }


    public void cargarSpinner() {
        final AutoCompleteTextView ac_languages = findViewById(R.id.languageTextView);

        ArrayList<String> listaIdiomas = getLanguagesList();

        // Crear el adaptador
        ArrayAdapter<String> adapter = new ArrayAdapter<>(LstMoviesActivity.this, R.layout.list_item, listaIdiomas);
        ac_languages.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idioma = String.valueOf(ac_languages.getText());

                switch (idioma) {
                    case ESPANOL:
                        idioma = "es";
                        break;
                    case INGLES:
                        idioma = "en";
                        break;
                    case ALEMAN:
                        idioma = "de";
                        break;
                    case FRANCES:
                        idioma = "fr";
                        break;
                    case COREANO:
                        idioma = "ko";
                        break;
                }

                Intent intent = new Intent(getApplicationContext(), FilterMoviesActivity.class);
                intent.putExtra("idioma", idioma);
                startActivity(intent);
            }
        });
    }

    private ArrayList<String> getLanguagesList() {
        ArrayList<String> languagesList = new ArrayList<>();
        languagesList.add(ESPANOL);
        languagesList.add(INGLES);
        languagesList.add(ALEMAN);
        languagesList.add(FRANCES);
        languagesList.add(COREANO);
        return languagesList;
    }

}
