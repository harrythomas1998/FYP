package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.fyp.Objects.Orientation;
import com.example.fyp.Objects.SoilType;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class FindSoilType extends AppCompatActivity {


    private Spinner s1, s2, s3;
    private Button b2;

    private SoilType soilType;
    private Orientation or;

    private FirebaseUser mCurrentUser;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference myRef, myRef2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_soil_type);


            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.statusBar));

        s3 = findViewById(R.id.spinnerOrientation);
        s2 = findViewById(R.id.spinnerProvince);
        s1 = findViewById(R.id.spinnerArea);
        b2 = findViewById(R.id.button);

        List<String> orientations = new ArrayList<>();
        orientations.add("-Choose your Orientation-");
        orientations.add("North");
        orientations.add("South");
        orientations.add("East");
        orientations.add("West");


        List<String> provinces = new ArrayList<>();
        provinces.add("-Choose your Province-");
        provinces.add("Leinster");
        provinces.add("Munster");
        provinces.add("Ulster");
        provinces.add("Connaught");

        List<String> leinster = new ArrayList<>();
        leinster.add("- Choose your area -");
        leinster.add("Louth- West");
        leinster.add("Louth- East");
        leinster.add("Longford");
        leinster.add("Meath");
        leinster.add("Westmeath");
        leinster.add("Dublin");
        leinster.add("Offaly- East");
        leinster.add("Offaly- West");
        leinster.add("Kildare- East");
        leinster.add("Kildare- West");
        leinster.add("Laois- North-East");
        leinster.add("Laois- Central-West");
        leinster.add("Laois");
        leinster.add("Wicklow- Central");
        leinster.add("Wicklow");
        leinster.add("Carlow");
        leinster.add("Wexford- North-West");
        leinster.add("Wexford");
        leinster.add("Kilkenny- Central");
        leinster.add("Kilkenny");

        List<String> munster = new ArrayList<>();
        munster.add("- Choose your area -");
        munster.add("Clare- West");
        munster.add("Clare- Central");
        munster.add("Clare- North");
        munster.add("Clare- East");
        munster.add("Tipperary");
        munster.add("Limerick- West");
        munster.add("Limerick- East");
        munster.add("Waterford");
        munster.add("Cork- Central-West");
        munster.add("Cork- South-West");
        munster.add("Cork");
        munster.add("Kerry- North");
        munster.add("Kerry- South");

        List<String> connaught = new ArrayList<>();
        connaught.add("- Choose your area -");
        connaught.add("Sligo");
        connaught.add("Leitrim");
        connaught.add("Leitrim- North-West");
        connaught.add("Mayo- Central");
        connaught.add("Mayo- South-West");
        connaught.add("Mayo- North");
        connaught.add("Mayo- South-East");
        connaught.add("Mayo- East");
        connaught.add("Galway- West");
        connaught.add("Galway- East");
        connaught.add("Roscommon- North");
        connaught.add("Roscommon- South");

        List<String> ulster = new ArrayList<>();
        ulster.add("- Choose your area -");
        ulster.add("Donegal");
        ulster.add("Cavan- North");
        ulster.add("Cavan- South");
        ulster.add("Monaghan- North");
        ulster.add("Monaghan- South");

        ArrayAdapter<String> provinceAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, provinces);
        provinceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s2.setAdapter(provinceAdapter);

        ArrayAdapter<String> orientationAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, orientations);
        orientationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s3.setAdapter(orientationAdapter);

        final ArrayAdapter<String> leinsterAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, leinster);
        leinsterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final ArrayAdapter<String> munsterAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, munster);
        munsterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final ArrayAdapter<String> connaughtAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, connaught);
        connaughtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final ArrayAdapter<String> ulsterAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ulster);
        ulsterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        s3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String orientation = s3.getSelectedItem().toString();

                or.setOrientation(orientation);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String province = s2.getSelectedItem().toString();


                if(province.equalsIgnoreCase("Leinster")){

                    s1.setAdapter(leinsterAdapter);
                }
                else if(province.equalsIgnoreCase("Munster")){

                    s1.setAdapter(munsterAdapter);
                }
                else if(province.equalsIgnoreCase("Connaught")){

                    s1.setAdapter(connaughtAdapter);
                }
                else if(province.equalsIgnoreCase("Ulster")){

                    s1.setAdapter(ulsterAdapter);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String areaSelected = s1.getSelectedItem().toString();

                if(areaSelected.equals("Monaghan- South") || areaSelected.equals("Louth- East") || areaSelected.equals("Mayo- South-East") || areaSelected.equals("Galway- East") || areaSelected.equals("Roscommon- South") || areaSelected.equals("Longford") || areaSelected.equals("Meath") || areaSelected.equals("Westmeath") || areaSelected.equals("Dublin") || areaSelected.equals("Offaly- West") || areaSelected.equals("Kildare- East")
                        || areaSelected.equals("Laois") || areaSelected.equals("Clare- North") || areaSelected.equals("Clare- East") || areaSelected.equals("Tipperary") || areaSelected.equals("Wicklow")
                        || areaSelected.equals("Carlow") || areaSelected.equals("Kilkenny") || areaSelected.equals("Limerick- East") || areaSelected.equals("Waterford") || areaSelected.equals("Cork")){

                    soilType.setName("Brown Soil");
                    soilType.setPh("Slightly Acidic");
                    soilType.setFertility("High");
                    soilType.setComVeg("Grassland, Woodland, Farmland");
                    soilType.setClimate("Humid");
                    soilType.setDrainage("Very Good");


                }
                else if( areaSelected.equals("Donegal") || areaSelected.equals("Sligo") || areaSelected.equals("Leitrim- North-West") || areaSelected.equals("Mayo- South-West") || areaSelected.equals("Mayo- North") || areaSelected.equals("Galway- West")
                        || areaSelected.equals("Offaly- East") || areaSelected.equals("Kildare- West") || areaSelected.equals("Laois- North-East") || areaSelected.equals("Wicklow- Central") || areaSelected.equals("Wexford- North-West")
                        || areaSelected.equals("Cork- South-West") || areaSelected.equals("Kerry- South")){

                    soilType.setName("Peaty Soil");
                    soilType.setPh("Strongly Acidic");
                    soilType.setFertility("Poor");
                    soilType.setComVeg("Bogland");
                    soilType.setClimate("Humid");
                    soilType.setDrainage("Poor");


                }

                else if(areaSelected.equals("Leitrim") || areaSelected.equals("Cavan- North") || areaSelected.equals("Monaghan- North") || areaSelected.equals("Mayo- East") || areaSelected.equals("Roscommon- North") || areaSelected.equals("Clare- West") ||
                        areaSelected.equals("Clare- Central") || areaSelected.equals("Limerick- West") || areaSelected.equals("Kerry- North")){

                    soilType.setName("Gley Soil");
                    soilType.setPh("Acidic");
                    soilType.setFertility("High");
                    soilType.setComVeg("Bogland, Farmland");
                    soilType.setClimate("All Cliamtes");
                    soilType.setDrainage("Poor");

                }
                else{

                    soilType.setName("Podzol Soil");
                    soilType.setPh("Strongly Acidic");
                    soilType.setFertility("Poor");
                    soilType.setComVeg("Coniferous Woodland, Grazing");
                    soilType.setClimate("Humid");
                    soilType.setDrainage("Poor");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        soilType = new SoilType();
        or = new Orientation();

        firebaseAuth = FirebaseAuth.getInstance();
        mCurrentUser = firebaseAuth.getCurrentUser();
        myRef = FirebaseDatabase.getInstance().getReference().child("SoilType").child(mCurrentUser.getUid());
        myRef2 = FirebaseDatabase.getInstance().getReference().child("Orientation").child(mCurrentUser.getUid());


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                myRef.removeValue();
                myRef.push().setValue(soilType);

                myRef2.removeValue();
                myRef2.push().setValue(or);

                Intent intent= new Intent(FindSoilType.this, GardenInfoActivity.class);

                startActivity(intent);
            }
        });



    }
}
