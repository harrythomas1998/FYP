package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.fyp.MyJobs.DATE;
import static com.example.fyp.MyJobs.DESCRIPTION;
import static com.example.fyp.MyJobs.TEMP;
import static com.example.fyp.MyJobs.TIME;
import static com.example.fyp.MyJobs.TITLE;
import static com.example.fyp.MyJobs.WEATHER;

public class ViewJob extends AppCompatActivity {

    TextView timeBox, weatherType, tempBox, descriptionBox, titleBox, dateBox;
    Button edit, markAsDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_job);

        timeBox = findViewById(R.id.view_job_time);
        weatherType = findViewById(R.id.view_job_weather);
        tempBox = findViewById(R.id.view_job_temp);
        descriptionBox = findViewById(R.id.view_job_description);
        titleBox = findViewById(R.id.view_job_title);
        dateBox = findViewById(R.id.view_job_date);


        edit = findViewById(R.id.edit_button);
        markAsDone = findViewById(R.id.mark_as_done_button);

        Intent intent = getIntent();

        final String weather = intent.getStringExtra(WEATHER);
        final String time = intent.getStringExtra(TIME);
        final String date = intent.getStringExtra(DATE);
        final Double temp = intent.getDoubleExtra(TEMP, 0);
        final String title = intent.getStringExtra(TITLE);
        final String description = intent.getStringExtra(DESCRIPTION);

        weatherType.setText(weather);
        timeBox.setText(time);
        dateBox.setText(date);
        tempBox.setText("" + temp + "°C");
        titleBox.setText(title);
        descriptionBox.setText(description);

        markAsDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });



    }
}
