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

import com.example.fyp.Objects.Job;
import com.example.fyp.Objects.MaintenancePlant;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import static com.example.fyp.ArrayInterface.summer;
import static com.example.fyp.ArrayInterface.winter;
import static com.example.fyp.PlantActivities.ConifersActivity.CARE;
import static com.example.fyp.PlantActivities.ConifersActivity.GROWTH;
import static com.example.fyp.PlantActivities.ConifersActivity.IMAGE;
import static com.example.fyp.PlantActivities.ConifersActivity.NAME;
import static com.example.fyp.PlantActivities.ConifersActivity.POSITION;
import static com.example.fyp.PlantActivities.ConifersActivity.SOIL;

public class ViewMyPlant extends AppCompatActivity {

    TextView nameBox, positionBox, soilBox, growthBox, careBox;
    ImageView imageBox;

    Button b1;

    MaintenancePlant mp;

    private FirebaseUser mCurrentUser;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference myRef;

    Job job;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_my_plant);

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.statusBar));

        b1 = findViewById(R.id.add_maintenance_button);

        nameBox = findViewById(R.id.my_plant_view_name);
        positionBox = findViewById(R.id.my_plant_view_position);
        soilBox = findViewById(R.id.my_plant_view_soil);
        growthBox = findViewById(R.id.my_plant_view_growth);
        careBox = findViewById(R.id.my_plant_view_care);
        imageBox = findViewById(R.id.my_plant_view_image);

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

        firebaseAuth = FirebaseAuth.getInstance();
        mCurrentUser = firebaseAuth.getCurrentUser();
        myRef = FirebaseDatabase.getInstance().getReference().child("MaintenancePlants").child(mCurrentUser.getUid());

        mp = new MaintenancePlant();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               if(care.contains("summer")){

                   summer.add(mp);
               }
               if(care.contains("winter")){
                   winter.add(mp);
               }
                if(care.contains("spring")){
                    winter.add(mp);
                }
                if(care.contains("autumn")){
                    winter.add(mp);
                }
                Toast.makeText(ViewMyPlant.this, "Added to Jobs!", Toast.LENGTH_SHORT).show();
            }
        });




    }
}
