package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fyp.Objects.Job;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import static com.example.fyp.MaintenancePlanner.DATE;
import static com.example.fyp.MaintenancePlanner.TEMP;
import static com.example.fyp.MaintenancePlanner.TIME;
import static com.example.fyp.MaintenancePlanner.WEATHER;

public class JobActivity extends AppCompatActivity implements ArrayInterface {

    Button addJob;
    EditText title, description;
    Job job;
    private FirebaseUser mCurrentUser;
    private FirebaseAuth firebaseAuth;



    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);

            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.statusBar));


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

        weatherTxt.setText(weather);
        timeTxt.setText(time);
        dateTxt.setText(date);
        tempTxt.setText("" + (Math.round(temp - 273.15)) + "°C");

        job = new Job();

        firebaseAuth = FirebaseAuth.getInstance();
        mCurrentUser = firebaseAuth.getCurrentUser();
        myRef = FirebaseDatabase.getInstance().getReference().child("Job").child(mCurrentUser.getUid());



        addJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(title.getText().toString().isEmpty() || description.getText().toString().isEmpty()){

                    Toast.makeText(JobActivity.this, "Fields cannot be empty", Toast.LENGTH_LONG).show();
                }
                else{
                    job.setDate(date);
                    job.setDescription(description.getText().toString());
                    job.setTemp(Math.round(temp - 273.15));
                    job.setTime(time);
                    job.setWeatherType(weather);
                    job.setTitle(title.getText().toString());

                    myRef.push().setValue(job);
                    Toast.makeText(JobActivity.this, "Job has been created", Toast.LENGTH_LONG).show();

                }
                

            }
        });

    }



}
