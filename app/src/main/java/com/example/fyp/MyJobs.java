package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.RequestQueue;

import java.util.ArrayList;

public class MyJobs extends AppCompatActivity {

    private RecyclerView recyclerView;
    private JobAdapter jobAdapter;
    private ArrayList<Job> jobData;

    JobActivity jobActivity = new JobActivity();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_jobs);


        recyclerView = findViewById(R.id.jobs_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        jobData = new ArrayList<>();

        jobAdapter = new JobAdapter(this, getJobData());
        recyclerView.setAdapter(jobAdapter);


    }


    private ArrayList<Job> getJobData() {

        for(Job job : jobActivity.getJobs()){

            jobData.add(job);
        }

        return jobData;
    }
}
