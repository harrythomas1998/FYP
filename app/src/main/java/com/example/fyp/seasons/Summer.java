package com.example.fyp.seasons;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.fyp.Adapters.MaintenancePlantAdapter;
import com.example.fyp.ArrayInterface;
import com.example.fyp.MaintenancePlantActivity;
import com.example.fyp.Objects.MaintenancePlant;
import com.example.fyp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.fyp.ArrayInterface.spring;
import static com.example.fyp.ArrayInterface.summer;

public class Summer extends AppCompatActivity implements MaintenancePlantAdapter.OnItemClickListener, ArrayInterface {

    RecyclerView recyclerView;
    MaintenancePlantAdapter adapter;

    String NAME = "name";
    String IMAGE = "image";
    String LINK = "link";
    String CARE = "care";

    DatabaseReference reference;
    private FirebaseUser user;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summer);

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.statusBar));

        recyclerView = findViewById(R.id.summer_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        firebaseAuth = FirebaseAuth.getInstance();

        user = firebaseAuth.getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference().child("SummerJobs").child(user.getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                summer.clear();

                for (DataSnapshot snapshot1 : dataSnapshot.getChildren()) {

                    MaintenancePlant mp = snapshot1.getValue(MaintenancePlant.class);
                    assert mp != null;

                    mp.setKey(snapshot1.getKey());
                    summer.add(mp);

                }

                adapter = new MaintenancePlantAdapter(Summer.this, summer);
                recyclerView.setAdapter(adapter);
                adapter.setOnItemClickListener(Summer.this);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });

    }

    @Override
    public void onItemClick(int position) {

        Intent i = new Intent(this, MaintenancePlantActivity.class);
        MaintenancePlant clickedPlantItem = summer.get(position);

        i.putExtra(NAME, clickedPlantItem.getName());
        i.putExtra(IMAGE, clickedPlantItem.getImage());
        i.putExtra(LINK, clickedPlantItem.getLink());
        i.putExtra(CARE, clickedPlantItem.getCare());

        startActivity(i);

    }

    @Override
    public void onDoneClick(int position) {

        MaintenancePlant selectedJob = summer.get(position);
        String selectedKey = selectedJob.getKey();
        reference.child(selectedKey).removeValue();
        removeItem(position);

        Toast.makeText(this, "Job Removed", Toast.LENGTH_SHORT).show();
    }

    public void removeItem(int position){

        summer.remove(position);
        adapter.notifyDataSetChanged();
    }
}
