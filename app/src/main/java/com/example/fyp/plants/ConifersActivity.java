package com.example.fyp.plants;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.example.fyp.Adapters.PlantAdapter;
import com.example.fyp.Objects.Plant;
import com.example.fyp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

public class ConifersActivity extends AppCompatActivity {

    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<Plant> list = new ArrayList<>();
    PlantAdapter adapter;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conifers);

        recyclerView = findViewById(R.id.myRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);


        b1 = findViewById(R.id.addButton);

        /*reference = FirebaseDatabase.getInstance().getReference().child("conifers");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot1: dataSnapshot.getChildren()){

                    Plant p = snapshot1.getValue(Plant.class);
                    list.add(p);
                }

                adapter = new PlantAdapter(ConifersActivity.this, list);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        }); */


        loadJSONFromAsset();

    }

    public void loadJSONFromAsset() {

        String json;
        try {
            InputStream is = getAssets().open("conifers.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");


            JSONObject obj = new JSONObject(json);
            JSONArray m_jArry = obj.getJSONArray("conifers");



            for (int i = 0; i < m_jArry.length(); i++) {

                JSONObject jo_inside = m_jArry.getJSONObject(i);


                String name = jo_inside.getString("name");
                String image = jo_inside.getString("image-src");
                String position = jo_inside.getString("position");
                String soil = jo_inside.getString("soil");
                String growth = jo_inside.getString("growth");
                String care = jo_inside.getString("care");


                list.add(new Plant(name, image, position, soil, growth, care));


                adapter = new PlantAdapter(ConifersActivity.this, list);
                recyclerView.setAdapter(adapter);

            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
