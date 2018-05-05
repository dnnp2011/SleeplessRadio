package com.sleepless_entertainment.drowsy.sleeplessradio.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.sleepless_entertainment.drowsy.sleeplessradio.Activities.MainActivity;
import com.sleepless_entertainment.drowsy.sleeplessradio.Model.Station;
import com.sleepless_entertainment.drowsy.sleeplessradio.R;
import com.sleepless_entertainment.drowsy.sleeplessradio.Model.Station.StationGenre;

import java.io.IOException;
import java.net.URI;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailsFragment extends Fragment {

    private static final String ARG_PARAM1 = "stationParam";

    private Station station;
    private TextView stationNameView;
    private ImageView stationImgView;
    private FrameLayout infoBarLayout;


    public DetailsFragment() {
        // Required empty public constructor
    }

    public static DetailsFragment newInstance(Station station) throws IOException {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, MainActivity.serializeToString(station));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            try {
                station = (Station) MainActivity.reconstituteFromString(getArguments().getString(ARG_PARAM1));
            }
            catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        stationNameView = view.findViewById(R.id.stationNameView);
        stationImgView = view.findViewById(R.id.stationImgView);
        infoBarLayout = view.findViewById(R.id.detailInfoBar);

//        load in song cards from recycler view
        setStationData();
        loadSongView();

        return view;
    }

    private void loadSongView() {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        SongsFragment fragment = SongsFragment.newInstance(station.getStationGenre());
        manager.beginTransaction()
                .add(R.id.station_details_container, fragment)
                .commit();
    }

    private void setStationData() {
        if (station != null) {
            stationNameView.setText(station.getStationTitle());
            int resource = stationImgView.getResources().getIdentifier(station.getImgUri(), null, stationImgView.getContext().getPackageName());
            stationImgView.setImageResource(resource);


            if (station.getStationGenre().equals(StationGenre.FLYING)) {
                infoBarLayout.setBackgroundColor(getResources().getColor(R.color.flightPlanAccent, null));
            }
            else if (station.getStationGenre().equals(StationGenre.BIKING)) {
                infoBarLayout.setBackgroundColor(getResources().getColor(R.color.bikingAccent, null));
            }
            else if (station.getStationGenre().equals(StationGenre.KIDS)) {
                infoBarLayout.setBackgroundColor(getResources().getColor(R.color.kidsJamsAccent, null));
            }
            else if (station.getStationGenre().equals(StationGenre.SOCIAL)) {
                infoBarLayout.setBackgroundColor(getResources().getColor(R.color.socialAccent, null));
            }
            else if (station.getStationGenre().equals(StationGenre.SOUL)) {
                infoBarLayout.setBackgroundColor(getResources().getColor(R.color.soulAccent, null));
            }
            else if (station.getStationGenre().equals(StationGenre.THROWBACK)) {
                infoBarLayout.setBackgroundColor(getResources().getColor(R.color.throwbackAccent, null));
            }
            else {
                System.out.println("ERROR-> DetailsFragment.setStationData() : Cannot match stationTitle");
                return;
            }
        }
    }

}
