package com.sleepless_entertainment.drowsy.sleeplessradio.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sleepless_entertainment.drowsy.sleeplessradio.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsSoul extends Fragment {


    public DetailsSoul() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details_soul, container, false);
    }

}
