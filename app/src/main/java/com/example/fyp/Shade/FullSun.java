package com.example.fyp.Shade;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fyp.FullSunActivities.FullSunAlpines;
import com.example.fyp.FullSunActivities.FullSunBedding;
import com.example.fyp.FullSunActivities.FullSunClimbers;
import com.example.fyp.FullSunActivities.FullSunConifers;
import com.example.fyp.FullSunActivities.FullSunExotics;
import com.example.fyp.FullSunActivities.FullSunFerns;
import com.example.fyp.FullSunActivities.FullSunGrasses;
import com.example.fyp.FullSunActivities.FullSunHedges;
import com.example.fyp.R;

public class FullSun extends AppCompatActivity {

    Button conifers;
    Button alpines;
    Button bedding;
    Button climbers;
    Button exotic;
    Button ferns;
    Button grasses;
    Button hedges;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_sun);

        conifers = findViewById(R.id.fullconifersButton);
        alpines = findViewById(R.id.fullalpinesButton);
        bedding = findViewById(R.id.fullbeddingButton);
        climbers = findViewById(R.id.fullclimbersButton);
        exotic = findViewById(R.id.fullexoticButton);
        ferns = findViewById(R.id.fullfernButton);
        grasses = findViewById(R.id.fullgrassesButton);
        hedges = findViewById(R.id.fullhedgesButton);

        conifers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(FullSun.this, FullSunConifers.class);
                startActivity(i);
            }
        });

        alpines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(FullSun.this, FullSunAlpines.class);
                startActivity(i);
            }
        });

        bedding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(FullSun.this, FullSunBedding.class);
                startActivity(i);
            }
        });

        climbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(FullSun.this, FullSunClimbers.class);
                startActivity(i);
            }
        });

        exotic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(FullSun.this, FullSunExotics.class);
                startActivity(i);
            }
        });

        ferns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(FullSun.this, FullSunFerns.class);
                startActivity(i);
            }
        });

        grasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(FullSun.this, FullSunGrasses.class);
                startActivity(i);
            }
        });

        hedges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(FullSun.this, FullSunHedges.class);
                startActivity(i);
            }
        });


    }
}
