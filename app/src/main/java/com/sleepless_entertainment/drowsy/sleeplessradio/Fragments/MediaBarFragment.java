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

public class MediaBarFragment extends Fragment implements View.OnClickListener, MusicPlayer.OnMusicPlayerInteractionListener {

//    TODO: Take input from mediaBar, update mediaPlayer
//    TODO: Update based on event from mediaPlayer

    private static final String ARG_PARAM = "argParam";

    private ImageButton playButton, pauseButton;
    private TextView songName, songDescr;
    private Song currentSong;
    private String mString;

    public MediaBarFragment() {
        // Required empty public constructor
    }

    public static MediaBarFragment newInstance(String data) {
        MediaBarFragment fragment = new MediaBarFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM, data);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mString = getArguments().getString(ARG_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_media_bar, container, false);

        playButton = view.findViewById(R.id.playButton);
        pauseButton = view.findViewById(R.id.pauseButton);
        songName = view.findViewById(R.id.songNameView);
        songDescr = view.findViewById(R.id.songDescrView);

        playButton.setOnClickListener(this);
        pauseButton.setOnClickListener(this);
        playButton.setAlpha(0.4f);
        pauseButton.setAlpha(0.4f);

        setSongInfo();

        return view;
    }

    private void setSongInfo() {
        songName.setText(currentSong.getName());
        songDescr.setText(currentSong.getDescription());
    }

    // TODO: Rename method, update argument and hook method into UI
    @Override
    public void onClick(View view) {
        MusicPlayer musicPlayer = MainActivity.getMainActivity().musicPlayer;
        Song currentSong = musicPlayer.getCurrentSong();
        if (currentSong == null)
            return;

        ImageButton button = (ImageButton) view;
        if (button.equals(playButton)) {
            musicPlayer.playSong(currentSong);
        }
        else if (button.equals(pauseButton)) {
            musicPlayer.pauseSong(currentSong);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void OnMusicPlayerPlaySong(Song song) {
        currentSong = song;
        setSongInfo();
//        TODO: Implement these interface methods!
    }

    @Override
    public void OnMusicPlayerPauseSong(Song song) {

    }

    @Override
    public void OnMusicPlayerStopSong(Song song) {

    }
}
