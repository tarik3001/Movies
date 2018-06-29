package com.example.thodzic.movies;

//This class represents a single data item

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {

    //Create the fields we need for this class
    private String Title;
    private String Date;
    private String MoviePoster;
    private String VoteAverage;
    private String Plot;

    //Generate the class constructor (Command + N shortcut)
    public Movie(String title, String date, String moviePoster, String voteAverage, String plot) {
        Title = title;
        Date = date;
        MoviePoster = moviePoster;
        VoteAverage = voteAverage;
        Plot = plot;
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    //New constructor which gets the parcelable in.
    protected Movie(Parcel in) {
        Title = in.readString();
        Date = in.readString();
        MoviePoster = in.readString();
        VoteAverage = in.readString();
        Plot = in.readString();
    }

    //Getters allow us to get the information we want from the list.
    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDate() {
        return Date;
    }

    //Setters allow us to set the information from the list into the item
    public void setDate(String date) {
        Date = date;
    }

    public String getVoteAverage() {
        return VoteAverage;
    }

    public void setVoteAverage(String voteAverage) {
        VoteAverage = voteAverage;
    }

    public String getPlot() {
        return Plot;
    }

    public void setPlot(String plot) {
        Plot = plot;
    }

    public String getMoviePoster() {
        return MoviePoster;
    }

    public void setMoviePoster(String moviePoster) {
        MoviePoster = moviePoster;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Title);
        dest.writeString(Date);
        dest.writeString(MoviePoster);
        dest.writeString(VoteAverage);
        dest.writeString(Plot);
    }
}
