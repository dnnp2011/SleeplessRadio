package com.sleepless_entertainment.drowsy.sleeplessradio.Model;

public class Station {

    //region Class Variables
    final String DRAWABLE = "drawable/";
    private String stationTitle;
    private String imgUri;
    //endregion


    //region Getters and Setters
    public String getStationTitle() {
        return stationTitle;
    }

    public String getImgUri() {
        return imgUri;
    }
    //endregion


    public enum StationType {
        FEATURED, RECENT, PARTY
    }


    //region Constructor
    public Station(String stationTitle, String imgUri) {
        this.stationTitle = stationTitle;
        this.imgUri = imgUri;
    }
    //endregion

}
