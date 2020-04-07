package com.example.fyp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fyp.Adapters.WeatherAdapter;
import com.example.fyp.Objects.Weather;

public class FragmentTuesday extends Fragment implements ArrayInterface, WeatherAdapter.OnItemClickListener{

    View v;
    private RecyclerView recyclerView;

    public static final String WEATHER = "weather";
    public static final String TIME = "time";
    public static final String DATE = "date";
    public static final String TEMP = "temp";


    public FragmentTuesday() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.tuesday_fragment, container, false);
        recyclerView = v.findViewById(R.id.tuesday_recycler);
        WeatherAdapter weatherAdapter = new WeatherAdapter(getContext(), tuesdayData);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(weatherAdapter);
        weatherAdapter.setOnItemClickListener(FragmentTuesday.this);
        recyclerView.setHasFixedSize(true);
        return v;
    }

    @Override
    public void onItemClick(int position) {

        Intent i = new Intent(getActivity(), JobActivity.class);
        Weather clickedWeatherItem = tuesdayData.get(position);

        i.putExtra(WEATHER, clickedWeatherItem.getWeatherType());
        i.putExtra(TIME, clickedWeatherItem.getTime());
        i.putExtra(DATE, clickedWeatherItem.getDate());
        i.putExtra(TEMP, clickedWeatherItem.getTemperature());

        startActivity(i);


    }
}
