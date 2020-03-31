package com.example.fyp.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fyp.Objects.Plant;
import com.example.fyp.Objects.SoilType;
import com.example.fyp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

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
        void onAddClick(int position);
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

        DatabaseReference reference;
        FirebaseUser user;
        FirebaseAuth firebaseAuth;

        Plant currentItem = mPlants.get(position);

        name = currentItem.getName();
        image = currentItem.getPicture();
        pos = currentItem.getPosition();
        soil = currentItem.getSoil();
        growth = currentItem.getGrowth();
        care = currentItem.getCare();

        holder.name.setText(name);
        Picasso.get().load(image).fit().centerInside().into(holder.plantPic);

        firebaseAuth = FirebaseAuth.getInstance();

        user = firebaseAuth.getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference().child("SoilType").child(user.getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot1: dataSnapshot.getChildren()){

                    SoilType st = snapshot1.getValue(SoilType.class);
                    assert st != null;
                    String fertility = st.getFertility();
                    String drainage = st.getDrainage();

                    //Suitable
                    if(soil.contains("well-drained") && fertility.contains("High") && drainage.contains("Very Good") ){

                        holder.suitability.setTextColor(Color.parseColor("#8DBE5E"));
                        holder.suitability.setText("Plant is suitable");
                    }
                    else if(soil.contains("Well-drained") && fertility.contains("High") && drainage.contains("Very Good") ){

                        holder.suitability.setTextColor(Color.parseColor("#8DBE5E"));
                        holder.suitability.setText("Plant is suitable");
                    }
                    else if(soil.contains("well drained") && fertility.contains("High") && drainage.contains("Very Good") ){

                        holder.suitability.setTextColor(Color.parseColor("#8DBE5E"));
                        holder.suitability.setText("Plant is suitable");
                    }
                    else if(soil.contains("well-drained") && soil.contains("humus-rich") && fertility.contains("High") && drainage.contains("Very Good")){

                        holder.suitability.setTextColor(Color.parseColor("#8DBE5E"));
                        holder.suitability.setText("Plant is suitable");
                    }
                    else if(soil.contains("well drained") && soil.contains("humus-rich") && fertility.contains("High") && drainage.contains("Very Good")){

                        holder.suitability.setTextColor(Color.parseColor("#8DBE5E"));
                        holder.suitability.setText("Plant is suitable");
                    }
                    else if(soil.contains("well-drained") && soil.contains("moist") && fertility.contains("High") && drainage.contains("Very Good")){

                        holder.suitability.setTextColor(Color.parseColor("#8DBE5E"));
                        holder.suitability.setText("Plant is suitable");
                    }
                    else if(soil.contains("well drained") && soil.contains("moist") && fertility.contains("High") && drainage.contains("Very Good")){

                        holder.suitability.setTextColor(Color.parseColor("#8DBE5E"));
                        holder.suitability.setText("Plant is suitable");
                    }

                    else if(soil.contains("freely draining") && soil.contains("humus-rich") && fertility.contains("High") && drainage.contains("Very Good")){

                        holder.suitability.setTextColor(Color.parseColor("#8DBE5E"));
                        holder.suitability.setText("Plant is suitable");
                    }

                    else if(soil.contains("freely draining") && soil.contains("moist") && fertility.contains("High") && drainage.contains("Very Good")){

                        holder.suitability.setTextColor(Color.parseColor("#8DBE5E"));
                        holder.suitability.setText("Plant is suitable");
                    }
                    else{

                        holder.suitability.setTextColor(Color.parseColor("#FF2233"));
                        holder.suitability.setText("Plant is not suitable");
                    }
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    @Override
    public int getItemCount() {
        return mPlants.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        ImageView plantPic;
        ImageButton add;
        TextView suitability;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            plantPic = itemView.findViewById(R.id.plantPic);
            add = itemView.findViewById(R.id.add_from_card);
            suitability = itemView.findViewById(R.id.suitability_text);



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

            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener !=null){

                        int position = getAdapterPosition();

                        if(position != RecyclerView.NO_POSITION){

                            mListener.onAddClick(position);
                        }
                    }
                }
            });

        }
    }

}

