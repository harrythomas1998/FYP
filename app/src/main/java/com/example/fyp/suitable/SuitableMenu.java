package com.example.fyp.suitable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.fyp.FullSunActivities.FullSunAlpines;
import com.example.fyp.R;
import com.example.fyp.Shade.FullSun;

public class SuitableMenu extends AppCompatActivity {

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
        setContentView(R.layout.activity_suitable_menu);

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.statusBar));

        conifers = findViewById(R.id.suitableConifersButton);
        alpines = findViewById(R.id.suitableAlpinesButton);
        bedding = findViewById(R.id.suitableBeddingButton);
        climbers = findViewById(R.id.suitableClimbersButton);
        exotic = findViewById(R.id.suitableExoticButton);
        ferns = findViewById(R.id.suitableFernButton);
        grasses = findViewById(R.id.suitableGrassesButton);
        hedges = findViewById(R.id.suitableHedgesButton);

        alpines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(SuitableMenu.this, SuitableAlpines.class);
                startActivity(i);
            }
        });

        bedding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(SuitableMenu.this, SuitableBedding.class);
                startActivity(i);
            }
        });

        conifers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(SuitableMenu.this, SuitableConifers.class);
                startActivity(i);
            }
        });

        climbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(SuitableMenu.this, SuitableClimbers.class);
                startActivity(i);
            }
        });

        exotic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(SuitableMenu.this, SuitableExotics.class);
                startActivity(i);
            }
        });

        ferns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(SuitableMenu.this, SuitableFerns.class);
                startActivity(i);
            }
        });

        grasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(SuitableMenu.this, SuitableGrasses.class);
                startActivity(i);
            }
        });

        hedges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(SuitableMenu.this, SuitableHedges.class);
                startActivity(i);
            }
        });
    }
}
