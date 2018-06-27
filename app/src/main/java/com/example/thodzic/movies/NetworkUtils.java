package com.example.thodzic.movies;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

//Build the class which will build the URL and talk to the database.
public class NetworkUtils {

    //These utilities will be used to communicate with the servers.
    private static final String TAG = NetworkUtils.class.getSimpleName();

    private static final String BASE_URL = "https://api.themoviedb.org/3/discover/movie";
    private static final String API_KEY = " ";

    //This builds the URL used to talk to movie database.
    public static URL buildUrl(String SEARCH_TERM) {
        Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter("api_key",API_KEY)
                .appendQueryParameter("sort_by", SEARCH_TERM)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Built URL " + url);

        return url;
    }

    //This method returns the entire result from the HTTP response.
    public static String getReponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream inputStream = urlConnection.getInputStream();

            Scanner scanner = new Scanner(inputStream);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();

        }
    }
}
