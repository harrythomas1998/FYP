package com.example.fyp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fyp.Objects.MaintenancePlant;
import com.example.fyp.Objects.Plant;
import com.example.fyp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MaintenancePlantAdapter extends RecyclerView.Adapter<MaintenancePlantAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<MaintenancePlant> mPlants;
    private OnItemClickListener mListener;

    private String name;
    private String image;
    String pos;
    String care;
    String link;

    public interface OnItemClickListener {

        void onItemClick(int position);
        void onDoneClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {

        mListener = listener;
    }


    public MaintenancePlantAdapter(Context context, ArrayList<MaintenancePlant> plants) {

        mContext = context;
        mPlants = plants;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(mContext).inflate(R.layout.plant_maintenance_card, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {

        MaintenancePlant currentItem = mPlants.get(position);

        name = currentItem.getName();
        image = currentItem.getImage();
        link = currentItem.getLink();
        care = currentItem.getCare();

        holder.name.setText(name);
        Picasso.get().load(image).fit().centerInside().into(holder.plantPic);


    }

    @Override
    public int getItemCount() {
        return mPlants.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        ImageView plantPic;
        ImageButton done;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.plant_maintenance_name);
            plantPic = itemView.findViewById(R.id.plant_maintenance_image);
            done = itemView.findViewById(R.id.done_from_card);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (mListener != null) {

                        int position = getAdapterPosition();

                        if (position != RecyclerView.NO_POSITION) {

                            mListener.onItemClick(position);
                        }
                    }
                }
            });

            done.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {

                        int position = getAdapterPosition();

                        if (position != RecyclerView.NO_POSITION) {

                            mListener.onDoneClick(position);
                        }
                    }
                }
            });

        }
    }

}


