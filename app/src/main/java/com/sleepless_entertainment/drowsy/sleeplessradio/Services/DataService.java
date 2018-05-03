package com.sleepless_entertainment.drowsy.sleeplessradio.Services;

import android.content.res.Resources;
import android.support.annotation.StringRes;

import com.sleepless_entertainment.drowsy.sleeplessradio.Activities.MainActivity;
import com.sleepless_entertainment.drowsy.sleeplessradio.Model.Station;
import com.sleepless_entertainment.drowsy.sleeplessradio.R;
import com.sleepless_entertainment.drowsy.sleeplessradio.Activities.MainActivity.*;

import java.util.ArrayList;

import static android.app.PendingIntent.getActivity;

public class DataService {
    private static final DataService ourInstance = new DataService();

    public static DataService getInstance() {
        return ourInstance;
    }

    private DataService() {
    }

    public ArrayList<Station> getFeaturedStations() {
//        This type of method would be used to download stations from the internet

        ArrayList<Station> list = new ArrayList<>();
        list.add(new Station(MainActivity.getMainActivity().getString(R.string.flight_plan_title), "flightplanmusic"));
        list.add(new Station(MainActivity.getMainActivity().getString(R.string.biking_title), "bicyclemusic"));
        list.add(new Station(MainActivity.getMainActivity().getString(R.string.kids_jams_title), "kidsmusic"));

        return list;
    }

    public ArrayList<Station> getRecentStations() {
//        TODO: Add functionality to save and display recently selected stations

        ArrayList<Station> list = new ArrayList<>();
        list.add(getFeaturedStations().get(2));
        list.add(getPartyPlaylist().get(1));
        list.add(getFeaturedStations().get(1));
        list.add(getPartyPlaylist().get(2));
        list.add(getFeaturedStations().get(0));

        return list;
    }

    public ArrayList<Station> getPartyPlaylist() {
        ArrayList<Station> list = new ArrayList<>();
        list.add(new Station(MainActivity.getMainActivity().getString(R.string.throwback_title), "vinylmusic"));
        list.add(new Station(MainActivity.getMainActivity().getString(R.string.social_title), "socialmusic"));
        list.add(new Station(MainActivity.getMainActivity().getString(R.string.soul_title), "keymusic"));

        return list;
    }
}
