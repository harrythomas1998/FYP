package com.example.fyp.Adapters;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageButton;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fyp.CreateEvent;

import com.example.fyp.R;
import com.example.fyp.Weather;


import java.util.List;

public class WeatherAdapter  extends RecyclerView.Adapter{

    private Context mContext;
    private List<Weather> mWeathers;


    public WeatherAdapter(Context mContext,List<Weather> mWeathers){
        this.mContext = mContext;
        this.mWeathers = mWeathers;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.weather_item, parent, false);
        return new WeatherAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ViewHolder myHolder = (ViewHolder) holder;

        final Weather weather = mWeathers.get(position);
        myHolder.weather_text.setText(weather.getWeatherType());
        myHolder.time_text.setText(weather.getTime());
        myHolder.degrees_text.setText(weather.getTemperature());

        myHolder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, CreateEvent.class);
                intent.putExtra("weatherType", weather.getWeatherType());
                intent.putExtra("temp", weather.getTemperature());
                intent.putExtra("degrees", weather.getTemperature());
                mContext.startActivity(intent);
            }
        });




    }

    @Override
    public int getItemCount() {
        return mWeathers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView time_text;
        public TextView weather_text;
        public TextView degrees_text;
        public ImageButton btn;



        public ViewHolder(View itemView){

            super(itemView);

            time_text = itemView.findViewById(R.id.time_text);
            weather_text = itemView.findViewById(R.id.weather_text);
            degrees_text = itemView.findViewById(R.id.degrees_text);
            btn = itemView.findViewById(R.id.add_button);

        }
    }
}
