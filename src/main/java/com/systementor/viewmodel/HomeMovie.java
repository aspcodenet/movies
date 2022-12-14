package com.systementor.viewmodel;

public class HomeMovie {
    private String title;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    private String director;
    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }


    private int year;
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


    private String genreName;

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public HomeMovie(String title, String director, int year, String genreName ) {
        super();
        this.title = title;
        this.director = director;
        this.year = year;
        this.genreName = genreName;
    }
}
