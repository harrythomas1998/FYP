package com.example.fyp.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.ViewTarget;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.example.fyp.Objects.Plant;
import com.example.fyp.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PlantAdapter extends RecyclerView.Adapter<PlantAdapter.MyViewHolder> {

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
    }

    public void setOnItemClickListener(OnItemClickListener listener){

        mListener = listener;
    }



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

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            plantPic = itemView.findViewById(R.id.plantPic);


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

    public void filterList(ArrayList<Plant> filteredList){

        mPlants = filteredList;
        notifyDataSetChanged();
    }
}

