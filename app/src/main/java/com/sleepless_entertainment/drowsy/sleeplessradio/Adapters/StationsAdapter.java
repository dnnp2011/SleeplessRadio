package com.sleepless_entertainment.drowsy.sleeplessradio.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.sleepless_entertainment.drowsy.sleeplessradio.Holders.StationViewHolder;

//  Fetches data from source (Database, File, etc) and sticks into ViewHolder to then be loaded into the View

public class StationsAdapter extends RecyclerView.Adapter<StationViewHolder> {


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
