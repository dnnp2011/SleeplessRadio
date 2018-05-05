package com.sleepless_entertainment.drowsy.sleeplessradio.Model;

public class Song {

    private final String RAW = "raw/";
    private String Name;
    private String Description;
    private Station.StationGenre SongGenre;
    private int SongURI;

    public Song(String name, String description, Station.StationGenre songGenre, int songURI) {
        Name = name;
        Description = description;
        SongGenre = songGenre;
        SongURI = songURI;
    }

    public String getName() {
        return Name;
    }

    public String getDescription() {
        return Description;
    }

    public Station.StationGenre getSongGenre() {
        return SongGenre;
    }

    public int getSongURI(){
        return this.SongURI;
    }
}
