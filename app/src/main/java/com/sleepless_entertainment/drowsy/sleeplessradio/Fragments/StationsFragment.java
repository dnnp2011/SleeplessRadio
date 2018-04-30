package com.sleepless_entertainment.drowsy.sleeplessradio.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sleepless_entertainment.drowsy.sleeplessradio.Adapters.StationsAdapter;
import com.sleepless_entertainment.drowsy.sleeplessradio.R;

public class StationsFragment extends Fragment {

    public static final int STATION_TYPE_FEATURED = 0;
    public static final int STATION_TYPE_RECENT = 1;
    public static final int STATION_TYPE_PARTY = 2;

    public static final String ARG_STATION_TYPE = "StationTypeParam";

    public enum StationType {
        FEATURED, RECENT, PARTY;

        public int toInt() {
            if (this == FEATURED)
                return 0;
            else if (this == RECENT)
                return 1;
            else if (this == PARTY)
                return 2;
            else
                return -1;
        }

        public static StationType fromInt(int x) {
            switch (x) {
                case 0:
                    return  FEATURED;
                case 1:
                    return RECENT;
                case 2:
                    return  PARTY;
                default:
                    return null;
            }
        }
    }

    private StationType stationType;


    public StationsFragment() {
        // Required empty public constructor
        newInstance(StationType.FEATURED.toInt());
    }

    public static StationsFragment newInstance(int stationType) {
        StationsFragment fragment = new StationsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_STATION_TYPE, stationType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            stationType = StationType.fromInt(getArguments().getInt(ARG_STATION_TYPE));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stations, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.stations_recycler_view);
        recyclerView.setHasFixedSize(true);

        StationsAdapter adapter = new StationsAdapter();
        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }

}
