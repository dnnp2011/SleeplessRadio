package com.sleepless_entertainment.drowsy.sleeplessradio.Holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sleepless_entertainment.drowsy.sleeplessradio.Model.Station;
import com.sleepless_entertainment.drowsy.sleeplessradio.R;

//  Puts data that it holds into the View when the View is ready to receive more data

public class StationViewHolder extends RecyclerView.ViewHolder {

    private ImageView mainImage;
    private TextView titleTextView;


    /**
     * Constructor: Initializes the class variables for each CardView with image and title
     * @param itemView The inflated station card with which to fetch the View Id's
     */
    public StationViewHolder(View itemView) {
        super(itemView);

        this.mainImage = itemView.findViewById(R.id.main_image);
        this.titleTextView = itemView.findViewById(R.id.title_text);
    }

    /**
     * Updates the CardView with the appropriate Image and Title from its respective Station object
     * @param station The Station object containing this CardView's data
     */
    public void updateUI(Station station) {
        String uri = station.getImgUri();
        int resource = mainImage.getResources().getIdentifier(uri, null, mainImage.getContext().getPackageName());
        mainImage.setImageResource(resource);

        titleTextView.setText(station.getStationTitle());
    }

}
