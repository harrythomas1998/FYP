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

import java.util.ArrayList;
import java.util.List;

public class SoilActivity extends AppCompatActivity {


    Button b1;
    TextView soilType, ph, fertility, vegetation, climate, drainage;

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


        Intent intent = getIntent();

        soilType.setText(intent.getStringExtra("NAME"));
        ph.setText(intent.getStringExtra("PH"));
        fertility.setText(intent.getStringExtra("FERTILITY"));
        vegetation.setText(intent.getStringExtra("COMVEG"));
        climate.setText(intent.getStringExtra("CLIMATE"));
        drainage.setText(intent.getStringExtra("DRAINAGE"));


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(SoilActivity.this, FindSoilType.class);
                startActivity(i);
            }
        });

    }
}
