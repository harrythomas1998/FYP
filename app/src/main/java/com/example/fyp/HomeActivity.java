package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class HomeActivity extends AppCompatActivity {

    Button logOut;
    Button maintainPlnr;
    Button myGarden;
    Button myJobs;
    Button forum;
    ImageView info;

    DatabaseReference reference;
    private FirebaseUser user;
    private FirebaseAuth firebaseAuth;

    String name;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.statusBar));


        logOut = findViewById(R.id.menuButton6);
        maintainPlnr = findViewById(R.id.menuButton2);
        myGarden = findViewById(R.id.menuButton1);
        myJobs = findViewById(R.id.menuButton5);
        forum = findViewById(R.id.menuButton4);

        firebaseAuth = FirebaseAuth.getInstance();

        user = firebaseAuth.getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference().child("SoilType").child(user.getUid());

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
               if (!dataSnapshot.exists()) {
                  openDialog();
               }
           }

           @Override
           public void onCancelled(DatabaseError databaseError) {

           }
        });
        info = findViewById(R.id.info);

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
                Intent intToJobsMenu = new Intent(HomeActivity.this, MyJobsMenu.class);
                startActivity(intToJobsMenu);
            }
        });


        forum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });


    }

    public void openDialog(){

            HomeScreenDialog hsd = new HomeScreenDialog();
            hsd.show(getSupportFragmentManager(), "Soil Dialog");

    }




}
