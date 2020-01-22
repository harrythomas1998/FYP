package com.example.fyp.Adapters;



import android.view.View;


import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fyp.R;


public class MyHolder extends RecyclerView.ViewHolder{

    TextView weatherType, time, degrees;


    public MyHolder(@NonNull View itemView) {
        super(itemView);

        this.weatherType = itemView.findViewById(R.id.weather);
        this.time = itemView.findViewById(R.id.time);
        this.degrees = itemView.findViewById(R.id.degrees);
    }


}
