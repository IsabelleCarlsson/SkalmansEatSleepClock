package com.example.skalmanseatsleepwatch;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    final Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler.post(new Runnable() {
            @Override
            public void run() {
                updateTimeToDo();
                handler.postDelayed(this, 1000);
            }
        });
    }

    public void updateTimeToDo() {
        TextView to_do = findViewById(R.id.to_do);
        Time now = new Time();
        now.setToNow();
        int mins = now.minute;

        // Update time to do text according to minutes
        if ((mins >= 7 && mins <= 12) || (mins >= 17 && mins <= 21) || (mins >= 28 && mins <= 33) || (mins >= 38 && mins <= 43) || (mins >= 48 && mins <= 53)) {
            to_do.setText(R.string.sleep);
        } else if ((mins >= 0 && mins <= 6) || (mins >= 13 && mins <= 16) || (mins >= 34 && mins <= 37) || (mins >= 54 && mins <= 59)) {
            to_do.setText(R.string.eat);
        } else {
            to_do.setText(R.string.drink);
        }
        (findViewById(R.id.vertical_layout)).invalidate();
    }

    public void settingsClicked(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}