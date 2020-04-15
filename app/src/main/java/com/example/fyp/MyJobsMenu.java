package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.fyp.seasons.Autumn;
import com.example.fyp.seasons.Spring;
import com.example.fyp.seasons.Summer;
import com.example.fyp.seasons.Winter;

public class MyJobsMenu extends AppCompatActivity {

    Button b1, b2, b3, b4, b5, b6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_jobs_menu);

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.statusBar));


        b1 = findViewById(R.id.reoccuring_button);
        b2 = findViewById(R.id.created_by_me_button);
        b3 = findViewById(R.id.spring_button);
        b4 = findViewById(R.id.summer_button);
        b5 = findViewById(R.id.autumn_button);
        b6 = findViewById(R.id.winter_button);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MyJobsMenu.this, MyJobs.class);
                startActivity(i);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MyJobsMenu.this, MyJobs.class);
                startActivity(i);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MyJobsMenu.this, Spring.class);
                startActivity(i);
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MyJobsMenu.this, Summer.class);
                startActivity(i);
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MyJobsMenu.this, Autumn.class);
                startActivity(i);
            }
        });

        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MyJobsMenu.this, Winter.class);
                startActivity(i);
            }
        });

    }
}
