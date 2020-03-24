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

import com.example.fyp.ArrayInterface;
import com.example.fyp.Objects.Plant;
import com.example.fyp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ViewPlantsAdapter extends RecyclerView.Adapter<ViewPlantsAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<Plant> mPlants;
    private OnItemClickListener mListener;

    private String name;
    private String image;
    String pos;
    String soil;
    String growth;
    String care;

    public interface OnItemClickListener{

        void onItemClick(int position);
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){

        mListener = listener;
    }



    public ViewPlantsAdapter(Context context, ArrayList<Plant> plants){

        mContext = context;
        mPlants = plants;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(mContext).inflate(R.layout.my_plant_view_card, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {

        Plant currentItem = mPlants.get(position);

        name = currentItem.getName();
        image = currentItem.getPicture();
        pos = currentItem.getPosition();
        soil = currentItem.getSoil();
        growth = currentItem.getGrowth();
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
        ImageButton delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.view_plants_name);
            plantPic = itemView.findViewById(R.id.view_plants_picture);
            delete = itemView.findViewById(R.id.deletePlantButton);


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

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener !=null){

                        int position = getAdapterPosition();

                        if(position != RecyclerView.NO_POSITION){

                            mListener.onDeleteClick(position);
                        }
                    }
                }
            });

        }
    }


}
