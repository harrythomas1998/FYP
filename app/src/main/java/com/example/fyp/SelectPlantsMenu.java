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

public class SelectPlantsMenu extends AppCompatActivity {

    Button conifers;
    Button alpines;
    Button bedding;
    Button climbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_plants_menu);

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.statusBar));
        }

        conifers = findViewById(R.id.conifersButton);
        alpines = findViewById(R.id.alpinesButton);
        bedding = findViewById(R.id.beddingButton);
        climbers = findViewById(R.id.climbersButton);


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
                Intent iiii = new Intent(SelectPlantsMenu.this, ClimbersActivity.class);
                startActivity(iiii);
            }
        });
    }
}
