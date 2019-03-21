package com.example.skalmanseatsleepwatch;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        View someView = findViewById(R.id.color_group);// get Any child View

        // Find the root view
        View root = someView.getRootView();

        // Set the color

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radiobutton_red:
                if (checked)
                    root.setBackgroundColor(getResources().getColor(android.R.color.black));
                    break;
            case R.id.radiobutton_green:
                if (checked)

                    break;
            case R.id.radiobutton_blue:
                if (checked)

                    break;
            case R.id.radiobutton_yellow:
                if (checked)

                    break;
        }
    }

}
