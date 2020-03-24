package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MyGarden extends AppCompatActivity {

    Button soilB;
    Button plantsB;
    Button addPlants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_garden);

            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.statusBar));


        soilB = findViewById(R.id.soilButton);
        plantsB = findViewById(R.id.plantsButton);
        addPlants = findViewById(R.id.addPlantsButton);


        soilB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MyGarden.this, GardenInfoActivity.class);
                startActivity(i);


            }
        });

        plantsB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MyGarden.this, MyPlantsActivity.class);
                startActivity(i);


            }
        });

        addPlants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MyGarden.this, SelectPlantsMenu.class);
                startActivity(i);

            }
        });




    }
}
