package com.sleepless_entertainment.drowsy.sleeplessradio.Activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.sleepless_entertainment.drowsy.sleeplessradio.Fragments.MainFragment;
import com.sleepless_entertainment.drowsy.sleeplessradio.Fragments.MediaBarFragment;
import com.sleepless_entertainment.drowsy.sleeplessradio.R;
import com.sleepless_entertainment.drowsy.sleeplessradio.Services.MusicPlayer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Base64;

public class MainActivity extends AppCompatActivity implements MediaBarFragment.OnMediaBarFragmentInteractionListener {

    //region To-Do List:
    //    TODO: Add basic functionality to toolbar buttons
    //    TODO: Complete DetailsFragment
    //    TODO: Animate toolbar changes between Fragments
    //    TODO: Add more station cards to exemplify scrolling functionality
    //    TODO: Add "More" Button
    //    TODO: Add capacity to play limited number of songs, or tune into internet radio station
    //    TODO: Animate CardView inflating into DetailsFragment background
    //    TODO: Add basic search functionality
    //    TODO: Add bluetooth casting functionality
    //    TODO: Add basic media player options: Start, Stop, Pause
    //    TODO: Add data persistence for recent stations, and current playing song
    //    TODO: Handle switching view modes
    //    TODO: Change genre images between stations
    //    TODO: Implement proper "recent activity" station functionality
    //    TODO: When hitting play on a new song, stop all other songs, and reset their PAUSE/PLAY buttons
    //    TODO: Animate PLAY/PAUSE switch (at least an opacity transition)
    //    TODO: Adjust bottom padding when MediaBar is instantiated
    //    TODO:
    //    BUG: Problem with Station label under Kid's Jams
    //    BUG: Pressing PAUSE/START changes the size of SongCard
    //    BUG: PAUSE and START buttons aren't aligned
    //    BUG: MediaBar doesn't appear properly sized on certain devices
    //    BUG: App crashes when switching to landscape
    //    BUG: Under certain circumstances the PLAY/PAUSE buttons don't properly revert (when current song is paused from MediaBar and another SongCard's play button is pressed, both songs switch to pause icon
    //    BUG: App crashes when opening to detail view while previously playin song (Song data is not persistent)
    //    BUG: App crashes when pressing MediaBar buttons before playing a song
    //    BUG:
    //endregion

    private static MainActivity mainActivity;
    public MusicPlayer musicPlayer;

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
        this.musicPlayer = new MusicPlayer();

        loadMainFragment();
    }

    private void loadMainFragment() {
        FragmentManager manager = getSupportFragmentManager();
        MainFragment mainFragment = (MainFragment) manager.findFragmentById(R.id.fragment_container);

        if (mainFragment == null) {
            mainFragment = MainFragment.newInstance("", "");
            manager.beginTransaction()
                    .setCustomAnimations(R.anim.enter_from_bottom, R.anim.exit_to_top)
                    .add(R.id.fragment_container, mainFragment)
                    .commit();
        }
    }

    public void loadMediaBarFragment() {
        FragmentManager manager = getSupportFragmentManager();
        MediaBarFragment mediaBarFragment = (MediaBarFragment) manager.findFragmentById(R.id.media_bar_container);

        manager.beginTransaction()
                .setCustomAnimations(R.anim.enter_from_bottom, R.anim.exit_to_bottom)
                .replace(R.id.media_bar_container, MediaBarFragment.newInstance(""))
                .commit();
    }

    /**
     * Generic method to load the target fragment. Adds to Backstack, and sets standard animation (In Left, Out Right, PopIn Right, PopOut Left)
     * @param fragment The fragment to be loaded. Must pass newInstance of fragment with the Station as an argument
     */
    public void loadNextFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .addToBackStack(null)
                .setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left)
                .replace(R.id.fragment_container, fragment)
                .commit();
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

    @Override
    protected void onStop() {
        this.musicPlayer.destroy();
        this.musicPlayer = null;
        super.onStop();
    }

    @Override
    public void onMediaBarFragmentInteraction(MusicPlayer.MediaCommand command) {

    }
}
