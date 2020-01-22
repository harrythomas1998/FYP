package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SoilActivity extends AppCompatActivity {

    Spinner spin1;
    TextView soilType, ph, fertility, vegetation, climate, drainage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soil);

        spin1 = findViewById(R.id.soilDropDown);

        soilType = findViewById(R.id.soilType);
        ph = findViewById(R.id.ph);
        fertility = findViewById(R.id.fertility);
        vegetation = findViewById(R.id.vegetation);
        climate = findViewById(R.id.climate);
        drainage = findViewById(R.id.drainage);


        List<String> soils = new ArrayList<>();
        soils.add("- Choose your Soil Type -");
        soils.add("Brown Soil");
        soils.add("Gley Soil");
        soils.add("Podzol Soil");
        soils.add("Peaty Soil");

        ArrayAdapter<String> soilAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, soils);
        soilAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin1.setAdapter(soilAdapter);


        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String soilSelected = spin1.getSelectedItem().toString();

                if(soilSelected.equals("Brown Soil")){

                    soilType.setText("Brown Soil");
                    ph.setText("Slighty Acidic");
                    fertility.setText("Very High");
                    vegetation.setText("GrassLand, Woodland, Farmland");
                    climate.setText("Humid");
                    drainage.setText("Very Good");
                }

                else if(soilSelected.equals("Gley Soil")){

                    soilType.setText("Gley Soil");
                    ph.setText("Acidic");
                    fertility.setText("Very High");
                    vegetation.setText("Bogland, Farmland");
                    climate.setText("All Climates");
                    drainage.setText("Poor");
                }

                else if(soilSelected.equals("Podzol Soil")){

                    soilType.setText("Podzol Soil");
                    ph.setText("Strongly Acidic");
                    fertility.setText("Poor");
                    vegetation.setText("Coniferous Woodland, Grazing");
                    climate.setText("Humid");
                    drainage.setText("Poor");
                }

                else if(soilSelected.equals("Peaty Soil")){

                    soilType.setText("Peaty Soil");
                    ph.setText("Strongly Acidic");
                    fertility.setText("Poor");
                    vegetation.setText("Bogland");
                    climate.setText("Humid");
                    drainage.setText("Poor");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
