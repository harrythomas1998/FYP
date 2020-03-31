package com.example.fyp.seasons;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.fyp.Adapters.MaintenancePlantAdapter;
import com.example.fyp.ArrayInterface;
import com.example.fyp.Objects.MaintenancePlant;
import com.example.fyp.R;

public class Autumn extends AppCompatActivity implements MaintenancePlantAdapter.OnItemClickListener, ArrayInterface {


    RecyclerView recyclerView;
    MaintenancePlantAdapter adapter;

    String NAME = "name";
    String IMAGE = "image";
    String LINK = "link";
    String CARE = "care";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autumn);

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.statusBar));

        recyclerView = findViewById(R.id.autumn_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);


        adapter = new MaintenancePlantAdapter(Autumn.this, autumn);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(Autumn.this);
    }

    @Override
    public void onItemClick(int position) {

        Intent i = new Intent(this, Autumn.class);
        MaintenancePlant clickedPlantItem = autumn.get(position);

        i.putExtra(NAME, clickedPlantItem.getName());
        i.putExtra(IMAGE, clickedPlantItem.getImage());
        i.putExtra(LINK, clickedPlantItem.getLink());
        i.putExtra(CARE, clickedPlantItem.getCare());

        startActivity(i);

    }

    @Override
    public void onDoneClick(int position) {



    }
}
