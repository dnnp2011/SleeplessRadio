package com.sleepless_entertainment.drowsy.sleeplessradio.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

//        Load in station details fragment and change infoBar
        loadStationView();

        return view;
    }

    private void loadStationView() {
        if (station != null) {
            stationNameView.setText(station.getStationTitle());
            int resource = stationImgView.getResources().getIdentifier(station.getImgUri(), null, stationImgView.getContext().getPackageName());
            stationImgView.setImageResource(resource);

            FragmentManager manager = getActivity().getSupportFragmentManager();
            Fragment targetFragment;

            if (station.getStationTitle().equals(StationGenre.FLYING.toString())) {
                targetFragment = new DetailsFlightPlan();
            }
            else if (station.getStationTitle().equals(StationGenre.BIKING.toString())) {
                targetFragment = new DetailsFlightPlan();
            }
            else if (station.getStationTitle().equals(StationGenre.KIDS.toString())) {
                targetFragment = new DetailsFlightPlan();
            }
            else if (station.getStationTitle().equals(StationGenre.SOCIAL.toString())) {
                targetFragment = new DetailsFlightPlan();
            }
            else if (station.getStationTitle().equals(StationGenre.SOUL.toString())) {
                targetFragment = new DetailsFlightPlan();
            }
            else if (station.getStationTitle().equals(StationGenre.THROWBACK.toString())) {
                targetFragment = new DetailsFlightPlan();
            }
            else {
                System.out.println("ERROR-> DetaisFragment.loadStationView() : Cannot match stationTitle");
                return;
            }

            manager.beginTransaction()
                    .replace(R.id.station_details_container, targetFragment)
                    .commit();

        }
    }

}
