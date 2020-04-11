package com.example.fyp.Halfshade;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.fyp.Adapters.PlantAdapter;
import com.example.fyp.ArrayInterface;
import com.example.fyp.Objects.Plant;
import com.example.fyp.PlantsActivity;
import com.example.fyp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class HalfExotics extends AppCompatActivity implements PlantAdapter.OnItemClickListener, ArrayInterface {

    RecyclerView recyclerView;
    PlantAdapter adapter;
    SearchView search;

    private FirebaseUser mCurrentUser;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference myRef;

    public static final String NAME = "name";
    public static final String IMAGE = "image";
    public static final String POSITION = "position";
    public static final String SOIL = "soil";
    public static final String GROWTH = "growth";
    public static final String CARE = "care";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_half_exotics);

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.statusBar));

        recyclerView = findViewById(R.id.halfExoticsRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        search = findViewById(R.id.searchHalfExotics);

        loadJSONFromAsset();

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });


    }

    public void loadJSONFromAsset() {

        halfExoticsList.clear();

        String json;
        try {
            InputStream is = getAssets().open("exotic.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);

            JSONObject obj = new JSONObject(json);
            JSONArray m_jArry = obj.getJSONArray("exotic");

            for (int i = 0; i < m_jArry.length(); i++) {

                JSONObject jo_inside = m_jArry.getJSONObject(i);

                String name = jo_inside.getString("name");
                String image = jo_inside.getString("image-src");
                String position = jo_inside.getString("position");
                String soil = jo_inside.getString("soil");
                String growth = jo_inside.getString("growth");
                String care = jo_inside.getString("care");

                if(position.contains("partial") || position.contains("lightly dappled") || position.contains("light, dappled") || position.contains("Partial Shade") || position.contains("Lightly Dappled") || position.contains("little") || position.contains("dappled")) {

                    halfExoticsList.add(new Plant(name, image, position, soil, growth, care));

                }
                adapter = new PlantAdapter(HalfExotics.this, halfExoticsList);
                recyclerView.setAdapter(adapter);
                adapter.setOnItemClickListener(HalfExotics.this);

            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onItemClick(int position) {

        Intent i = new Intent(this, PlantsActivity.class);
        Plant clickedPlantItem = halfExoticsList.get(position);

        i.putExtra(NAME, clickedPlantItem.getName());
        i.putExtra(IMAGE, clickedPlantItem.getPicture());
        i.putExtra(POSITION, clickedPlantItem.getPosition());
        i.putExtra(SOIL, clickedPlantItem.getSoil());
        i.putExtra(GROWTH, clickedPlantItem.getGrowth());
        i.putExtra(CARE, clickedPlantItem.getCare());

        startActivity(i);

    }

    @Override
    public void onAddClick(int position) {

        Plant selectedPlant = halfExoticsList.get(position);

        firebaseAuth = FirebaseAuth.getInstance();
        mCurrentUser = firebaseAuth.getCurrentUser();
        myRef = FirebaseDatabase.getInstance().getReference().child("MyPlants").child(mCurrentUser.getUid());

        myRef.push().setValue(selectedPlant);

        Toast.makeText(this, "Plant Added!", Toast.LENGTH_SHORT).show();
    }
}
