package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.example.fyp.MyJobs.DATE;
import static com.example.fyp.MyJobs.DESCRIPTION;
import static com.example.fyp.MyJobs.TEMP;
import static com.example.fyp.MyJobs.TIME;
import static com.example.fyp.MyJobs.TITLE;
import static com.example.fyp.MyJobs.WEATHER;
import static com.example.fyp.PlantActivities.ConifersActivity.CARE;
import static com.example.fyp.PlantActivities.ConifersActivity.GROWTH;
import static com.example.fyp.PlantActivities.ConifersActivity.IMAGE;
import static com.example.fyp.PlantActivities.ConifersActivity.NAME;
import static com.example.fyp.PlantActivities.ConifersActivity.POSITION;
import static com.example.fyp.PlantActivities.ConifersActivity.SOIL;

public class PlantsActivity extends AppCompatActivity {

    TextView nameBox, positionBox, soilBox, growthBox, careBox;
    ImageView imageBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plants);

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.statusBar));
        }

        nameBox = findViewById(R.id.plant_view_name);
        imageBox = findViewById(R.id.plant_view_image);
        positionBox = findViewById(R.id.plant_view_position);
        soilBox = findViewById(R.id.plant_view_soil);
        growthBox = findViewById(R.id.plant_view_growth);
        careBox = findViewById(R.id.plant_view_care);

        Intent intent = getIntent();

        final String name = intent.getStringExtra(NAME);
        final String image = intent.getStringExtra(IMAGE);
        final String position = intent.getStringExtra(POSITION);
        final String soil = intent.getStringExtra(SOIL);
        final String growth= intent.getStringExtra(GROWTH);
        final String care = intent.getStringExtra(CARE);

        nameBox.setText(name);
        Picasso.get().load(image).fit().into(imageBox);
        positionBox.setText(position);
        soilBox.setText(soil);
        growthBox.setText(growth);
        careBox.setText(care);

    }
}
