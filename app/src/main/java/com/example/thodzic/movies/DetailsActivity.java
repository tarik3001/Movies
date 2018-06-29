package com.example.thodzic.movies;

import android.content.Intent;
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

        Intent intent = getIntent();
        Movie movie = intent.getParcelableExtra("movie");

        String image = movie.getMoviePoster();
        String title = movie.getTitle();
        String releaseDate = movie.getDate();
        String voteAverage = movie.getVoteAverage();
        String plot = movie.getPlot();

        ImageView poster = findViewById(R.id.details_image);
        Picasso.with(this).load(image).into(poster);

        TextView name = findViewById(R.id.details_title);
        name.setText((getResources().getString(R.string.movie_title)) + " " + title);

        TextView dateRelease = findViewById(R.id.details_release_date);
        dateRelease.setText((getResources().getString(R.string.release_date)) + " " + releaseDate);

        TextView averageVote = findViewById(R.id.details_voter_average);
        averageVote.setText((getResources().getString(R.string.vote_average)) + " " + voteAverage);

        TextView moviePlot = findViewById(R.id.details_plot);
        moviePlot.setText((getResources().getString(R.string.plot)) + " " + plot);

    }
}
