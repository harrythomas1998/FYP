package com.example.fyp.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
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
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PlantAdapter extends RecyclerView.Adapter<PlantAdapter.MyViewHolder> implements Filterable {

    private Context mContext;
    private ArrayList<Plant> mPlants;
    private ArrayList<Plant> mPlants2;
    private OnItemClickListener mListener;

    private String name;
    private String image;
    String pos;
    String soil;
    String growth;
    String care;

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Plant> filteredList = new ArrayList<>();

            if(constraint == null || constraint.length() == 0){
                filteredList.addAll(mPlants2);
            }
            else{
                String filterPattern = constraint.toString().toLowerCase().trim();

                for(Plant p : mPlants2){
                    if(p.getName().toLowerCase().contains(filterPattern)){
                        filteredList.add(p);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            mPlants.clear();
            mPlants.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };

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
        mPlants2 = new ArrayList<>(mPlants);
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
        assert user != null;
        reference = FirebaseDatabase.getInstance().getReference().child("SoilType").child(user.getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot1: dataSnapshot.getChildren()){

                    SoilType st = snapshot1.getValue(SoilType.class);
                    assert st != null;
                    String name = st.getName();

                    //Brown Soil
                    switch (name) {
                        case "Brown Soil":

                            if (soil.contains("alkaline")) {
                                holder.suitability.setTextColor(Color.parseColor("#FF2233"));
                                holder.suitability.setText("Not suitable for your garden");
                            } else if (soil.contains("moderately")) {
                                holder.suitability.setTextColor(Color.parseColor("#FFC409"));
                                holder.suitability.setText("Partially suitable for your garden");
                            } else if (soil.contains("poor")) {
                                holder.suitability.setTextColor(Color.parseColor("#FF2233"));
                                holder.suitability.setText("Not suitable for your garden");
                            } else if (soil.contains("sandy")) {
                                holder.suitability.setTextColor(Color.parseColor("#FF2233"));
                                holder.suitability.setText("Not suitable for your garden");
                            } else if (soil.contains("peaty")) {
                                holder.suitability.setTextColor(Color.parseColor("#FF2233"));
                                holder.suitability.setText("Not suitable for your garden");
                            } else if (soil.contains("well-drained")) {
                                holder.suitability.setTextColor(Color.parseColor("#8DBE5E"));
                                holder.suitability.setText("Suitable for your garden");
                            } else {
                                holder.suitability.setTextColor(Color.parseColor("#8DBE5E"));
                                holder.suitability.setText("Suitable for your garden");
                            }
                            break;

                        //Peaty Soil
                        case "Peaty Soil":

                            if (soil.contains("Acid") || soil.contains("acid") || soil.contains("Acidic") || soil.contains("acidic") ) {
                                holder.suitability.setTextColor(Color.parseColor("#8DBE5E"));
                                holder.suitability.setText("Suitable for your garden");
                            }
                            //okay
                            else if (soil.contains("moderately")) {
                                holder.suitability.setTextColor(Color.parseColor("#FFC409"));
                                holder.suitability.setText("Partially suitable for your garden");
                            } else if (soil.contains("fertile")) {
                                holder.suitability.setTextColor(Color.parseColor("#FFC409"));
                                holder.suitability.setText("Partially suitable for your garden");
                            } else if (soil.contains("moist") || soil.contains("Moist")) {
                                holder.suitability.setTextColor(Color.parseColor("#FF2233"));
                                holder.suitability.setText("Not suitable for your garden");
                            }
                            //bad
                            else if (soil.contains("alkaline")) {
                                holder.suitability.setTextColor(Color.parseColor("#FF2233"));
                                holder.suitability.setText("Not suitable for your garden");
                            } else if (soil.contains("humus-rich") || soil.contains("humus rich")) {
                                holder.suitability.setTextColor(Color.parseColor("#FF2233"));
                                holder.suitability.setText("Not suitable for your garden");
                            } else if(soil.contains("Well-drained") || soil.contains("well-drained") || soil.contains("well drained") || soil.contains("Well drained")){
                                holder.suitability.setTextColor(Color.parseColor("#FF2233"));
                                holder.suitability.setText("Not suitable for your garden");
                            }
                            //good
                            else {
                                holder.suitability.setTextColor(Color.parseColor("#8DBE5E"));
                                holder.suitability.setText("Suitable for your garden");
                            }

                            break;

                        //Podzol Soil

                        //Good
                        case "Podzol Soil":

                            if (soil.contains("Acid") || soil.contains("acid") || soil.contains("Acidic") || soil.contains("acidic") ) {
                                holder.suitability.setTextColor(Color.parseColor("#8DBE5E"));
                                holder.suitability.setText("Suitable for your garden");
                            }
                            //okay
                            else if (soil.contains("moderately")) {
                                holder.suitability.setTextColor(Color.parseColor("#FFC409"));
                                holder.suitability.setText("Partially suitable for your garden");
                            } else if (soil.contains("fertile")) {
                                holder.suitability.setTextColor(Color.parseColor("#FFC409"));
                                holder.suitability.setText("Partially suitable for your garden");
                            }
                            //bad
                            else if (soil.contains("alkaline")) {
                                holder.suitability.setTextColor(Color.parseColor("#FF2233"));
                                holder.suitability.setText("Not suitable for your garden");
                            } else if (soil.contains("humus-rich") || soil.contains("humus rich")) {
                                holder.suitability.setTextColor(Color.parseColor("#FF2233"));
                                holder.suitability.setText("Not suitable for your garden");
                            } else if(soil.contains("Well-drained") || soil.contains("well-drained") || soil.contains("well drained") || soil.contains("Well drained")){
                                holder.suitability.setTextColor(Color.parseColor("#FF2233"));
                                holder.suitability.setText("Not suitable for your garden");
                            }else if(soil.contains("peaty") || soil.contains("boggy")){
                                holder.suitability.setTextColor(Color.parseColor("#FF2233"));
                                holder.suitability.setText("Not suitable for your garden");
                            }

                            //good
                            else {
                                holder.suitability.setTextColor(Color.parseColor("#8DBE5E"));
                                holder.suitability.setText("Suitable for your garden");
                            }

                            break;

                        //Gley Soil
                        case "Gley Soil":
                            //Good
                            if (soil.contains("humus-rich") || soil.contains("humus rich")) {
                                holder.suitability.setTextColor(Color.parseColor("#8DBE5E"));
                                holder.suitability.setText("Suitable for your garden");
                            } else if (soil.contains("Moist") || soil.contains("moist")) {
                                holder.suitability.setTextColor(Color.parseColor("#8DBE5E"));
                                holder.suitability.setText("Suitable for your garden");
                            } else if (soil.contains("acid") || soil.contains("acidic") || soil.contains("Acid") || soil.contains("Acidic")) {
                                holder.suitability.setTextColor(Color.parseColor("#8DBE5E"));
                                holder.suitability.setText("Suitable for your garden");
                            }else if (soil.contains("fertile")) {
                                holder.suitability.setTextColor(Color.parseColor("#8DBE5E"));
                                holder.suitability.setText("Suitable for your garden");
                            }

                            //okay
                            else if (soil.contains("moderately")) {

                                holder.suitability.setTextColor(Color.parseColor("#FFC409"));
                                holder.suitability.setText("Partially suitable for your garden");
                            }
                            else if (soil.contains("well-drained") && soil.contains("moist") && soil.contains("Moist")) {

                                holder.suitability.setTextColor(Color.parseColor("#FFC409"));
                                holder.suitability.setText("Partially suitable for your garden");
                            }
                            else if (soil.contains("well drained") || soil.contains("Well drained") && soil.contains("Moist")) {

                                holder.suitability.setTextColor(Color.parseColor("#FFC409"));
                                holder.suitability.setText("Partially suitable for your garden");
                            }
                            else if (soil.contains("Well drained") && soil.contains("moist")) {

                                holder.suitability.setTextColor(Color.parseColor("#FFC409"));
                                holder.suitability.setText("Partially suitable for your garden");
                            }
                            else if (soil.contains("well drained") && soil.contains("moist")) {

                                holder.suitability.setTextColor(Color.parseColor("#FFC409"));
                                holder.suitability.setText("Partially suitable for your garden");
                            }
                            else if (soil.contains("moist") && soil.contains("Moist") && soil.contains("humus-rich")) {

                                holder.suitability.setTextColor(Color.parseColor("#FFC409"));
                                holder.suitability.setText("Partially suitable for your garden");
                            }
                            if (soil.contains("moist") || soil.contains("Moist") && soil.contains("freely-draining")) {

                                holder.suitability.setTextColor(Color.parseColor("#FFC409"));
                                holder.suitability.setText("Partially suitable for your garden");
                            }
                            if (soil.contains("moist") || soil.contains("Moist") && soil.contains("freely draining")) {

                                holder.suitability.setTextColor(Color.parseColor("#FFC409"));
                                holder.suitability.setText("Partially suitable for your garden");
                            }
                            if (soil.contains("humus-rich") && soil.contains("freely draining")) {

                                holder.suitability.setTextColor(Color.parseColor("#FFC409"));
                                holder.suitability.setText("Partially suitable for your garden");
                            }
                            if (soil.contains("humus rich") && soil.contains("freely-draining") ) {

                                holder.suitability.setTextColor(Color.parseColor("#FFC409"));
                                holder.suitability.setText("Partially suitable for your garden");
                            }
                            if (soil.contains("moist") || soil.contains("Moist") && soil.contains("humus rich")) {

                                holder.suitability.setTextColor(Color.parseColor("#FFC409"));
                                holder.suitability.setText("Partially suitable for your garden");
                            }
                            if (soil.contains("well-drained") || soil.contains("Well drained")) {

                                holder.suitability.setTextColor(Color.parseColor("#FFC409"));
                                holder.suitability.setText("Partially suitable for your garden");
                            }
                            //bad
                            if (soil.contains("alkaline")) {
                                holder.suitability.setTextColor(Color.parseColor("#FF2233"));
                                holder.suitability.setText("Not suitable for your garden");
                            }
                            if (soil.contains("well-drained") || soil.contains("well drained")) {
                                holder.suitability.setTextColor(Color.parseColor("#FF2233"));
                                holder.suitability.setText("Not suitable for your garden");
                            }
                            if (soil.contains("Well-drained") || soil.contains("Well drained")) {
                                holder.suitability.setTextColor(Color.parseColor("#FF2233"));
                                holder.suitability.setText("Not suitable for your garden");
                            }
                            if (soil.contains("freely") || soil.contains("well drained")) {
                                holder.suitability.setTextColor(Color.parseColor("#FF2233"));
                                holder.suitability.setText("Not suitable for your garden");
                            }

                            break;
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

