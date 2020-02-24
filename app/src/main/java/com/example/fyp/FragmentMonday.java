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

import java.util.ArrayList;


public class FragmentMonday extends Fragment {

    View v;
    private RecyclerView recyclerView;
    private ArrayList<Weather> lstWeather = new ArrayList<>();



    public FragmentMonday(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.monday_fragment, container, false);
        recyclerView = v.findViewById(R.id.monday_recycler);
        WeatherAdapter weatherAdapter = new WeatherAdapter(getContext(), lstWeather);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(weatherAdapter);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);






        Bundle extras = getActivity().getIntent().getExtras();




    }
}
