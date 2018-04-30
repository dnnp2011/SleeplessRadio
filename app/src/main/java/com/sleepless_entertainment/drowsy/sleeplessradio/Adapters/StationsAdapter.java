package com.sleepless_entertainment.drowsy.sleeplessradio.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sleepless_entertainment.drowsy.sleeplessradio.Holders.StationViewHolder;
import com.sleepless_entertainment.drowsy.sleeplessradio.Model.Station;
import com.sleepless_entertainment.drowsy.sleeplessradio.R;

import java.util.ArrayList;

//  Fetches data from source (Database, File, etc) and sticks into ViewHolder to then be loaded into the View

public class StationsAdapter extends RecyclerView.Adapter<StationViewHolder> {

    private ArrayList<Station> stations;

    /**
     * Constructor: Initialize the Array of stations for this View
     * @param stations The Array of stations
     */
    public StationsAdapter(ArrayList<Station> stations) {
        this.stations = stations;
    }

    /**
     * Inflates the stationCard and passes the empty stationCard to a new StationViewHolder
     * @param parent The parent ViewGroup -- In this case, the RecyclerView
     * @param viewType Not used in this context
     * @return New StationViewHolder instance with the inflated StationCard
     */
    @NonNull
    @Override
    public StationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View stationCard = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_station, parent, false);



        return new StationViewHolder(stationCard);
    }

    /**
     * When the Adapter binds the ViewHolder
     * @param holder The custom ViewHolder I created
     * @param position The position in the data source of the next View
     */
    @Override
    public void onBindViewHolder(@NonNull StationViewHolder holder, int position) {
        Station station = stations.get(position);
        holder.updateUI(station);

        final int p = position;
    }

    /**
     * @return Numbers of elements in data source
     */
    @Override
    public int getItemCount() {
        return stations.size();
    }

}
