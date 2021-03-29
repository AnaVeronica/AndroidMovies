package com.svalero.movies.movies.lstMovies.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

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
    private String[] listaSpinner = {"Filtrar por idioma original:", ESPANOL, INGLES, ALEMAN, FRANCES, COREANO};

    private View layoutError;
    private TextView textViewError;
    private Button buttonRetry;
    private ProgressBar progressBarLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lst_movies);

        cargarSpinner();

        // Obtener el Recycler y el layout y sus componentes en caso de error
        recycler = findViewById(R.id.recyclerMovies);
        layoutError = findViewById(R.id.activity_lst_movies_layout_error);
        textViewError = findViewById(R.id.activity_lst_movies_txt_error);
        buttonRetry = findViewById(R.id.activity_lst_movies_button_retry);
        progressBarLoading = findViewById(R.id.activity_lst_movies_progressbar_loading);

        progressBarLoading.setVisibility(View.VISIBLE);


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


    /**
     * Método que carga el spinner de valores previamente indicados
     */
    public void cargarSpinner() {
        Spinner spinnerFiltro = findViewById(R.id.spinnerFiltro);
        ArrayAdapter<String> adapterFiltro = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, listaSpinner);
        spinnerFiltro.setAdapter(adapterFiltro);
        spinnerFiltro.setSelected(false);
        spinnerFiltro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            /**
             * Captura el elemento seleccionado del spinner
             * @param parent
             * @param view
             * @param position
             * @param id
             */
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String idioma = parent.getItemAtPosition(position).toString();

                if(idioma.equals("Filtrar por idioma original:"))
                    return;
                if(idioma.equals(ESPANOL)) {
                    idioma = "es";
                }
                if(idioma.equals(INGLES)) {
                    idioma = "en";
                }
                if(idioma.equals(ALEMAN)) {
                    idioma = "de";
                }
                if(idioma.equals(FRANCES)) {
                    idioma = "fr";
                }
                if(idioma.equals(COREANO)) {
                    idioma = "ko";
                }

                Intent intent = new Intent(parent.getContext(), FilterMoviesActivity.class);
                intent.putExtra("idioma", idioma);
                parent.getContext().startActivity(intent);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
