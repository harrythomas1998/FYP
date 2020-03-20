package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fyp.Objects.Plant;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.example.fyp.PlantActivities.ConifersActivity.CARE;
import static com.example.fyp.PlantActivities.ConifersActivity.GROWTH;
import static com.example.fyp.PlantActivities.ConifersActivity.IMAGE;
import static com.example.fyp.PlantActivities.ConifersActivity.NAME;
import static com.example.fyp.PlantActivities.ConifersActivity.POSITION;
import static com.example.fyp.PlantActivities.ConifersActivity.SOIL;

public class PlantsActivity extends AppCompatActivity implements ArrayInterface{

    TextView nameBox, positionBox, soilBox, growthBox, careBox;
    ImageView imageBox;
    Button add;

    private FirebaseUser mCurrentUser;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference myRef;

    Plant plant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plants);

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.statusBar));


        nameBox = findViewById(R.id.plant_view_name);
        imageBox = findViewById(R.id.plant_view_image);
        positionBox = findViewById(R.id.plant_view_position);
        soilBox = findViewById(R.id.plant_view_soil);
        growthBox = findViewById(R.id.plant_view_growth);
        careBox = findViewById(R.id.plant_view_care);
        add = findViewById(R.id.add_to_my_plants_button);

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

        plant = new Plant();


        firebaseAuth = FirebaseAuth.getInstance();
        mCurrentUser = firebaseAuth.getCurrentUser();
        myRef = FirebaseDatabase.getInstance().getReference().child("MyPlants").child(mCurrentUser.getUid());

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                plant.setName(name);
                plant.setPicture(image);
                plant.setPosition(position);
                plant.setSoil(soil);
                plant.setGrowth(growth);
                plant.setCare(care);

                myRef.push().setValue(plant);

                //conifers.remove(plant);

                Toast.makeText(PlantsActivity.this, "Plant has been added to your Plants", Toast.LENGTH_LONG).show();
            }
        });

    }
}
