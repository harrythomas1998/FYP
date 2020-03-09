package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.fyp.Objects.SoilType;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SoilActivity extends AppCompatActivity {


    Button b1;
    TextView soilType, ph, fertility, vegetation, climate, drainage;

    DatabaseReference reference;
    private FirebaseUser user;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soil);


        b1 = findViewById(R.id.findSoilBtn);


        soilType = findViewById(R.id.soilType);
        ph = findViewById(R.id.ph);
        fertility = findViewById(R.id.fertility);
        vegetation = findViewById(R.id.vegetation);
        climate = findViewById(R.id.climate);
        drainage = findViewById(R.id.drainage);


        firebaseAuth = FirebaseAuth.getInstance();

        user = firebaseAuth.getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference().child("SoilType").child(user.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot1: dataSnapshot.getChildren()){

                    SoilType soil = snapshot1.getValue(SoilType.class);

                    soilType.setText(soil.getName());
                    ph.setText(soil.getPh());
                    fertility.setText(soil.getFertility());
                    vegetation.setText(soil.getComVeg());
                    climate.setText(soil.getClimate());
                    drainage.setText(soil.getDrainage());


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(SoilActivity.this, FindSoilType.class);
                startActivity(i);
            }
        });

    }
}
