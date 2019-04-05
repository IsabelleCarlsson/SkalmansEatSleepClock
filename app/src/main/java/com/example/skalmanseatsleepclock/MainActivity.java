package com.example.skalmanseatsleepclock;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AnalogClock;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private String clockToast;
    private Handler handler;
    private TextView toDoView;
    private String toDoText;
    private AnalogClock analogClock;
    private TextClock digitalClock;
    private ToDoUpdater toDoUpdater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler();
        toDoUpdater = new ToDoUpdater();
        toDoView = findViewById(R.id.to_do_text);
        analogClock = findViewById(R.id.analog_clock);
        digitalClock = findViewById(R.id.digital_clock);
        digitalClock.setVisibility(View.GONE);

        if (savedInstanceState != null) {
            toDoView.setTextSize(savedInstanceState.getInt("textSize"));
            if (savedInstanceState.getBoolean("digitalClock")) {
                digitalClock.setVisibility(View.VISIBLE);
                analogClock.setVisibility(View.GONE);
            } else {
                analogClock.setVisibility(View.VISIBLE);
                digitalClock.setVisibility(View.GONE);
            }

        }
        textHandler();

        // Shows toast when analog clock is clicked
        analogClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, clockToast, Toast.LENGTH_SHORT).show();
            }
        });

        // Shows toast when digital clock is clicked
        digitalClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, clockToast, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void textHandler() {
        // Handler that updates To Do text
        handler.post(new Runnable() {
            @Override
            public void run() {
                toDoUpdater.updateText();
                toDoText = toDoUpdater.toString();
                toDoView.setText(toDoText);
                clockToast = toDoText;
                handler.postDelayed(this, 1000);
            }
        });
    }

    public void settingsClicked(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivityForResult(intent, SettingsActivity.SETTINGS_REQUEST);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("textSize", (int) (toDoView.getTextSize() / getResources().getDisplayMetrics().scaledDensity));
        outState.putBoolean("digitalClock", digitalClock.isShown());
        System.out.println((int) toDoView.getTextSize());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int fontSize;

        if (requestCode == SettingsActivity.SETTINGS_REQUEST) {
            if (resultCode == RESULT_OK) {
                if (data != null) {

                    // Change text size according to slider progress
                    fontSize = data.getIntExtra("toDoSize", 32);
                    toDoView.setTextSize(fontSize);

                    // Show/hide digital/analog clock according to switch state
                    if (data.getBooleanExtra("digitalClock", false)) {
                        digitalClock.setVisibility(View.VISIBLE);
                        analogClock.setVisibility(View.GONE);
                    } else {
                        analogClock.setVisibility(View.VISIBLE);
                        digitalClock.setVisibility(View.GONE);
                    }
                }
            }
        }
    }
}