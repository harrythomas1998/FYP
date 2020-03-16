package com.example.fyp.PlantActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.fyp.Adapters.PlantAdapter;
import com.example.fyp.Objects.Plant;
import com.example.fyp.PlantsActivity;
import com.example.fyp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class BeddingActivity extends AppCompatActivity  implements PlantAdapter.OnItemClickListener {

    RecyclerView recyclerView;
    ArrayList<Plant> list = new ArrayList<>();
    PlantAdapter adapter;
    Button b1;

    public static final String NAME = "name";
    public static final String IMAGE = "image";
    public static final String POSITION = "position";
    public static final String SOIL = "soil";
    public static final String GROWTH = "growth";
    public static final String CARE = "care";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bedding);

        recyclerView = findViewById(R.id.myRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);


        b1 = findViewById(R.id.addButton);


        loadJSONFromAsset();

    }

    public void loadJSONFromAsset() {

        String json;
        try {
            InputStream is = getAssets().open("bedding.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");


            JSONObject obj = new JSONObject(json);
            JSONArray m_jArry = obj.getJSONArray("bedding");



            for (int i = 0; i < m_jArry.length(); i++) {

                JSONObject jo_inside = m_jArry.getJSONObject(i);


                String name = jo_inside.getString("name");
                String image = jo_inside.getString("image-src");
                String position = jo_inside.getString("position");
                String soil = jo_inside.getString("soil");
                String growth = jo_inside.getString("growth");
                String care = jo_inside.getString("care");


                list.add(new Plant(name, image, position, soil, growth, care));


                adapter = new PlantAdapter(BeddingActivity.this, list);
                recyclerView.setAdapter(adapter);
                adapter.setOnItemClickListener(BeddingActivity.this);

            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onItemClick(int position) {

        Intent i = new Intent(this, PlantsActivity.class);
        Plant clickedWeatherItem = list.get(position);

        i.putExtra(NAME, clickedWeatherItem.getName());
        i.putExtra(IMAGE, clickedWeatherItem.getPicture());
        i.putExtra(POSITION, clickedWeatherItem.getPosition());
        i.putExtra(SOIL, clickedWeatherItem.getSoil());
        i.putExtra(GROWTH, clickedWeatherItem.getGrowth());
        i.putExtra(CARE, clickedWeatherItem.getCare());


        startActivity(i);


    }
}
