package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.fyp.PlantActivities.GrassesActivity;

public class MaintenancePlannerMenu extends AppCompatActivity {

    Button b1,b2, b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_planner_menu);

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.statusBar));

        b1 = findViewById(R.id.grassesButton);
        b2 = findViewById(R.id.toWeatherButton);
        b3 = findViewById(R.id.toPlantsButton);



        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intToPlanner = new Intent(MaintenancePlannerMenu.this, MaintenancePlanner.class);
                startActivity(intToPlanner);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intToPlanner = new Intent(MaintenancePlannerMenu.this, MyPlantsActivity.class);
                startActivity(intToPlanner);
            }
        });


    }
}
