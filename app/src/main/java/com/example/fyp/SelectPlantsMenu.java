package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fyp.plants.ConifersActivity;

public class SelectPlantsMenu extends AppCompatActivity {

    Button conifers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_plants_menu);

        conifers = findViewById(R.id.conifersButton);




        conifers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(SelectPlantsMenu.this, ConifersActivity.class);
                startActivity(i);
            }
        });
    }
}
