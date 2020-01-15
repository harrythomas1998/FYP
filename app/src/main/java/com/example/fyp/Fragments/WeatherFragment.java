package com.example.fyp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fyp.Adapters.WeatherAdapter;
import com.example.fyp.CreateEvent;
import com.example.fyp.MaintenancePlanner;
import com.example.fyp.R;
import com.example.fyp.Weather;
import com.example.fyp.WeatherParser;


import java.util.ArrayList;
import java.util.List;

public class WeatherFragment extends Fragment {

    private RecyclerView recyclerView;

    private List<Weather> mWeathers;

    private WeatherAdapter weatherAdapter;

    MaintenancePlanner mp;

    WeatherParser weatherParser;

    Button create_event;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.fragment_weather, container, false);

        recyclerView = view.findViewById(R.id.weather_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mWeathers = new ArrayList<>();

        create_event = view.findViewById(R.id.add_button);



        readWeatherObjects();

        create_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), CreateEvent.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void readWeatherObjects() {

        for (Weather weather : weatherParser.weathers()) {

                mWeathers.add(weather);

        }

        weatherAdapter = new WeatherAdapter(getContext(), mWeathers);
        recyclerView.setAdapter(weatherAdapter);



    }


}
