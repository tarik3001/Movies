package com.example.thodzic.movies;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;


import org.json.JSONException;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MovieAdapter mMovieAdapter;
    String SEARCH_TERM = "popular";

    /*
    API KEY
    https://api.themoviedb.org/3/movie/550?api_key=1f5029b7d824dee72f4d4a156dac90ed
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        mMovieAdapter = new MovieAdapter();

        mRecyclerView.setAdapter(mMovieAdapter);

        loadMovieData();
    }

    private void showMovieDataView(){
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void loadMovieData() {
        new FetchMovieTask().execute(SEARCH_TERM);
    }

    public class FetchMovieTask extends AsyncTask<String, Void, String[]> {

        @Override
        protected String[] doInBackground(String... strings) {

            URL moviesUrl = NetworkUtils.buildUrl(SEARCH_TERM);
            try {
                String jsonMoviesResponse = NetworkUtils.getReponseFromHttpUrl(moviesUrl);

                String[] movieData = MoviesJsonUtils.getMovieData(MainActivity.this, jsonMoviesResponse);

                return movieData;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String[] movieData) {
            if (movieData != null) {
                showMovieDataView();
                    mMovieAdapter.setMovieData(movieData);
                }
            }
        }
    }
