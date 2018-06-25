package com.example.thodzic.movies;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    private static final String TAG = "GalleryActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details_layout);
        Log.d(TAG, "onCreate: started.");

        getIncomingIntent();
    }

    private void getIncomingIntent() {
        if (getIntent().hasExtra("movie_title") && getIntent().hasExtra("poster")
                && getIntent().hasExtra("release_date") && getIntent().hasExtra("vote_average")
                && getIntent().hasExtra("plot")) {

            String movieTitle = getIntent().getStringExtra("movie_title");
            String poster = getIntent().getStringExtra("poster");
            String releaseDate = getIntent().getStringExtra("release_date");
            String voteAverage = getIntent().getStringExtra("vote_average");
            String plot = getIntent().getStringExtra("plot");

            setPoster(poster, movieTitle, releaseDate, voteAverage, plot);
        }
    }

    private void setPoster(String imageUrl, String movieTitle, String releaseDate, String voteAverage,
                           String plot) {
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
