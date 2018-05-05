package com.sleepless_entertainment.drowsy.sleeplessradio.Services;

import com.sleepless_entertainment.drowsy.sleeplessradio.Activities.MainActivity;
import com.sleepless_entertainment.drowsy.sleeplessradio.Model.Song;
import com.sleepless_entertainment.drowsy.sleeplessradio.Model.Station;
import com.sleepless_entertainment.drowsy.sleeplessradio.Model.Station.StationGenre;
import com.sleepless_entertainment.drowsy.sleeplessradio.R;

import java.util.ArrayList;

public class DataService {
    private static final DataService ourInstance = new DataService();

    public static DataService getInstance() {
        return ourInstance;
    }

    private ArrayList<Song> songsList;

    private DataService() {
        songsList = new ArrayList<>();

        songsList.add(new Song("Buddy", "A friendly song", StationGenre.SOCIAL, R.raw.buddy));
        songsList.add(new Song("Clear Day", "A song that makes you see the wide open skies", StationGenre.FLYING, R.raw.clearday));
        songsList.add(new Song("Cute", "Cute and fuzzy", StationGenre.KIDS, R.raw.cute));
        songsList.add(new Song("Dubstep", "Put some wub in your dub", StationGenre.SOCIAL, R.raw.dubstep));
        songsList.add(new Song("Endless Motion", "Keep on movin'", StationGenre.BIKING, R.raw.endlessmotion));
        songsList.add(new Song("French Jazz", "Feel the culture", StationGenre.SOUL, R.raw.frenchyjazz));
        songsList.add(new Song("Happy Rock", "Rock n Roll", StationGenre.THROWBACK, R.raw.happyrock));
        songsList.add(new Song("Moose", "Nuff said", StationGenre.SOCIAL, R.raw.moose));
        songsList.add(new Song("Retro Soul", "Let the music move you", StationGenre.SOUL, R.raw.retrosoul));
        songsList.add(new Song("Summer", "Everybody loves summer", StationGenre.BIKING, R.raw.summer));
        songsList.add(new Song("Sunny", "Bright, sunshiney day", StationGenre.FLYING, R.raw.sunny));
        songsList.add(new Song("Funny Song", "Funky, funny", StationGenre.KIDS, R.raw.sunnysong));
        songsList.add(new Song("Ukelele", "A little bit of twang", StationGenre.THROWBACK, R.raw.ukelele));
    }

    public ArrayList<Song> getGenreSongs(StationGenre genre) {
        ArrayList<Song> subList = new ArrayList<>();

        for(Song song : songsList) {
            if (song.getSongGenre().equals(genre))
                subList.add(song);
        }

        return subList;
    }

    public ArrayList<Station> getFeaturedStations() {
//        This type of method would be used to download stations from the internet

        ArrayList<Station> list = new ArrayList<>();
        list.add(new Station(MainActivity.getMainActivity().getString(R.string.flight_plan_title), StationGenre.FLYING, "flightplanmusic"));
        list.add(new Station(MainActivity.getMainActivity().getString(R.string.biking_title), StationGenre.BIKING, "bicyclemusic"));
        list.add(new Station(MainActivity.getMainActivity().getString(R.string.kids_jams_title), StationGenre.KIDS, "kidsmusic"));

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
        list.add(new Station(MainActivity.getMainActivity().getString(R.string.throwback_title), StationGenre.THROWBACK, "vinylmusic"));
        list.add(new Station(MainActivity.getMainActivity().getString(R.string.social_title), StationGenre.SOCIAL, "socialmusic"));
        list.add(new Station(MainActivity.getMainActivity().getString(R.string.soul_title), StationGenre.SOUL, "keymusic"));

        return list;
    }
}
