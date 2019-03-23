package com.example.skalmanseatsleepwatch;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.AnalogClock;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private String clockToast;
    Time now = new Time();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Handler handler = new Handler();
        AnalogClock clock = findViewById(R.id.clock);

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
        TextView to_do = findViewById(R.id.to_do);
        String toDo;
        now.setToNow();
        int mins = now.minute;


        // Update time to do text according to minutes
        if ((mins >= 7 && mins <= 12) || (mins >= 17 && mins <= 21) || (mins >= 28 && mins <= 33) || (mins >= 38 && mins <= 42) || (mins >= 48 && mins <= 55)) {
            toDo = "Time to sleep";
        } else if ((mins >= 0 && mins <= 6) || (mins >= 13 && mins <= 16) || (mins >= 34 && mins <= 37) || (mins >= 56 && mins <= 59)) {
            toDo = "Time to eat";
        } else {
            toDo = "Time to drink";
        }
        to_do.setText(toDo);
        clockToast = toDo;
    }

    public void settingsClicked(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}