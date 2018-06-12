package com.example.thodzic.movies;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MoviesJsonUtils {

    public static String[] getMovieData(Context context, String moviesJsonStr)
            throws JSONException {
        final String MOVIES_RESULTS = "results";
        final String MOVIES_POSTER_IMAGE = "poster_path";
        final String MOVIES_TITLE = "title";
        final String RELEASE_DATE = "release_date";

        String [] parsedMoviesData = null;

        JSONObject moviesJson = new JSONObject(moviesJsonStr);

        JSONArray moviesArray = moviesJson.getJSONArray(MOVIES_RESULTS);

        parsedMoviesData = new String[moviesArray.length()];

        for (int i = 0; i < moviesArray.length(); i++){

            String moviePoster;
            String movieTitle;
            String movieReleaseDate;
            JSONObject movie = moviesArray.getJSONObject(i);

            moviePoster = ("http://image.tmdb.org/t/p/w185/" + movie.getString(MOVIES_POSTER_IMAGE));

            parsedMoviesData[i] = moviePoster;
        }
        return parsedMoviesData;
    }
}
