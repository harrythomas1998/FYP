package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.fyp.Adapters.JobAdapter;
import com.example.fyp.Objects.Job;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class MyJobs extends AppCompatActivity {

    private RecyclerView recyclerView;
    private JobAdapter jobAdapter;
    private ArrayList<Job> jobData;
    DatabaseReference reference;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_jobs);


        recyclerView = findViewById(R.id.jobs_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        jobData = new ArrayList<>();


        reference = FirebaseDatabase.getInstance().getReference().child("Job");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot1: dataSnapshot.getChildren()){

                    Job job = snapshot1.getValue(Job.class);
                    jobData.add(job);
                }

                jobAdapter = new JobAdapter(MyJobs.this, jobData);
                recyclerView.setAdapter(jobAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





    }



}
