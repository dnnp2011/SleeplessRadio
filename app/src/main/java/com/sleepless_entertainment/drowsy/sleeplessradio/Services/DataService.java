package com.sleepless_entertainment.drowsy.sleeplessradio.Services;

import com.sleepless_entertainment.drowsy.sleeplessradio.Model.Station;

import java.util.ArrayList;

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
        list.add(new Station("Flight Plan (Tunes  for travel)", "flightplanmusic"));
        list.add(new Station("Two Wheelin' (Biking playlist)", "bicyclemusic"));
        list.add(new Station("Kid's Jams (Music for youngsters)", "kidsmusic"));

        return list;
    }

    public ArrayList<Station> getRecentStations() {
//        TODO: Add functionality to save and display recently selected stations

        ArrayList<Station> list = new ArrayList<>();
        list.add(getFeaturedStations().get(0));
        list.add(getPartyPlaylist().get(1));
        list.add(getFeaturedStations().get(1));
        list.add(getPartyPlaylist().get(2));

        return list;
    }

    public ArrayList<Station> getPartyPlaylist() {
        ArrayList<Station> list = new ArrayList<>();
        list.add(new Station("Blast From the Past (Throwback tunes)", "vinylmusic"));
        list.add(new Station("Gather Round (Jam with your friends)", "socialmusic"));
        list.add(new Station("Unlock Your Soul (Inspiring songs)", "keymusic"));

        return list;
    }
}
