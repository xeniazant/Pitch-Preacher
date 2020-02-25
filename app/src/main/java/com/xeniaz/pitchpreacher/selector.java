package com.xeniaz.pitchpreacher;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class selector extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector);

        Button toSettings = findViewById(R.id.go_to_settings_button);
        Button toPitch = findViewById(R.id.go_to_pitch_button);
        Button toRange = findViewById(R.id.go_to_range_button);

        toSettings.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intent);
            }
        }));


        toPitch.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), pitch.class);
                startActivity(intent);
            }
        }));

        toRange.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), range.class);
                startActivity(intent);
            }
        }));


    }
}
