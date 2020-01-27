package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class FindSoilType extends AppCompatActivity {

    EditText prov;
    Spinner s1;
    Button b1, b2;

    String name, ph, fertility, comVeg, climate, drainage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_soil_type);

        prov = findViewById(R.id.provName);
        s1 = findViewById(R.id.spinner);
        b1 = findViewById(R.id.button);
        b2 = findViewById(R.id.button2);

        List<String> leinster = new ArrayList<>();
        leinster.add("- Choose your area -");
        leinster.add("- Choose your Area -");
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

        final ArrayAdapter<String> leinsterAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, leinster);
        leinsterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final ArrayAdapter<String> munsterAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, munster);
        munsterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final ArrayAdapter<String> connaughtAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, connaught);
        connaughtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final ArrayAdapter<String> ulsterAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ulster);
        ulsterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);




        b1.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

               String province = prov.getText().toString();


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
        });


        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String areaSelected = s1.getSelectedItem().toString();

                if(areaSelected.equals("Monaghan- South") || areaSelected.equals("Louth- East") || areaSelected.equals("Mayo- South-East") || areaSelected.equals("Galway- East") || areaSelected.equals("Roscommon- South") || areaSelected.equals("Longford") || areaSelected.equals("Meath") || areaSelected.equals("Westmeath") || areaSelected.equals("Dublin") || areaSelected.equals("Offaly- West") || areaSelected.equals("Kildare- East")
                        || areaSelected.equals("Laois") || areaSelected.equals("Clare- North") || areaSelected.equals("Clare- East") || areaSelected.equals("Tipperary") || areaSelected.equals("Wicklow")
                        || areaSelected.equals("Carlow") || areaSelected.equals("Kilkenny") || areaSelected.equals("Limerick- East") || areaSelected.equals("Waterford") || areaSelected.equals("Cork")){

                    name = "Brown Soil";
                    ph = "Slightly Acidic";
                    fertility = "High";
                    comVeg = "Grassland, Woodland, Farmland";
                    climate = "Humid";
                    drainage = "Very Good";


                }
                else if( areaSelected.equals("Donegal") || areaSelected.equals("Sligo") || areaSelected.equals("Leitrim- North-West") || areaSelected.equals("Mayo- South-West") || areaSelected.equals("Mayo- North") || areaSelected.equals("Galway- West")
                        || areaSelected.equals("Offaly- East") || areaSelected.equals("Kildare- West") || areaSelected.equals("Laois- North-East") || areaSelected.equals("Wicklow- Central") || areaSelected.equals("Wexford- North-West")
                        || areaSelected.equals("Cork- South-West") || areaSelected.equals("Kerry- South")){

                    name = "Peaty Soil";
                    ph = "Strongly Acidic";
                    fertility = "Poor";
                    comVeg = "Bogland";
                    climate = "Humid";
                    drainage = "Poor";


                }

                else if(areaSelected.equals("Leitrim") || areaSelected.equals("Cavan- North") || areaSelected.equals("Monaghan- North") || areaSelected.equals("Mayo- East") || areaSelected.equals("Roscommon- North") || areaSelected.equals("Clare- West") ||
                        areaSelected.equals("Clare- Central") || areaSelected.equals("Limerick- West") || areaSelected.equals("Kerry- North")){

                    name = "Gley Soil";
                    ph = "Acidic";
                    fertility = "High";
                    comVeg = "Bogland, Farmland";
                    climate = "All Cliamtes";
                    drainage = "Poor";

                }
                else{

                    name = "Podzol Soil";
                    ph = "Strongly Acidic";
                    fertility = "Poor";
                    comVeg = "Coniferous Woodland, Grazing";
                    climate = "Humid";
                    drainage = "Poor";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent= new Intent(FindSoilType.this, SoilActivity.class);

                intent.putExtra("NAME", name);
                intent.putExtra("PH", ph);
                intent.putExtra("FERTILITY", fertility);
                intent.putExtra("COMVEG", comVeg);
                intent.putExtra("CLIMATE", climate);
                intent.putExtra("DRAINAGE", drainage);

                startActivity(intent);
            }
        });



    }
}
