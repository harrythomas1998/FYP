package com.example.fyp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.Viewholder> {


    private Context mContext;
    private ArrayList<Job> mJobList;




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



    }

    @Override
    public int getItemCount() {
        return mJobList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{

        public TextView jobTxt;



        public Viewholder(@NonNull View itemView) {
            super(itemView);

            jobTxt = itemView.findViewById(R.id.title_text);


        }
    }

}
