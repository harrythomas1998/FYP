package com.example.fyp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fyp.R;
import com.example.fyp.Weather;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyHolder> {

    Context c;
    ArrayList<Weather> weatherData;


    public MyAdapter(Context c, ArrayList<Weather> weatherData) {
        this.c = c;
        this.weatherData = weatherData;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item1, null);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        holder.weatherType.setText(weatherData.get(position).getWeatherType());
        holder.time.setText(weatherData.get(position).getTime());
        holder.degrees.setText(weatherData.get(position).getTemperature());

    }

    @Override
    public int getItemCount() {
        return weatherData.size();
    }
}
