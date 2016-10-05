package com.example.amir.bruinswipes;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView swipes;
    private int swipesRemaining = 11;
    private static final String SWIPES = "swipes";
    private static final String KEY = "key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swipes = (TextView) findViewById(R.id.textView);
        loadInt(KEY);

        if(savedInstanceState != null) {
            swipesRemaining = savedInstanceState.getInt(SWIPES);
        }

        updateText(swipesRemaining);
    }

    public void save(View view) {
        saveInt("key", swipesRemaining);
    }

    public void reset(View view) {
        swipesRemaining = 11;
        updateText(swipesRemaining);
    }

    public void swipe(View view) {
        if(swipesRemaining > 1) {
            swipesRemaining--;
            updateText(swipesRemaining);
        } else {
            Toast toast = Toast.makeText(this, "You ran out of swipes!...Resetting", Toast.LENGTH_SHORT);
            toast.show();
            reset(view);
        }
    }

    private void updateText(int swipesRemaining) {
        swipes.setText("Swipes: " + swipesRemaining);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt(SWIPES, swipesRemaining );
        super.onSaveInstanceState(savedInstanceState);
    }

    private void saveInt(String key, int value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    private void loadInt(String key) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        swipesRemaining = sharedPreferences.getInt(key, swipesRemaining);

        Toast toast = Toast.makeText(this, "Swipes left: " + swipesRemaining, Toast.LENGTH_SHORT);
        toast.show();
    }
}
