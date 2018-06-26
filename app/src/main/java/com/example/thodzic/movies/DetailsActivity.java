package com.example.thodzic.movies;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

//Activity needs added to manifest.
public class DetailsActivity extends AppCompatActivity {

    //LOG tag for debugging
    private static final String TAG = "GalleryActivity";

    //Override on Create and set contentView to new activity_details layout.
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        //Log for debugging so we can tell if activity started successfully.
        Log.d(TAG, "onCreate: started.");

        getIncomingIntent();
    }

    //Retrieve our intent extras and set them to the views.
    private void getIncomingIntent() {
        Log.d(TAG, "Checking for incoming intents");
        //Need to make sure that the intent actually has extras or the app will crash.
        if (getIntent().hasExtra("movie_title") && getIntent().hasExtra("poster")
                && getIntent().hasExtra("release_date") && getIntent().hasExtra("vote_average")
                && getIntent().hasExtra("plot")) {
            Log.d(TAG, "Found intent extras.");

            String movieTitle = getIntent().getStringExtra("movie_title");
            String poster = getIntent().getStringExtra("poster");
            String releaseDate = getIntent().getStringExtra("release_date");
            String voteAverage = getIntent().getStringExtra("vote_average");
            String plot = getIntent().getStringExtra("plot");

            setData(poster, movieTitle, releaseDate, voteAverage, plot);
        }
    }

    private void setData(String imageUrl, String movieTitle, String releaseDate, String voteAverage,
                         String plot) {
        Log.d(TAG, "Setting the data to the views");

        ImageView poster = findViewById(R.id.details_image);
        Picasso.with(this).load(imageUrl).into(poster);

        TextView name = findViewById(R.id.details_title);
        name.setText(movieTitle);

        TextView dateRelease = findViewById(R.id.details_release_date);
        dateRelease.setText(releaseDate);

        TextView averageVote = findViewById(R.id.details_voter_average);
        averageVote.setText(voteAverage);

        TextView moviePlot = findViewById(R.id.details_plot);
        moviePlot.setText(plot);

    }
}
