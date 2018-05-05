package com.sleepless_entertainment.drowsy.sleeplessradio.Holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sleepless_entertainment.drowsy.sleeplessradio.Model.Song;
import com.sleepless_entertainment.drowsy.sleeplessradio.R;

public class SongsViewHolder extends RecyclerView.ViewHolder {

    private TextView songNameView;
    private TextView songDescrView;
    private ImageView genreImg1;
    private ImageView genreImg2;
    private ImageView genreImg3;

    public SongsViewHolder(View itemView) {
        super(itemView);

        songNameView = itemView.findViewById(R.id.songNameView);
        songDescrView = itemView.findViewById(R.id.songDescrView);
        genreImg1 = itemView.findViewById(R.id.genreImg1);
        genreImg2 = itemView.findViewById(R.id.genreImg2);
        genreImg3 = itemView.findViewById(R.id.genreImg3);
    }

    public void updateUI(Song song) {
        songNameView.setText(song.getName());
        songDescrView.setText(song.getDescription());

//        TODO: Replace genreImages
    }

}
