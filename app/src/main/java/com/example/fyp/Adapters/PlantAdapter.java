package com.example.fyp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.fyp.Objects.Plant;
import com.example.fyp.R;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PlantAdapter extends RecyclerView.Adapter<PlantAdapter.MyViewHolder>{

    Context mContext;
    ArrayList<Plant> mPlants;


    public PlantAdapter(Context context, ArrayList<Plant> plants){

        mContext = context;
        mPlants = plants;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.plant_card_view, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Plant currentItem = mPlants.get(position);

        String name = currentItem.getName();
        String image = currentItem.getPicture();

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

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            plantPic = itemView.findViewById(R.id.plantPic);

        }
    }


}
