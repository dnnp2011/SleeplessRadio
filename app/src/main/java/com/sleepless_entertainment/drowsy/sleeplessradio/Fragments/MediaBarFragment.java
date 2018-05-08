package com.sleepless_entertainment.drowsy.sleeplessradio.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.sleepless_entertainment.drowsy.sleeplessradio.Activities.MainActivity;
import com.sleepless_entertainment.drowsy.sleeplessradio.Model.Song;
import com.sleepless_entertainment.drowsy.sleeplessradio.R;
import com.sleepless_entertainment.drowsy.sleeplessradio.Services.MusicPlayer;

import java.io.IOException;

import static com.sleepless_entertainment.drowsy.sleeplessradio.Services.MusicPlayer.*;


public class MediaBarFragment extends Fragment implements View.OnClickListener {

//    TODO: Take input from mediaBar, update mediaPlayer
//    TODO: Update based on event from mediaPlayer

    private static final String ARG_CURRENT_SONG = "argCurrentSong";

    private ImageButton playButton, pauseButton;
    private TextView songName, songDescr;

    // TODO: Rename and change types of parameters
    private Song mCurrentSong;

    private OnMediaBarFragmentInteractionListener mCallback;

    public MediaBarFragment() {
        // Required empty public constructor
    }

    public static MediaBarFragment newInstance(Song song) {
        MediaBarFragment fragment = new MediaBarFragment();
        Bundle args = new Bundle();
        try {
            String string = MainActivity.serializeToString(song);
            System.out.println(string);
            args.putString(ARG_CURRENT_SONG, string);
        } catch (IOException e) {
            e.printStackTrace();
        }
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            try {
                mCurrentSong = (Song) MainActivity.reconstituteFromString(getArguments().getString(ARG_CURRENT_SONG));
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
//        MusicPlayer.getInstance().setListenerRef(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_media_bar, container, false);

        playButton = view.findViewById(R.id.playButton);
        pauseButton = view.findViewById(R.id.pauseButton);
        songName = view.findViewById(R.id.songNameView);
        songDescr = view.findViewById(R.id.songDescrView);;

        playButton.setOnClickListener(this);
        pauseButton.setOnClickListener(this);
        playButton.setAlpha(0.4f);
        pauseButton.setAlpha(0.4f);

        setSongInfo();

        return view;
    }

    private void setSongInfo() {
        songName.setText(mCurrentSong.getName());
        songDescr.setText(mCurrentSong.getDescription());
    }

    // TODO: Rename method, update argument and hook method into UI
    @Override
    public void onClick(View view) {
        MusicPlayer player = MusicPlayer.getInstance();

        if (!player.isPlayerActive())
            return;

        ImageButton button = (ImageButton) view;
        if (button.equals(playButton)) {
            if (!player.isSongPlaying())
            {
                player.playSong(player.getCurrentSong());
                playButton.setAlpha(0.4f);
                pauseButton.setAlpha(1f);
            }
        }
        else if (button.equals(pauseButton)) {
            if (player.isSongPlaying())
            {
                player.pauseSong();
                pauseButton.setAlpha(0.4f);
                playButton.setAlpha(1f);
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnMediaBarFragmentInteractionListener) {
            mCallback = (OnMediaBarFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }

//    @Override
//    public void OnMusicPlayerInteraction(Song song) {
////        Event from MusicPlayer when a new song begins playing, set Song info
//        songName.setText(song.getName());
//        songDescr.setText(song.getDescription());
//        pauseButton.setAlpha(1f);
//    }

    public interface OnMediaBarFragmentInteractionListener {
        // TODO: Update argument type and name
        void onMediaBarFragmentInteraction(MediaCommand command);
    }
}
