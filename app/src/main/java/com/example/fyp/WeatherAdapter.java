package com.example.fyp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.Viewholder> {

    private Context mContext;
    private ArrayList<Weather> mWeatherList;
    private OnItemClickListener mListener;


    public interface OnItemClickListener{

        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){

        mListener = listener;
    }
    public WeatherAdapter(Context context, ArrayList<Weather> weatherList){

        mContext = context;
        mWeatherList = weatherList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.recycler_item1, parent, false);
        return new Viewholder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

        Weather currentItem = mWeatherList.get(position);

        String weatherType = currentItem.getWeatherType();
        double temp = Math.round(currentItem.getTemperature() - 273.15);
        String time = currentItem.getTime();
        String date = currentItem.getDate();

        holder.weatherTxt.setText(weatherType);
        holder.temp.setText(Double.toString(temp) + "°C");
        holder.time.setText(time);
        holder.date.setText(date);


    }

    @Override
    public int getItemCount() {
        return mWeatherList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{

        public TextView weatherTxt;
        public TextView temp;
        public TextView time;
        public TextView date;


        public Viewholder(@NonNull View itemView) {
            super(itemView);

            weatherTxt = itemView.findViewById(R.id.weather);
            temp = itemView.findViewById(R.id.degrees);
            time = itemView.findViewById(R.id.time);
            date = itemView.findViewById(R.id.date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(mListener !=null){

                        int position = getAdapterPosition();

                        if(position != RecyclerView.NO_POSITION){

                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }


}
