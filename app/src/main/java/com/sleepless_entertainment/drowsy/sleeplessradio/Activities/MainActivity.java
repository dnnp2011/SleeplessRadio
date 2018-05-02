package com.sleepless_entertainment.drowsy.sleeplessradio.Activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.sleepless_entertainment.drowsy.sleeplessradio.Fragments.MainFragment;
import com.sleepless_entertainment.drowsy.sleeplessradio.Model.Station;
import com.sleepless_entertainment.drowsy.sleeplessradio.R;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Base64;

public class MainActivity extends AppCompatActivity {

    private static MainActivity mainActivity;

    public static MainActivity getMainActivity() {
        return mainActivity;
    }

    private static void setMainActivity(MainActivity mainActivity) {
        MainActivity.mainActivity = mainActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainActivity.setMainActivity(this);

        loadMainFragment();
    }

    private void loadMainFragment() {
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.add(R.id.fragment_container, new StationsFragment());
//        transaction.commit();

//        Alternate Method, Allows activity to load much faster if the fragment has already been initialized
        FragmentManager manager = getSupportFragmentManager();
        MainFragment mainFragment = (MainFragment) manager.findFragmentById(R.id.fragment_container);

        if (mainFragment == null) {
            mainFragment = MainFragment.newInstance("", "");
            manager.beginTransaction().setCustomAnimations(R.anim.enter_from_bottom, R.anim.exit_to_top).add(R.id.fragment_container, mainFragment).commit();
        }
    }

    /**
     * Generic method to load the target fragment. Adds to Backstack, and sets standard animation (In Left, Out Right, PopIn Right, PopOut Left)
     * @param fragment The fragment to be loaded. Must pass newInstance of fragment with the Station as an argument
     */
    public void loadNextFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().addToBackStack(null).setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left)
                .replace(R.id.fragment_container, fragment).commit();
    }

    public static String serializeToString(Serializable object) throws IOException {
        ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteOutputStream);
        objectOutputStream.writeObject(object);
        objectOutputStream.close();
        return Base64.getEncoder().encodeToString(byteOutputStream.toByteArray());
    }

    public static Object reconstituteFromString(String string) throws IOException, ClassNotFoundException {
        byte[] data = Base64.getDecoder().decode(string);
        ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(data));
        Object object = (objectInputStream.readObject());
        objectInputStream.close();
        return object;
    }
}
