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

import com.example.fyp.Objects.MaintenancePlant;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

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
    private DatabaseReference autumnRef, springRef, summerRef, winterRef, otherRef;


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
        positionBox.setText(position.replace("Position:", ""));
        soilBox.setText(soil.replace("Soil:", ""));
        growthBox.setText(growth.replace("Rate of growth:", ""));
        careBox.setText(care.replace("Garden Care:", ""));

        firebaseAuth = FirebaseAuth.getInstance();
        mCurrentUser = firebaseAuth.getCurrentUser();
        autumnRef = FirebaseDatabase.getInstance().getReference().child("AutumnPlants").child(mCurrentUser.getUid());
        springRef = FirebaseDatabase.getInstance().getReference().child("SpringPlants").child(mCurrentUser.getUid());
        winterRef = FirebaseDatabase.getInstance().getReference().child("WinterPlants").child(mCurrentUser.getUid());
        summerRef = FirebaseDatabase.getInstance().getReference().child("SummerPlants").child(mCurrentUser.getUid());
        otherRef = FirebaseDatabase.getInstance().getReference().child("OtherPlants").child(mCurrentUser.getUid());


        mp = new MaintenancePlant();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                assert care != null;
                if(careBox.getText().toString().contains("summer") || careBox.getText().toString().contains("Summer") || careBox.getText().toString().contains("may") || careBox.getText().toString().contains("May") || careBox.getText().toString().contains("June") || careBox.getText().toString().contains("june")
                        || careBox.getText().toString().contains("July") || careBox.getText().toString().contains("july")){

                    mp.setName(nameBox.getText().toString());
                    mp.setCare(careBox.getText().toString());
                    mp.setImage(image);
                    mp.setLink("https://www.google.com/search?q="+ name +"+maintenance&rlz=1C5CHFA_enIE887IE891&oq="+ name +"+maintena&aqs=chrome.0.0j69i57j0l6.7602j1j7&sourceid=chrome&ie=UTF-8");

                    summerRef.push().setValue(mp);

                    Toast.makeText(ViewMyPlant.this, "Job added to Summer Jobs", Toast.LENGTH_LONG).show();
               }
               else if(careBox.getText().toString().contains("winter") || careBox.getText().toString().contains("Winter") || careBox.getText().toString().contains("january") || careBox.getText().toString().contains("January") || careBox.getText().toString().contains("December") || careBox.getText().toString().contains("december")
                        || careBox.getText().toString().contains("November") || careBox.getText().toString().contains("november")){

                    mp.setName(nameBox.getText().toString());
                    mp.setCare(careBox.getText().toString());
                    mp.setImage(image);
                    mp.setLink("https://www.google.com/search?q="+ name +"+maintenance&rlz=1C5CHFA_enIE887IE891&oq="+ name +"+maintena&aqs=chrome.0.0j69i57j0l6.7602j1j7&sourceid=chrome&ie=UTF-8");

                    winterRef.push().setValue(mp);

                    Toast.makeText(ViewMyPlant.this, "Job added to Winter Jobs", Toast.LENGTH_LONG).show();
               }
               else if(careBox.getText().toString().contains("spring") || careBox.getText().toString().contains("Spring") || careBox.getText().toString().contains("February") || careBox.getText().toString().contains("february") || careBox.getText().toString().contains("March") || careBox.getText().toString().contains("march")
                        || careBox.getText().toString().contains("April") || careBox.getText().toString().contains("april")){

                    mp.setName(nameBox.getText().toString());
                    mp.setCare(careBox.getText().toString());
                    mp.setImage(image);
                    mp.setLink("https://www.google.com/search?q="+ name +"+maintenance&rlz=1C5CHFA_enIE887IE891&oq="+ name +"+maintena&aqs=chrome.0.0j69i57j0l6.7602j1j7&sourceid=chrome&ie=UTF-8");

                    springRef.push().setValue(mp);

                    Toast.makeText(ViewMyPlant.this, "Job added to Spring Jobs", Toast.LENGTH_LONG).show();
               }
               else if(careBox.getText().toString().contains("autumn") || careBox.getText().toString().contains("Autumn") || careBox.getText().toString().contains("August") || careBox.getText().toString().contains("august") || careBox.getText().toString().contains("September") || careBox.getText().toString().contains("september")
                        || careBox.getText().toString().contains("October") || careBox.getText().toString().contains("october")){

                    mp.setName(nameBox.getText().toString());
                    mp.setCare(careBox.getText().toString());
                    mp.setImage(image);
                    mp.setLink("https://www.google.com/search?q="+ name +"+maintenance&rlz=1C5CHFA_enIE887IE891&oq="+ name +"+maintena&aqs=chrome.0.0j69i57j0l6.7602j1j7&sourceid=chrome&ie=UTF-8");

                    autumnRef.push().setValue(mp);

                    Toast.makeText(ViewMyPlant.this, "Job added to Autumn Jobs", Toast.LENGTH_LONG).show();
               }
               else{

                    mp.setName(nameBox.getText().toString());
                    mp.setCare(careBox.getText().toString());
                    mp.setImage(image);
                    mp.setLink("https://www.google.com/search?q="+ name +"+maintenance&rlz=1C5CHFA_enIE887IE891&oq="+ name +"+maintena&aqs=chrome.0.0j69i57j0l6.7602j1j7&sourceid=chrome&ie=UTF-8");

                    otherRef.push().setValue(mp);

                    Toast.makeText(ViewMyPlant.this, "Job added to Other Jobs", Toast.LENGTH_LONG).show();

                }



            }
        });




    }
}
