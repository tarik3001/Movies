package com.example.thodzic.movies;

public class Movie {

    private String Title;
    private String Date;
    private String MoviePoster;

    public Movie(String title, String date, String moviePoster) {
        Title = title;
        Date = date;
        MoviePoster = moviePoster;
    }

    public Movie() {
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getMoviePoster() {
        return MoviePoster;
    }

    public void setMoviePoster(String moviePoster) {
        MoviePoster = moviePoster;
    }
}
