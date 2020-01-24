package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;

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
    Button b1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_soil_type);

        prov = findViewById(R.id.provName);
        s1 = findViewById(R.id.spinner);
        b1 = findViewById(R.id.button);

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

                if(areaSelected.equals("Dublin")){


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




    }
}
