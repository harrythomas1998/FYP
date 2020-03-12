package com.example.fyp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fyp.Objects.Job;
import com.example.fyp.R;

import java.util.ArrayList;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.Viewholder> {


    private Context mContext;
    private ArrayList<Job> mJobList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{

        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){

        mListener = listener;
    }

    public JobAdapter(Context context, ArrayList<Job> jobList){

        mContext = context;
        mJobList = jobList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.job_item, parent, false);
        return new Viewholder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

        Job currentItem = mJobList.get(position);

        String title = currentItem.getTitle();


        holder.jobTxt.setText(title);

        if(title.contains("grass") || title.contains("Grass")){

            holder.imageView.setImageResource(R.drawable.grass);
        }



    }

    @Override
    public int getItemCount() {
        return mJobList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{

        public TextView jobTxt;
        public ImageView imageView;



        public Viewholder(@NonNull View itemView) {
            super(itemView);

            jobTxt = itemView.findViewById(R.id.title_text);
            imageView = itemView.findViewById(R.id.image_view_job);

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
