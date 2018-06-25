package com.example.thodzic.movies;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

//1. Add internet permission in the android manifest file.

public class MainActivity extends AppCompatActivity {

    //5. Declare some of the objects we are using.
    String SEARCH_TERM = "popular";
    private RecyclerView mRecyclerView;

    //6. GridLayoutManage will allow us to load our items in a grid.
    private GridLayoutManager gridLayoutManager;

    //7. Custoj Adapter lets us bind out data from the web server with our recylerview
    private MovieAdapter mMovieAdapter;

    //8. Need a list to store the data from the server.
    private List<Movie> movieData;

    /*
    API KEY
    https://api.themoviedb.org/3/movie/550?api_key=1f5029b7d824dee72f4d4a156dac90ed
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //14.  Reference the RecyclerView
        mRecyclerView = findViewById(R.id.recycler_view);

        //15.  Reference the list.  This needs to be done before setting the adapter to the recycler
        //view or the app will think there is an empty list.
        movieData = new ArrayList<>();

        //16.  To update the list with items, we create a new method to do that.
        loadMovieData();

        //21.Create a new grid layout manager in order to display data to a grid.
        gridLayoutManager = new GridLayoutManager(this, 3);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        //22.Bind the data we receive from the web server to the recyclerview itself.
        mMovieAdapter = new MovieAdapter(this, movieData);

        //23.  Apply the adapter to the recyclerview.
        mRecyclerView.setAdapter(mMovieAdapter);

    }

    //17.  Tell the new method to get the dat abased on the search term within the url.
    private void loadMovieData() {
        new FetchMovieTask().execute(SEARCH_TERM);
    }

    //18.  We need to use an AsyncTask to perform the request to get the data.  The first argument
    //we use a String because this will allow us to pass the url.
    public class FetchMovieTask extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... strings) {

            final String MOVIES_RESULTS = "results";
            final String MOVIES_POSTER_IMAGE = "poster_path";
            final String MOVIES_TITLE = "title";
            final String RELEASE_DATE = "release_date";
            final String VOTE_AVERAGE = "vote_average";
            final String PLOT = "overview";

            //19.  Create the network request to download the JSON data from the url database.
            URL moviesUrl = NetworkUtils.buildUrl(SEARCH_TERM);
            try {
                //The response we get is in the form of JSON.
                String jsonMoviesResponse = NetworkUtils.getReponseFromHttpUrl(moviesUrl);

                //A new JSON object created from the JSON response.
                JSONObject moviesJson = new JSONObject(jsonMoviesResponse);

                //Read the movie results array from the JSON object.
                JSONArray moviesArray = moviesJson.getJSONArray(MOVIES_RESULTS);

                //A loop is created to read the array and add the data we need to a list.
                for (int i = 0; i < moviesArray.length(); i++) {

                    String moviePoster;
                    String movieTitle;
                    String movieReleaseDate;
                    String voteAverage;
                    String plot;
                    JSONObject movie = moviesArray.getJSONObject(i);

                    moviePoster = ("http://image.tmdb.org/t/p/w185/" + movie.getString(MOVIES_POSTER_IMAGE));
                    movieTitle = movie.getString(MOVIES_TITLE);
                    movieReleaseDate = movie.getString(RELEASE_DATE);
                    voteAverage = movie.getString(VOTE_AVERAGE);
                    plot = movie.getString(PLOT);

                    Movie data = new Movie(movieTitle, movieReleaseDate, moviePoster, voteAverage, plot);

                    //Add the data items to our movieData list.
                    movieData.add(data);

                }


            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        //35.  This is called when the network request is done.  We use this method to tell our
        //custom adapter that there is a change in the data list so that it can load new cardview
        //widgets in the list.
        @Override
        protected void onPostExecute(Void aVoid) {
            mMovieAdapter.notifyDataSetChanged();
        }
    }
};
