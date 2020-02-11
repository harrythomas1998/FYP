package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fyp.Objects.Job;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import static com.example.fyp.MaintenancePlanner.DATE;
import static com.example.fyp.MaintenancePlanner.TEMP;
import static com.example.fyp.MaintenancePlanner.TIME;
import static com.example.fyp.MaintenancePlanner.WEATHER;

public class JobActivity extends AppCompatActivity {

    Button addJob;
    EditText title, description;
    private String userID;
    Job job;

    ArrayList<Job> jobs;


    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);

        Intent intent = getIntent();
        final String weather = intent.getStringExtra(WEATHER);
        final String time = intent.getStringExtra(TIME);
        final String date = intent.getStringExtra(DATE);
        final Double temp = intent.getDoubleExtra(TEMP, 0);

        TextView weatherTxt = findViewById(R.id.job_weather);
        TextView timeTxt = findViewById(R.id.job_time);
        TextView dateTxt = findViewById(R.id.job_date);
        TextView tempTxt = findViewById(R.id.job_temp);
        title = findViewById(R.id.job_title);
        description = findViewById(R.id.job_description);

        addJob = findViewById(R.id.add_job_button);

        jobs = new ArrayList<>();

        weatherTxt.setText(weather);
        timeTxt.setText(time);
        dateTxt.setText(date);
        tempTxt.setText("" + (Math.round(temp - 273.15)) + "°C");

        job = new Job();
        myRef = FirebaseDatabase.getInstance().getReference().child("Job");



        addJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               job.setDate(date);
               job.setDescription(description.getText().toString());
               job.setTemp(Math.round(temp - 273.15));
               job.setTime(time);
               job.setWeatherType(weather);
               job.setTitle(title.getText().toString());

                myRef.push().setValue(job);

                Toast.makeText(JobActivity.this, "Job has been created", Toast.LENGTH_SHORT).show();


            }
        });

    }



}
