package com.sleepless_entertainment.drowsy.sleeplessradio.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.sleepless_entertainment.drowsy.sleeplessradio.Holders.SongsViewHolder;
import com.sleepless_entertainment.drowsy.sleeplessradio.Model.Song;
import com.sleepless_entertainment.drowsy.sleeplessradio.R;
import com.sleepless_entertainment.drowsy.sleeplessradio.Services.MusicPlayer;

import java.util.ArrayList;

public class SongsAdapter extends RecyclerView.Adapter<SongsViewHolder> {

    private ArrayList<Song> songs;

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

        song.setPlayButton((ImageButton) holder.itemView.findViewById(R.id.songPlayBtn));
        song.setPauseButton((ImageButton) holder.itemView.findViewById(R.id.songPauseBtn));


        song.getPlayButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MusicPlayer.getInstance().playSong(song);
            }
        });

        song.getPauseButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MusicPlayer.getInstance().pauseSong();
            }
        });

        holder.updateUI(song);
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public void stopAllSongs() {
        //reset all song play btns
        //Trigger change event in mediaBar
    }
}
