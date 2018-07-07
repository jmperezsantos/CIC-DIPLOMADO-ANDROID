package cic.ipn.mx.collectionsexample.model;

import java.io.Serializable;

public class SongModel implements Serializable {

    private String title;
    private String artist;
    private String duration;

    public SongModel() {
    }

    public SongModel(String title, String artist, String duration) {
        this.title = title;
        this.artist = artist;
        this.duration = duration;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return this.artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getDuration() {
        return this.duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "SongModel{" +
                "title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}
