package com.sleepless_entertainment.drowsy.sleeplessradio.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sleepless_entertainment.drowsy.sleeplessradio.Adapters.SongsAdapter;
import com.sleepless_entertainment.drowsy.sleeplessradio.Model.Station.StationGenre;
import com.sleepless_entertainment.drowsy.sleeplessradio.R;
import com.sleepless_entertainment.drowsy.sleeplessradio.Services.DataService;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SongsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SongsFragment extends Fragment {
    private static final String ARG_SONG_GENRE = "param1";

    // TODO: Rename and change types of parameters
    private StationGenre songGenre;


    public SongsFragment() {
        // Required empty public constructor
    }


    public static SongsFragment newInstance(StationGenre genre) {
        SongsFragment fragment = new SongsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SONG_GENRE, genre.toString());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            songGenre = StationGenre.fromString(getArguments().getString(ARG_SONG_GENRE));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_songs, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.stations_recycler_view);
        recyclerView.setHasFixedSize(true);

        SongsAdapter adapter = new SongsAdapter(DataService.getInstance().getGenreSongs(songGenre));
        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }

}
