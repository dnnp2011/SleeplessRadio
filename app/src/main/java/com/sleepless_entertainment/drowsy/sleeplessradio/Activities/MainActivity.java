package com.sleepless_entertainment.drowsy.sleeplessradio.Activities;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sleepless_entertainment.drowsy.sleeplessradio.Fragments.MainFragment;
import com.sleepless_entertainment.drowsy.sleeplessradio.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadMainFragment();
    }

    private void loadMainFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container, new MainFragment());
        transaction.commit();

//        Alternate Method, Allows activity to load much faster if the fragment has already been initialized
        FragmentManager manager = getSupportFragmentManager();
        MainFragment mainFragment = (MainFragment)manager.findFragmentById(R.id.fragment_container);

        if (mainFragment == null) {
            mainFragment = MainFragment.newInstance("", "");
            manager.beginTransaction().add(R.id.fragment_container, mainFragment).commit();
        }
    }
}
