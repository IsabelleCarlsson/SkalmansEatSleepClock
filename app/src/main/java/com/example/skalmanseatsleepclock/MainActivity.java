package com.example.skalmanseatsleepclock;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.AnalogClock;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private String clockToast;
    private final Handler handler = new Handler();
    private TextView toDo;
    AnalogClock clock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toDo = findViewById(R.id.to_do_text);
        clock = findViewById(R.id.clock_widget);

        // Handler that updates To Do text
        handler.post(new Runnable() {
            @Override
            public void run() {
                updateTimeToText();
                handler.postDelayed(this, 1000);
            }
        });

        // Shows toast when clock is clicked
        clock.setOnClickListener(new View.OnClickListener() {
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
                    fontSize = data.getIntExtra("size", 32);
                    toDo.setTextSize(fontSize);
                    data.getBooleanExtra("dial", false);
                    // TODO: Set dial background to klocka_numbers.jpg
                    // TODO: Figure out how to remove the hour dial
                }
            }
        }
    }
}