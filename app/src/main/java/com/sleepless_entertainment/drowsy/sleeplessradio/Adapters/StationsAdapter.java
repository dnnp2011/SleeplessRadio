package com.sleepless_entertainment.drowsy.sleeplessradio.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.sleepless_entertainment.drowsy.sleeplessradio.Holders.StationViewHolder;
import com.sleepless_entertainment.drowsy.sleeplessradio.Model.Station;

import java.util.ArrayList;

//  Fetches data from source (Database, File, etc) and sticks into ViewHolder to then be loaded into the View

public class StationsAdapter extends RecyclerView.Adapter<StationViewHolder> {

    private ArrayList<Station> stations;

    public StationsAdapter(ArrayList<Station> stations) {
        this.stations = stations;
    }

    @NonNull
    @Override
    public StationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull StationViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

}
