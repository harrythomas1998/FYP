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
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {

        Plant currentItem = mPlants.get(position);

        String name = currentItem.getName();
        String image = currentItem.getPicture();

        holder.name.setText(name);
        //Picasso.get().load(image).resize(130, 130).into(holder.plantPic);

        holder.plantPic.setImageResource(R.drawable.grass);
        new DownloadImageTask(holder.plantPic).execute(image);





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

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }
        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }



}

