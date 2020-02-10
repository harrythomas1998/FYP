package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    Button logOut;
    Button maintainPlnr;
    Button myGarden;
    Button myJobs;
    FirebaseAuth mFireBaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        logOut = findViewById(R.id.menuButton6);
        maintainPlnr = findViewById(R.id.menuButton2);
        myGarden = findViewById(R.id.menuButton1);
        myJobs = findViewById(R.id.menuButton5);


        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();
                Intent intToMain = new Intent(HomeActivity.this, FirstScreen.class);
                startActivity(intToMain);

            }
        });

        maintainPlnr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intToPlanner = new Intent(HomeActivity.this, MaintenancePlanner.class);
                startActivity(intToPlanner);
            }
        });

        myGarden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intToGarden = new Intent(HomeActivity.this, MyGarden.class);
                startActivity(intToGarden);
            }
        });


        myJobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intToGarden = new Intent(HomeActivity.this, MyJobs.class);
                startActivity(intToGarden);
            }
        });





    }
}
