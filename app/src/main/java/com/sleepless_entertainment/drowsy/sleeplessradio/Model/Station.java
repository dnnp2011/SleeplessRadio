package com.sleepless_entertainment.drowsy.sleeplessradio.Model;

import com.sleepless_entertainment.drowsy.sleeplessradio.Activities.MainActivity;
import com.sleepless_entertainment.drowsy.sleeplessradio.R;

import java.io.Serializable;

public class Station implements Serializable {

    //region Class Variables
    final String DRAWABLE = "drawable/";
    private String stationTitle;
    private String imgUri;
    //endregion

    /**
     * The valid Station Types for each RecyclerView
     */
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

    public enum StationGenre {
        FLYING, BIKING, SOCIAL, KIDS, SOUL, THROWBACK;


        @Override
        public String toString() {
            switch (this) {
                case FLYING:
                    return MainActivity.getMainActivity().getString(R.string.flight_plan_title);
                case BIKING:
                    return MainActivity.getMainActivity().getString(R.string.biking_title);
                case SOCIAL:
                    return MainActivity.getMainActivity().getString(R.string.social_title);
                case KIDS:
                    return MainActivity.getMainActivity().getString(R.string.kids_jams_title);
                case SOUL:
                    return MainActivity.getMainActivity().getString(R.string.soul_title);
                case THROWBACK:
                    return MainActivity.getMainActivity().getString(R.string.throwback_title);
            }
            return null;
        }
    }


    //region Getters and Setters
    public String getStationTitle() {
        return stationTitle;
    }

    public String getImgUri() {
        return DRAWABLE + imgUri;
    }
    //endregion


    //region Constructor
    public Station(String stationTitle, String imgUri) {
        this.stationTitle = stationTitle;
        this.imgUri = imgUri;
    }
    //endregion


    @Override
    public String toString() {
        return String.format("Station Object-> Title: %s URI: %s", this.stationTitle, this.imgUri);
    }
}
