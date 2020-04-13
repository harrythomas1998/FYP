package com.example.fyp.Shade;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.fyp.FullSunActivities.FullSunAlpines;
import com.example.fyp.FullSunActivities.FullSunBedding;
import com.example.fyp.FullSunActivities.FullSunClimbers;
import com.example.fyp.FullSunActivities.FullSunConifers;
import com.example.fyp.FullSunActivities.FullSunExotics;
import com.example.fyp.FullSunActivities.FullSunFerns;
import com.example.fyp.FullSunActivities.FullSunGrasses;
import com.example.fyp.FullSunActivities.FullSunHedges;
import com.example.fyp.Halfshade.HalfAlpines;
import com.example.fyp.Halfshade.HalfBedding;
import com.example.fyp.Halfshade.HalfClimbers;
import com.example.fyp.Halfshade.HalfConifers;
import com.example.fyp.Halfshade.HalfExotics;
import com.example.fyp.Halfshade.HalfFerns;
import com.example.fyp.Halfshade.HalfGrasses;
import com.example.fyp.Halfshade.HalfHedges;
import com.example.fyp.R;

public class HalfShade extends AppCompatActivity {

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
        setContentView(R.layout.activity_half_shade);

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.statusBar));


        conifers = findViewById(R.id.halfconifersButton);
        alpines = findViewById(R.id.halfalpinesButton);
        bedding = findViewById(R.id.halfbeddingButton);
        climbers = findViewById(R.id.halfclimbersButton);
        exotic = findViewById(R.id.halfexoticButton);
        ferns = findViewById(R.id.halffernButton);
        grasses = findViewById(R.id.halfgrassesButton);
        hedges = findViewById(R.id.halfhedgesButton);

        conifers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(HalfShade.this, HalfConifers.class);
                startActivity(i);
            }
        });

        alpines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(HalfShade.this, HalfAlpines.class);
                startActivity(i);
            }
        });

        bedding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(HalfShade.this, HalfBedding.class);
                startActivity(i);
            }
        });

        climbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(HalfShade.this, HalfClimbers.class);
                startActivity(i);
            }
        });

        exotic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(HalfShade.this, HalfExotics.class);
                startActivity(i);
            }
        });

        ferns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(HalfShade.this, HalfFerns.class);
                startActivity(i);
            }
        });

        grasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(HalfShade.this, HalfGrasses.class);
                startActivity(i);
            }
        });

        hedges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(HalfShade.this, HalfHedges.class);
                startActivity(i);
            }
        });

    }
}
