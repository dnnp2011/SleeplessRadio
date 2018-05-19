package com.sleepless_entertainment.drowsy.sleeplessradio.Services;

import android.media.MediaPlayer;
import android.view.View;

import com.sleepless_entertainment.drowsy.sleeplessradio.Activities.MainActivity;
import com.sleepless_entertainment.drowsy.sleeplessradio.Fragments.MediaBarFragment;
import com.sleepless_entertainment.drowsy.sleeplessradio.Model.Song;
import com.sleepless_entertainment.drowsy.sleeplessradio.R;

import java.util.ArrayList;
import java.util.EventListener;

public class MusicPlayer {

    private MediaPlayer mediaPlayer;
    private Song lastSong;
    private Song currentSong;

    private ArrayList<OnMusicPlayerInteractionListener> listeners;

//    public OnMusicPlayerInteractionListener mCallback;

    public MusicPlayer() {
        mediaPlayer = new MediaPlayer();
        listeners = new ArrayList<>();
    }

    /**
     * Functionality:
     * Store Current Song
     * Play New Song
     * Pause Song
     * Stop Song
     * Call Interaction Listeners(Play, Pause, Stop)
     * Destroy MediaPlayer Instance onStop
     */

    public Song getCurrentSong() {
        return currentSong;
    }

    public void setCallbackListener(OnMusicPlayerInteractionListener callbackTarget) {
        listeners.add(callbackTarget);
    }

    public void removeCallbackListener(OnMusicPlayerInteractionListener callbackTarget) {
        listeners.remove(callbackTarget);
    }

    public void playSong(Song song) {
        // Case 2: Playing a different song that is currently playing -> mediaPlayer is playing / song != currentSong
        // Case 1: First time playing a song -> mediaPlayer not playing / current song == null
        // Case 3: Current song is paused, and is being unpaused -> mediaPlayer not playing / song == currentSong
        // Case 4: Current song is paused, and a new song is being played instead -> mediaPlayer not playing / song != currentSong

        if (isSongCurrentlyPlaying()) {
            if (song != currentSong) {
//                User hit play on a song while another song is currently playing -> Stop previous song, Play new song
                stopSong(currentSong);
                playNewSong(song);
            }
            else {
//                User hit player button on song that's already playing -> No effect
            }
        }
        else {
            if (currentSong == null) {
//                User hit play on a song for the first time since launching app -> Initialize player, play song
                playNewSong(song);
            }
            else if (song != currentSong) {
//                User hit play on another song while the current song was paused -> Just play new song
                playNewSong(song);
            }
            else {
//                User hit play on the current song that is currently paused -> continue playing current song
                unpauseSong(song);
            }
        }
    }

    public void unpauseSong(Song song) {
        if (currentSong != song)
            System.out.println("Error-> MusicPlayer.unpause(song) input song does not match currentSong stored in memory");
        mediaPlayer.start();
        if (!listeners.isEmpty())
            listeners.forEach((listener) -> listener.OnMusicPlayerPlaySong(song));
    }

    public void playNewSong(Song song) {
        if (mediaPlayer.isPlaying() || currentSong != null)
            stopSong(song);
        else {
            if (!MainActivity.getMainActivity().mediaBarFragmentLoaded())
                MainActivity.getMainActivity().loadMediaBarFragment();
        }
        mediaPlayer = MediaPlayer.create(MainActivity.getMainActivity().getApplicationContext(), song.getSongURI());
        mediaPlayer.start();
        currentSong = song;
        if (!listeners.isEmpty())
            listeners.forEach((listener) -> listener.OnMusicPlayerPlaySong(song));
    }

    public boolean isSongCurrentlyPlaying() {
        return mediaPlayer.isPlaying();
    }

    public void pauseSong(Song song) {
        if (currentSong != song)
            System.out.println("Error-> MusicPlayer.pauseSong(song) input song does not match currentSong stored in memory");
        mediaPlayer.pause();
        if (!listeners.isEmpty())
            listeners.forEach((listener) -> listener.OnMusicPlayerPauseSong(song));
    }

    public void stopSong(Song song) {
        if (currentSong != song)
            System.out.println("Error-> MusicPlayer.stopSong(song) input song does not match currentSong stored in memory");
        mediaPlayer.stop();
        lastSong = currentSong;
        currentSong = null;
        mediaPlayer.reset();
        if (!listeners.isEmpty())
            listeners.forEach((listener) -> listener.OnMusicPlayerStopSong(song));
    }

    public void destroy() {
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
    }

    public interface OnMusicPlayerInteractionListener extends EventListener {
        public void OnMusicPlayerPlaySong(Song song);
        public void OnMusicPlayerPauseSong(Song song);
        public void OnMusicPlayerStopSong(Song song);
    }


//    public void setListenerRef(OnMusicPlayerInteractionListener listenerRef) {
//        mCallback = listenerRef;
//    }
//
//    public Song getLastSong() {
//        return lastSong;
//    }
//
//    public MediaPlayer getMediaPlayer() {
//        return mediaPlayer;
//    }
//
//    public Song getCurrentSong() {
//        return currentSong;
//    }
//
//    public boolean isPlayerActive() {
//        return mediaPlayer != null && currentSong != null;
//    }
//
//    public boolean isSongPlaying() {
//        return currentSong != null && mediaPlayer != null && mediaPlayer.isPlaying();
//    }
//
//    public void playSong(Song song) {
//        //stop lastSong, reset lastSong play btn, start new song
//        //Trigger change event in mediaBar
//        if (currentSong != null) {
//            if (currentSong.equals(song)) {
//                mediaPlayer.start();
//                alternatePlayPause(song);
//                return;
//            }
//            stopSong();
//        }
//
//        mediaPlayer = MediaPlayer.create(MainActivity.getMainActivity().getApplicationContext(), song.getSongURI());
//        mediaPlayer.start();
//        currentSong = song;
//
//        MainActivity.getMainActivity().loadMediaBarFragment();
//
//        if (mCallback != null)
//            mCallback.OnMusicPlayerInteraction(song);
//
//        alternatePlayPause(song);
//    }
//
//    public void pauseSong() {
//        //pause currentSong, reset play btn
//        //Trigger change event in mediaBar
//        //Change pause/play buttons
//        mediaPlayer.pause();
//        alternatePlayPause(currentSong);
//    }
//
//    public void stopSong() {
//        //Trigger mediaBar close
//        mediaPlayer.stop();
//        lastSong = currentSong;
//        currentSong = null;
//        mediaPlayer.reset();
//        mediaPlayer.release();
//        alternatePlayPause(lastSong);
//    }
//
//    public void prepareSong() {
//        //prepare song async
//    }
//
//
//    private void alternatePlayPause(Song song) {
//        if (song.getPlayButton().getVisibility() == View.VISIBLE) {
//            song.getPlayButton().setVisibility(View.GONE);
//            song.getPauseButton().setVisibility(View.VISIBLE);
//        }
//        else {
//            song.getPlayButton().setVisibility(View.VISIBLE);
//            song.getPauseButton().setVisibility(View.GONE);
//        }
//    }
//
//    public enum MediaCommand  {
//        PLAY, PAUSE
//    }
//
//    public interface OnMusicPlayerInteractionListener {
//        public void OnMusicPlayerInteraction(Song song);
//    }
}
