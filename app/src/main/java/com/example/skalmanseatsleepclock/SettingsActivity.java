package com.example.skalmanseatsleepclock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {
    static final int SETTINGS_REQUEST = 32;
    private static int savedProgress = 32;
    private static boolean digitalClockChecked = false;
    private int progressChangedValue;
    private TextView settingsSizeText;
    private SeekBar fontSizeSlider;
    private Switch clockTypeSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        fontSizeSlider = findViewById(R.id.size_slider);
        settingsSizeText = findViewById(R.id.font_size_text);
        clockTypeSwitch = findViewById(R.id.type_switch);

        fontSizeSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // Unused
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                settingsSizeText.setText(String.format("Time To Do Font Size: %ssp",
                progressChangedValue));
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        savedProgress = fontSizeSlider.getProgress();
        digitalClockChecked = clockTypeSwitch.isChecked();
    }

    @Override
    protected void onResume() {
        super.onResume();
        settingsSizeText.setText(String.format("Time To Do Font Size: %ssp", savedProgress));
        fontSizeSlider.setProgress(savedProgress);
        clockTypeSwitch.setChecked(digitalClockChecked);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent();
                intent.putExtra("toDoSize", fontSizeSlider.getProgress());
                intent.putExtra("digitalClock", clockTypeSwitch.isChecked());
                setResult(RESULT_OK, intent);
                Toast.makeText(SettingsActivity.this, "Settings saved",
                        Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
        return true;
    }
}
