package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fyp.plants.ConifersActivity;

public class MyGarden extends AppCompatActivity {

    Button soilB;
    Button plantsB;
    Button addPlants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_garden);

        soilB = findViewById(R.id.soilButton);
        plantsB = findViewById(R.id.plantsButton);
        addPlants = findViewById(R.id.addPlantsButton);


        soilB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MyGarden.this, SoilActivity.class);
                startActivity(i);


            }
        });

        plantsB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


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
