package com.sleepless_entertainment.drowsy.sleeplessradio.Model;

import android.widget.ImageButton;

public class Song {

    private String RAW = "raw/";
    private String name;
    private String description;
    private Station.StationGenre SongGenre;
    private int songURI;
    private boolean isPlaying;
    private ImageButton playButton;
    private ImageButton pauseButton;

    public Song(String name, String description, Station.StationGenre songGenre, int songURI) {
        this.name = name;
        this.description = description;
        SongGenre = songGenre;
        this.songURI = songURI;
        isPlaying = false;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Station.StationGenre getSongGenre() {
        return SongGenre;
    }

    public int getSongURI(){
        return this.songURI;
    }

    public boolean isPlaying() {
        return this.isPlaying;
    }

    public void setPlayButton(ImageButton playButton) {
        this.playButton = playButton;
    }

    public void setPauseButton(ImageButton pauseButton) {
        this.pauseButton = pauseButton;
    }

    public ImageButton getPlayButton() {
        return (ImageButton) playButton;
    }

    public ImageButton getPauseButton() {
        return (ImageButton) pauseButton;
    }
}
