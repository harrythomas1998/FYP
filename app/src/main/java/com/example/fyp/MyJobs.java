package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;

import com.example.fyp.Adapters.JobAdapter;
import com.example.fyp.Objects.Job;
import com.example.fyp.Objects.Weather;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseUserMetadata;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class MyJobs extends AppCompatActivity implements JobAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private JobAdapter jobAdapter;
    private ArrayList<Job> jobData;
    DatabaseReference reference;
    private FirebaseUser user;
    private FirebaseAuth firebaseAuth;
    private CheckBox checkBox;

    public static final String WEATHER = "weather";
    public static final String TIME = "time";
    public static final String DATE = "date";
    public static final String TEMP = "temp";
    public static final String DESCRIPTION = "description";
    public static final String TITLE = "title";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_jobs);

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.statusBar));
        }


        recyclerView = findViewById(R.id.jobs_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        jobData = new ArrayList<>();

        firebaseAuth = FirebaseAuth.getInstance();

        user = firebaseAuth.getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference().child("Job").child(user.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot1: dataSnapshot.getChildren()){

                    Job job = snapshot1.getValue(Job.class);
                    jobData.add(job);
                }

                jobAdapter = new JobAdapter(MyJobs.this, jobData);
                recyclerView.setAdapter(jobAdapter);
                jobAdapter.setOnItemClickListener(MyJobs.this);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }

    @Override
    public void onItemClick(int position) {

        Intent i = new Intent(this, ViewJob.class);
        Job clickedJobItem = jobData.get(position);

        i.putExtra(WEATHER, clickedJobItem.getWeatherType());
        i.putExtra(TIME, clickedJobItem.getTime());
        i.putExtra(DATE, clickedJobItem.getDate());
        i.putExtra(TEMP, clickedJobItem.getTemp());
        i.putExtra(DESCRIPTION, clickedJobItem.getDescription());
        i.putExtra(TITLE, clickedJobItem.getTitle());

        startActivity(i);


    }



}
