package com.example.fyp;

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

public class FragmentThursday extends Fragment implements ArrayInterface {

    View v;
    private RecyclerView recyclerView;


    public FragmentThursday() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.thursday_fragment, container, false);
        recyclerView = v.findViewById(R.id.thursday_recycler);
        WeatherAdapter weatherAdapter = new WeatherAdapter(getContext(), thursdayData);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(weatherAdapter);
        recyclerView.setHasFixedSize(true);
        return v;
    }
}