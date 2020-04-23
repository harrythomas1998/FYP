package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.fyp.PlantActivities.AlpineRockeryActivity;
import com.example.fyp.PlantActivities.BeddingActivity;
import com.example.fyp.PlantActivities.ClimbersActivity;
import com.example.fyp.PlantActivities.ConifersActivity;
import com.example.fyp.PlantActivities.ExoticActivity;
import com.example.fyp.PlantActivities.FernActivity;
import com.example.fyp.PlantActivities.GrassesActivity;
import com.example.fyp.PlantActivities.HedgesActivity;
import com.example.fyp.Shade.FullShade;
import com.example.fyp.Shade.FullSun;
import com.example.fyp.Shade.HalfShade;
import com.example.fyp.suitable.SuitableMenu;

public class SelectPlantsMenu extends AppCompatActivity {

    Button conifers;
    Button alpines;
    Button bedding;
    Button climbers;
    Button exotic;
    Button ferns;
    Button grasses;
    Button hedges;

    Button fullShade;
    Button halfShade;
    Button fullSun;

    Button suitable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_plants_menu);


        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.statusBar));


        conifers = findViewById(R.id.conifersButton);
        alpines = findViewById(R.id.alpinesButton);
        bedding = findViewById(R.id.beddingButton);
        climbers = findViewById(R.id.climbersButton);
        exotic = findViewById(R.id.exoticButton);
        ferns = findViewById(R.id.fernButton);
        grasses = findViewById(R.id.grassesButton);
        hedges = findViewById(R.id.hedgesButton);

        fullSun = findViewById(R.id.noShadeButton);
        halfShade = findViewById(R.id.partialShadeButton);
        fullShade = findViewById(R.id.fullShadeButton);

        suitable = findViewById(R.id.suitablePlantsButton);


        conifers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(SelectPlantsMenu.this, ConifersActivity.class);
                startActivity(i);
            }
        });

        alpines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ii = new Intent(SelectPlantsMenu.this, AlpineRockeryActivity.class);
                startActivity(ii);
            }
        });

        bedding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent iii = new Intent(SelectPlantsMenu.this, BeddingActivity.class);
                startActivity(iii);
            }
        });

        climbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent iv = new Intent(SelectPlantsMenu.this, ClimbersActivity.class);
                startActivity(iv);
            }
        });

        exotic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent vv = new Intent(SelectPlantsMenu.this, ExoticActivity.class);
                startActivity(vv);
            }
        });

        ferns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent vi = new Intent(SelectPlantsMenu.this, FernActivity.class);
                startActivity(vi);
            }
        });

        grasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent vi = new Intent(SelectPlantsMenu.this, GrassesActivity.class);
                startActivity(vi);
            }
        });

        hedges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent vii = new Intent(SelectPlantsMenu.this, HedgesActivity.class);
                startActivity(vii);
            }
        });



        fullSun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent vii = new Intent(SelectPlantsMenu.this, FullSun.class);
                startActivity(vii);
            }
        });

        halfShade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent vii = new Intent(SelectPlantsMenu.this, HalfShade.class);
                startActivity(vii);
            }
        });

        fullShade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent vii = new Intent(SelectPlantsMenu.this, FullShade.class);
                startActivity(vii);
            }
        });

        suitable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent vii = new Intent(SelectPlantsMenu.this, SuitableMenu.class);
                startActivity(vii);
            }
        });




    }
}
