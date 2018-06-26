package com.example.thodzic.movies;

//This class represents a single data item

public class Movie {

    //10.  Crrate the fields we need for this class
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
}
