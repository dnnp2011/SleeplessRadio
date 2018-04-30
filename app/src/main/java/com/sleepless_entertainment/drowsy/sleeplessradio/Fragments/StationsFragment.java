package com.sleepless_entertainment.drowsy.sleeplessradio.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sleepless_entertainment.drowsy.sleeplessradio.Adapters.StationsAdapter;
import com.sleepless_entertainment.drowsy.sleeplessradio.R;
import com.sleepless_entertainment.drowsy.sleeplessradio.Model.Station.StationType;
import com.sleepless_entertainment.drowsy.sleeplessradio.Services.DataService;

public class StationsFragment extends Fragment {


    public static final String ARG_STATION_TYPE = "StationTypeParam";

    private StationType stationType;


    public StationsFragment() {
        // Required empty public constructor
    }

    public static StationsFragment newInstance(StationType stationType) {
        StationsFragment fragment = new StationsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_STATION_TYPE, stationType.toInt());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            stationType = StationType.fromInt(getArguments().getInt(ARG_STATION_TYPE, -1));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stations, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.stations_recycler_view);
        recyclerView.setHasFixedSize(true);

        StationsAdapter adapter = null;

        switch (stationType) {
            case FEATURED:
                adapter = new StationsAdapter(DataService.getInstance().getFeaturedStations());
                break;
            case RECENT:
                adapter = new StationsAdapter(DataService.getInstance().getRecentStations());
                break;
            case PARTY:
                adapter = new StationsAdapter(DataService.getInstance().getPartyPlaylist());
                break;
        }

        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }

}
