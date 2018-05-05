package com.sleepless_entertainment.drowsy.sleeplessradio.Adapters;

import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.sleepless_entertainment.drowsy.sleeplessradio.Activities.MainActivity;
import com.sleepless_entertainment.drowsy.sleeplessradio.Holders.SongsViewHolder;
import com.sleepless_entertainment.drowsy.sleeplessradio.Model.Song;
import com.sleepless_entertainment.drowsy.sleeplessradio.R;

import java.util.ArrayList;

public class SongsAdapter extends RecyclerView.Adapter<SongsViewHolder> {

    private ArrayList<Song> songs;

    public static MediaPlayer mediaPlayer;

    public SongsAdapter(ArrayList<Song> songs) {
        this.songs = songs;
    }

    @NonNull
    @Override
    public SongsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View songCard = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_song, parent, false);
        return new SongsViewHolder(songCard);
    }

    @Override
    public void onBindViewHolder(@NonNull final SongsViewHolder holder, int position) {
        final Song song = songs.get(position);

        final ImageButton playButton = holder.itemView.findViewById(R.id.songPlayBtn);
        final ImageButton pauseButton = holder.itemView.findViewById(R.id.songPauseBtn);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                TODO: Setup PLAY functionality
//                MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.getMainActivity().getApplicationContext(), )
            playButton.setVisibility(View.GONE);
            pauseButton.setVisibility(View.VISIBLE);

            mediaPlayer = MediaPlayer.create(MainActivity.getMainActivity().getApplicationContext(), song.getSongURI());

            mediaPlayer.start();
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                TODO: Setup PAUSE functionality
                playButton.setVisibility(View.VISIBLE);
                pauseButton.setVisibility(View.GONE);

                if (mediaPlayer != null && mediaPlayer.isPlaying())
                    mediaPlayer.stop();
                mediaPlayer.reset();
            }
        });

        holder.updateUI(song);
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

}
