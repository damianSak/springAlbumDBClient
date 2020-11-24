package org.melon.albumdbclient.model;

public class Album {

    private String band;
    private String title;
    private String genre;
    private int releaseYear;



    public Album(String band, String title, String genre, int releaseYear) {
        this.band = band;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
    }

    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }
}
