package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.google.firebase.auth.FirebaseAuth;



public class HomeActivity extends AppCompatActivity {

    Button logOut;
    Button maintainPlnr;
    Button myGarden;
    Button myJobs;
    Button forum;
    Button closePopup;
    ImageView info;

    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        logOut = findViewById(R.id.menuButton6);
        maintainPlnr = findViewById(R.id.menuButton2);
        myGarden = findViewById(R.id.menuButton1);
        myJobs = findViewById(R.id.menuButton5);
        forum = findViewById(R.id.menuButton4);


        info = findViewById(R.id.info);
        mContext = getApplicationContext();

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Popup popUpClass = new Popup();
                popUpClass.showPopupWindow(v);

            }
        });


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


        forum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intToForum = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intToForum);

            }
        });




    }




}
