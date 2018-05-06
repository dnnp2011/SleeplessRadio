package com.sleepless_entertainment.drowsy.sleeplessradio.Services;

import android.media.MediaPlayer;
import android.view.View;

import com.sleepless_entertainment.drowsy.sleeplessradio.Activities.MainActivity;
import com.sleepless_entertainment.drowsy.sleeplessradio.Fragments.MediaBarFragment;
import com.sleepless_entertainment.drowsy.sleeplessradio.Model.Song;

public class MusicPlayer {

    private static final MusicPlayer instance = new MusicPlayer();

    private MediaPlayer mediaPlayer;
    private Song lastSong = null;
    private Song currentSong = null;

    public OnMusicPlayerInteractionListener mCallback;

    //region Instantiation
    private MusicPlayer() {
        mediaPlayer = new MediaPlayer();
    }

    public void setListenerRef(OnMusicPlayerInteractionListener listenerRef) {
        mCallback = listenerRef;
    }


    public static MusicPlayer getInstance() {
        return instance;
    }
    //endregion

    public Song getLastSong() {
        return lastSong;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public Song getCurrentSong() {
        return currentSong;
    }

    public boolean isSongPlaying() {
        return mediaPlayer.isPlaying();
    }

    public void playSong(Song song) {
        //stop lastSong, reset lastSong play btn, start new song
        //Trigger change event in mediaBar
        if (currentSong != null) {
            if (currentSong.equals(song)) {
                mediaPlayer.start();
                alternatePlayPause(currentSong);
                return;
            }
            stopSong();
        }
        mediaPlayer = MediaPlayer.create(MainActivity.getMainActivity().getApplicationContext(), song.getSongURI());
        mediaPlayer.start();
        currentSong = song;
        mCallback.OnMusicPlayerInteraction(currentSong);
        alternatePlayPause(currentSong);
    }

    public void pauseSong() {
        //pause currentSong, reset play btn
        //Trigger change event in mediaBar
        //Change pause/play buttons
        mediaPlayer.pause();
        alternatePlayPause(currentSong);
    }

    public void stopSong() {
        //Trigger mediaBar close
        mediaPlayer.stop();
        lastSong = currentSong;
        currentSong = null;
        mediaPlayer.reset();
        mediaPlayer.release();
        alternatePlayPause(lastSong);
    }

    public void prepareSong() {
        //prepare song async
    }

    public void destroy() {
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
    }

    private void alternatePlayPause(Song song) {
        if (song.getPlayButton().getVisibility() == View.VISIBLE) {
            song.getPlayButton().setVisibility(View.GONE);
            song.getPauseButton().setVisibility(View.VISIBLE);
        }
        else {
            song.getPlayButton().setVisibility(View.VISIBLE);
            song.getPauseButton().setVisibility(View.GONE);
        }
    }

    public enum MediaCommand  {
        PLAY, PAUSE
    }

    public interface OnMusicPlayerInteractionListener {
        public void OnMusicPlayerInteraction(Song song);
    }
}
