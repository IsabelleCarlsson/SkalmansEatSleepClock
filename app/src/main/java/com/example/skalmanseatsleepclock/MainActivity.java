package com.example.skalmanseatsleepclock;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.AnalogClock;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private String clockToast;
    private final Handler handler = new Handler();
    private TextView toDo;
    AnalogClock analogClock;
    TextClock digitalClock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toDo = findViewById(R.id.to_do_text);
        analogClock = findViewById(R.id.analog_clock);
        digitalClock = findViewById(R.id.digital_clock);
        digitalClock.setVisibility(View.GONE);

        // Handler that updates To Do text
        handler.post(new Runnable() {
            @Override
            public void run() {
                updateTimeToText();
                handler.postDelayed(this, 1000);
            }
        });

        // Shows toast when analogClock is clicked
        analogClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, clockToast, Toast.LENGTH_SHORT).show();
            }
        });

        // Shows toast when digitalClock is clicked
        digitalClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, clockToast, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateTimeToText() {
        String toDoString;
        Time now = new Time();
        now.setToNow();
        int mins = now.minute;

        // Update time to do text according to minutes
        if ((mins >= 7 && mins <= 12) || (mins >= 17 && mins <= 21) || (mins >= 28 && mins <= 33) || (mins >= 38 && mins <= 42) || (mins >= 48 && mins <= 55)) {
            toDoString = "Time to sleep";
        } else if ((mins >= 0 && mins <= 6) || (mins >= 13 && mins <= 16) || (mins >= 34 && mins <= 37) || (mins >= 56 && mins <= 59)) {
            toDoString = "Time to eat";
        } else {
            toDoString = "Time to drink";
        }
        toDo.setText(toDoString);
        clockToast = toDoString;
    }

    public void settingsClicked(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivityForResult(intent, SettingsActivity.SETTINGS_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int fontSize;

        if (requestCode == SettingsActivity.SETTINGS_REQUEST) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    // Change size according to setting
                    fontSize = data.getIntExtra("size", 32);
                    toDo.setTextSize(fontSize);

                    // Handles digital/analog clock setting
                    if (data.getBooleanExtra("digital", false)) {
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