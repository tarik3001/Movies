package com.example.thodzic.movies;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String SEARCH_TERM = "popular";
    private RecyclerView mRecyclerView;
    private MovieAdapter mMovieAdapter;
    private GridLayoutManager gridLayoutManager;
    private List<Movie> movieData;

    /*
    API KEY
    https://api.themoviedb.org/3/movie/550?api_key=1f5029b7d824dee72f4d4a156dac90ed
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recycler_view);

        gridLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setHasFixedSize(true);

        mMovieAdapter = new MovieAdapter(this, movieData);

        mRecyclerView.setAdapter(mMovieAdapter);

        movieData = new ArrayList<>();

        loadMovieData();
    }


    private void loadMovieData() {
        new FetchMovieTask().execute(SEARCH_TERM);
    }

    public class FetchMovieTask extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... strings) {

            final String MOVIES_RESULTS = "results";
            final String MOVIES_POSTER_IMAGE = "poster_path";
            final String MOVIES_TITLE = "title";
            final String RELEASE_DATE = "release_date";
            URL moviesUrl = NetworkUtils.buildUrl(SEARCH_TERM);
            try {
                String jsonMoviesResponse = NetworkUtils.getReponseFromHttpUrl(moviesUrl);

                JSONObject moviesJson = new JSONObject(jsonMoviesResponse);

                JSONArray moviesArray = moviesJson.getJSONArray(MOVIES_RESULTS);

                for (int i = 0; i < moviesArray.length(); i++) {

                    String moviePoster;
                    String movieTitle;
                    String movieReleaseDate;
                    JSONObject movie = moviesArray.getJSONObject(i);

                    moviePoster = ("http://image.tmdb.org/t/p/w185/" + movie.getString(MOVIES_POSTER_IMAGE));
                    movieTitle = movie.getString(MOVIES_TITLE);
                    movieReleaseDate = movie.getString(RELEASE_DATE);

                    Log.i("MoviteTitle", movieTitle);
                    Log.i("ReleaseDate", movieReleaseDate);
                    Log.i("Image", moviePoster);

                    Movie data = new Movie(movieTitle, movieReleaseDate, moviePoster);

                    movieData.add(data);


                }


            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            mMovieAdapter.notifyDataSetChanged();
        }
    }
};
