package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.fyp.Adapters.ViewPlantsAdapter;
import com.example.fyp.Objects.Plant;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyPlantsActivity extends AppCompatActivity implements ViewPlantsAdapter.OnItemClickListener, ArrayInterface {

    DatabaseReference reference;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;

    private RecyclerView recyclerView;
    private ViewPlantsAdapter adapter;

    public static final String NAME = "name";
    public static final String IMAGE = "image";
    public static final String POSITION = "position";
    public static final String SOIL = "soil";
    public static final String GROWTH = "growth";
    public static final String CARE = "care";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_plants);

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.statusBar));

        recyclerView = findViewById(R.id.view_plants_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        adapter = new ViewPlantsAdapter(MyPlantsActivity.this, myPlants);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(MyPlantsActivity.this);

        reference = FirebaseDatabase.getInstance().getReference().child("MyPlants").child(user.getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                myPlants.clear();

                for(DataSnapshot snapshot1: dataSnapshot.getChildren()){

                    Plant plant = snapshot1.getValue(Plant.class);
                    assert plant != null;
                    plant.setKey(snapshot1.getKey());
                    myPlants.add(plant);

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onItemClick(int position) {

        Intent i = new Intent(this, ViewMyPlant.class);
        Plant clickedPlantItem = myPlants.get(position);

        i.putExtra(NAME, clickedPlantItem.getName());
        i.putExtra(IMAGE, clickedPlantItem.getPicture());
        i.putExtra(POSITION, clickedPlantItem.getPosition());
        i.putExtra(SOIL, clickedPlantItem.getSoil());
        i.putExtra(GROWTH, clickedPlantItem.getGrowth());
        i.putExtra(CARE, clickedPlantItem.getCare());

        startActivity(i);


    }

    @Override
    public void onDeleteClick(int position) {

        Plant selectedPlant = myPlants.get(position);
        String selectedKey = selectedPlant.getKey();
        reference.child(selectedKey).removeValue();
        removeItem(position);

        Toast.makeText(this, "Plant Removed", Toast.LENGTH_SHORT).show();
    }

    public void removeItem(int position){

        myPlants.remove(position);
        adapter.notifyDataSetChanged();
    }


}
