package com.sleepless_entertainment.drowsy.sleeplessradio.Adapters;

import com.sleepless_entertainment.drowsy.sleeplessradio.Model.Song;

public class MediaBarAdapter {

//    Able to perform same functions for pausing and unpausing as the MusicPlayer
//    Receive events from MusicPlayer when certain actions are used from SongCard
//    Displays song info

    private Song activeSong;
    private boolean paused;

    public MediaBarAdapter() {
        activeSong = null;
        paused = false;
    }

    public void startSong(Song song) {
        //Incoming signal from MusicPlayer
        //Display MediaBar if not in view
        //
    }

    public void pauseSong() {
        //Called from MediaBar or from MusicPlayer
    }

    public void unpauseSong() {
        //Called from MediaBar, signal to MusicPlayer
    }

}
