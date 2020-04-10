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
        reference = FirebaseDatabase.getInstance().getReference().child("SoilType").child(user.getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot1: dataSnapshot.getChildren()){

                    SoilType st = snapshot1.getValue(SoilType.class);
                    assert st != null;
                    String name = st.getName();

                    //Brown

                    //Suitable
                    if(soil.contains("freely draining") && name.equals("Brown Soil")  ){

                        holder.suitability.setTextColor(Color.parseColor("#8DBE5E"));
                        holder.suitability.setText("Plant is suitable");
                    }
                    if(soil.contains("freely-draining") && name.equals("Brown Soil")  ){

                        holder.suitability.setTextColor(Color.parseColor("#8DBE5E"));
                        holder.suitability.setText("Plant is suitable");
                    }
                    if(soil.contains("Well-drained") && name.equals("Brown Soil")){
                        holder.suitability.setTextColor(Color.parseColor("#8DBE5E"));
                        holder.suitability.setText("Plant is suitable");
                    }
                    if(soil.contains("Well drained") && name.equals("Brown Soil")){
                        holder.suitability.setTextColor(Color.parseColor("#8DBE5E"));
                        holder.suitability.setText("Plant is suitable");
                    }
                    if(soil.contains("well-drained") && name.equals("Brown Soil")){
                        holder.suitability.setTextColor(Color.parseColor("#8DBE5E"));
                        holder.suitability.setText("Plant is suitable");
                    }
                    if(soil.contains("well drained") && name.equals("Brown Soil")){
                        holder.suitability.setTextColor(Color.parseColor("#8DBE5E"));
                        holder.suitability.setText("Plant is suitable");
                    }

                    if(soil.contains("moist") && soil.contains("well drained") && name.equals("Brown Soil")  ){

                        holder.suitability.setTextColor(Color.parseColor("#8DBE5E"));
                        holder.suitability.setText("Plant is suitable");
                    }
                    if(soil.contains("moist") && soil.contains("well-drained") && name.equals("Brown Soil")  ){

                        holder.suitability.setTextColor(Color.parseColor("#8DBE5E"));
                        holder.suitability.setText("Plant is suitable");
                    }
                    if(soil.contains("moist") && soil.contains("Well drained") && name.equals("Brown Soil")  ){

                        holder.suitability.setTextColor(Color.parseColor("#8DBE5E"));
                        holder.suitability.setText("Plant is suitable");
                    }
                    if(soil.contains("moist") && soil.contains("Well-drained") && name.equals("Brown Soil")  ){

                        holder.suitability.setTextColor(Color.parseColor("#8DBE5E"));
                        holder.suitability.setText("Plant is suitable");
                    }
                    if(soil.contains("Moist") && soil.contains("well-drained")&& name.equals("Brown Soil")  ){

                        holder.suitability.setTextColor(Color.parseColor("#8DBE5E"));
                        holder.suitability.setText("Plant is suitable");
                    }
                    if(soil.contains("Moist") && soil.contains("well drained")&& name.equals("Brown Soil")  ){

                        holder.suitability.setTextColor(Color.parseColor("#8DBE5E"));
                        holder.suitability.setText("Plant is suitable");
                    }
                    if(soil.contains("Moist") && soil.contains("Well-drained")&& name.equals("Brown Soil")  ){

                        holder.suitability.setTextColor(Color.parseColor("#8DBE5E"));
                        holder.suitability.setText("Plant is suitable");
                    }
                    if(soil.contains("Moist") && soil.contains("Well drained")&& name.equals("Brown Soil")  ){

                        holder.suitability.setTextColor(Color.parseColor("#8DBE5E"));
                        holder.suitability.setText("Plant is suitable");
                    }
                    if(soil.contains("humus-rich") && soil.contains("Well drained")&& name.equals("Brown Soil")  ){

                        holder.suitability.setTextColor(Color.parseColor("#8DBE5E"));
                        holder.suitability.setText("Plant is suitable");
                    }
                    if(soil.contains("humus-rich") && soil.contains("Well-drained")&& name.equals("Brown Soil")  ){

                        holder.suitability.setTextColor(Color.parseColor("#8DBE5E"));
                        holder.suitability.setText("Plant is suitable");
                    }
                    if(soil.contains("humus-rich") && soil.contains("well-drained")&& name.equals("Brown Soil")  ){

                        holder.suitability.setTextColor(Color.parseColor("#8DBE5E"));
                        holder.suitability.setText("Plant is suitable");
                    }
                    if(soil.contains("humus-rich") && soil.contains("well drained")&& name.equals("Brown Soil")  ){

                        holder.suitability.setTextColor(Color.parseColor("#8DBE5E"));
                        holder.suitability.setText("Plant is suitable");
                    }
                    if(soil.contains("humus rich") && soil.contains("well drained")&& name.equals("Brown Soil")  ){

                        holder.suitability.setTextColor(Color.parseColor("#8DBE5E"));
                        holder.suitability.setText("Plant is suitable");
                    }
                    if(soil.contains("humus rich") && soil.contains("well-drained")&& name.equals("Brown Soil")  ){

                        holder.suitability.setTextColor(Color.parseColor("#8DBE5E"));
                        holder.suitability.setText("Plant is suitable");
                    }
                    if(soil.contains("humus rich") && soil.contains("Well-drained")&& name.equals("Brown Soil")  ){

                        holder.suitability.setTextColor(Color.parseColor("#8DBE5E"));
                        holder.suitability.setText("Plant is suitable");
                    }
                    if(soil.contains("humus rich") && soil.contains("Well drained")&& name.equals("Brown Soil")  ){

                        holder.suitability.setTextColor(Color.parseColor("#8DBE5E"));
                        holder.suitability.setText("Plant is suitable");
                    }
                    if(soil.contains("humus-rich") && soil.contains("moist")&& name.equals("Brown Soil")  ){

                        holder.suitability.setTextColor(Color.parseColor("#8DBE5E"));
                        holder.suitability.setText("Plant is suitable");
                    }
                    if(soil.contains("humus rich") && soil.contains("Moist")&& name.equals("Brown Soil")  ){

                        holder.suitability.setTextColor(Color.parseColor("#8DBE5E"));
                        holder.suitability.setText("Plant is suitable");
                    }



                    //moderate

                    if (soil.contains("moderately") && name.equals("Brown Soil")  ){

                        holder.suitability.setTextColor(Color.parseColor("#FFC409"));
                        holder.suitability.setText("Plant is okay");
                    }


                    //bad

                    if(name.equals("Brown Soil") && soil.contains("alkaline") ){
                        holder.suitability.setTextColor(Color.parseColor("#FF2233"));
                        holder.suitability.setText("Plant is not suitable");
                    }
                    if(name.equals("Brown Soil") && soil.contains("poor") ){
                        holder.suitability.setTextColor(Color.parseColor("#FF2233"));
                        holder.suitability.setText("Plant is not suitable");
                    }
                    if(name.equals("Brown Soil") && soil.contains("sandy") ){
                        holder.suitability.setTextColor(Color.parseColor("#FF2233"));
                        holder.suitability.setText("Plant is not suitable");
                    }
                    if(name.equals("Brown Soil") && soil.contains("peaty") ){
                        holder.suitability.setTextColor(Color.parseColor("#FF2233"));
                        holder.suitability.setText("Plant is not suitable");
                    }


                    //Peaty Soil

                    //Good

                    if(name.equals("Peaty Soil") && soil.contains("poor") ){
                        holder.suitability.setTextColor(Color.parseColor("#8DBE5E"));
                        holder.suitability.setText("Plant is suitable");
                    }
                    if(name.equals("Peaty Soil") && soil.contains("peaty") ){
                        holder.suitability.setTextColor(Color.parseColor("#8DBE5E"));
                        holder.suitability.setText("Plant is suitable");
                    }
                    if(name.equals("Peaty Soil") && soil.contains("acid") || soil.contains("acidic")){
                        holder.suitability.setTextColor(Color.parseColor("#8DBE5E"));
                        holder.suitability.setText("Plant is suitable");

                    }
                    if(name.equals("Peaty Soil") && soil.contains("Acid") || soil.contains("Acidic")){
                        holder.suitability.setTextColor(Color.parseColor("#8DBE5E"));
                        holder.suitability.setText("Plant is suitable");

                    }
                    if(name.equals("Peaty Soil") && soil.contains("acidic") || soil.contains("Acidic")){
                        holder.suitability.setTextColor(Color.parseColor("#8DBE5E"));
                        holder.suitability.setText("Plant is suitable");

                    }
                    if(name.equals("Peaty Soil") && soil.contains("Acidic") || soil.contains("Acidic")){
                        holder.suitability.setTextColor(Color.parseColor("#8DBE5E"));
                        holder.suitability.setText("Plant is suitable");

                    }

                    //okay

                    if(soil.contains("moderately") && name.equals("Peaty Soil")){

                        holder.suitability.setTextColor(Color.parseColor("#FFC409"));
                        holder.suitability.setText("Plant is okay");
                    }
                    if(soil.contains("moderately") && name.equals("Peaty Soil")){

                        holder.suitability.setTextColor(Color.parseColor("#FFC409"));
                        holder.suitability.setText("Plant is okay");
                    }
                    if(soil.contains("fetile") && name.equals("Peaty Soil")){

                        holder.suitability.setTextColor(Color.parseColor("#FFC409"));
                        holder.suitability.setText("Plant is okay");
                    }

                    //bad

                    if(name.equals("Peaty Soil") && soil.contains("alkaline") ){
                        holder.suitability.setTextColor(Color.parseColor("#FF2233"));
                        holder.suitability.setText("Plant is not suitable");
                    }
                    if(name.equals("Peaty Soil") && soil.contains("humus-rich") ){
                        holder.suitability.setTextColor(Color.parseColor("#FF2233"));
                        holder.suitability.setText("Plant is not suitable");
                    }
                    if(name.equals("Peaty Soil") && soil.contains("humus rich") ){
                        holder.suitability.setTextColor(Color.parseColor("#FF2233"));
                        holder.suitability.setText("Plant is not suitable");
                    }

                    //Podzol Soil

                    //Good

                    if(name.equals("Podzol Soil") && soil.contains("poor") ){
                        holder.suitability.setTextColor(Color.parseColor("#8DBE5E"));
                        holder.suitability.setText("Plant is suitable");
                    }
                    if(name.equals("Podzol Soil") && soil.contains("peaty") ){
                        holder.suitability.setTextColor(Color.parseColor("#8DBE5E"));
                        holder.suitability.setText("Plant is suitable");
                    }
                    if(name.equals("Podzol Soil") && soil.contains("acid") || soil.contains("acidic")){
                        holder.suitability.setTextColor(Color.parseColor("#8DBE5E"));
                        holder.suitability.setText("Plant is suitable");

                    }
                    if(name.equals("Podzol Soil") && soil.contains("Acid") || soil.contains("Acidic")){
                        holder.suitability.setTextColor(Color.parseColor("#8DBE5E"));
                        holder.suitability.setText("Plant is suitable");

                    }

                    //okay

                    if(soil.contains("moderately") && name.equals("Podzol Soil")){

                        holder.suitability.setTextColor(Color.parseColor("#FFC409"));
                        holder.suitability.setText("Plant is okay");
                    }
                    if(soil.contains("moderately") && name.equals("Podzol Soil")){

                        holder.suitability.setTextColor(Color.parseColor("#FFC409"));
                        holder.suitability.setText("Plant is okay");
                    }
                    if(soil.contains("fetile") && name.equals("Podzol Soil")){

                        holder.suitability.setTextColor(Color.parseColor("#FFC409"));
                        holder.suitability.setText("Plant is okay");
                    }

                    //bad

                    if(name.equals("Podzol Soil") && soil.contains("alkaline") ){
                        holder.suitability.setTextColor(Color.parseColor("#FF2233"));
                        holder.suitability.setText("Plant is not suitable");
                    }
                    if(name.equals("Podzol Soil") && soil.contains("humus-rich") ){
                        holder.suitability.setTextColor(Color.parseColor("#FF2233"));
                        holder.suitability.setText("Plant is not suitable");
                    }
                    if(name.equals("Podzol Soil") && soil.contains("humus rich") ){
                        holder.suitability.setTextColor(Color.parseColor("#FF2233"));
                        holder.suitability.setText("Plant is not suitable");
                    }

                    //Gley Soil

                    //Good

                    if(name.equals("Gley Soil") && soil.contains("humus-rich") || soil.contains("humus rich") ){
                        holder.suitability.setTextColor(Color.parseColor("#8DBE5E"));
                        holder.suitability.setText("Plant is suitable");
                    }
                    if(name.equals("Gley Soil") && soil.contains("Moist") || soil.contains("moist") ){
                        holder.suitability.setTextColor(Color.parseColor("#8DBE5E"));
                        holder.suitability.setText("Plant is suitable");
                    }
                    if(name.equals("Gley Soil") && soil.contains("acid") || soil.contains("acidic")){
                        holder.suitability.setTextColor(Color.parseColor("#8DBE5E"));
                        holder.suitability.setText("Plant is suitable");
                    }
                    if(name.equals("Gley Soil") && soil.contains("Acid") || soil.contains("Acidic")){
                        holder.suitability.setTextColor(Color.parseColor("#8DBE5E"));
                        holder.suitability.setText("Plant is suitable");
                    }

                    //okay

                    if(soil.contains("moderately") && name.equals("Gley Soil")){

                        holder.suitability.setTextColor(Color.parseColor("#FFC409"));
                        holder.suitability.setText("Plant is okay");
                    }
                    if(soil.contains("moderately") && name.equals("Gley Soil")){

                        holder.suitability.setTextColor(Color.parseColor("#FFC409"));
                        holder.suitability.setText("Plant is okay");
                    }
                    if(soil.contains("well-drained") && soil.contains("moist") || soil.contains("Moist") && name.equals("Gley Soil")  ){

                        holder.suitability.setTextColor(Color.parseColor("#FFC409"));
                        holder.suitability.setText("Plant is okay");
                    }
                    if(soil.contains("well drained") || soil.contains("Well drained") && soil.contains("moist") || soil.contains("Moist") && name.equals("Gley Soil")  ){

                        holder.suitability.setTextColor(Color.parseColor("#FFC409"));
                        holder.suitability.setText("Plant is okay");
                    }
                    if(soil.contains("moist") || soil.contains("Moist") && soil.contains("humus-rich") && name.equals("Gley Soil")  ){

                        holder.suitability.setTextColor(Color.parseColor("#FFC409"));
                        holder.suitability.setText("Plant is okay");
                    }
                    if(soil.contains("moist") || soil.contains("Moist") && soil.contains("freely-draining") && name.equals("Gley Soil")  ){

                        holder.suitability.setTextColor(Color.parseColor("#FFC409"));
                        holder.suitability.setText("Plant is okay");
                    }
                    if(soil.contains("moist") || soil.contains("Moist") && soil.contains("freely draining") && name.equals("Gley Soil")  ){

                        holder.suitability.setTextColor(Color.parseColor("#FFC409"));
                        holder.suitability.setText("Plant is okay");
                    }
                    if(soil.contains("humus-rich") && soil.contains("freely draining") && name.equals("Gley Soil")  ){

                        holder.suitability.setTextColor(Color.parseColor("#FFC409"));
                        holder.suitability.setText("Plant is okay");
                    }
                    if(soil.contains("humus rich") && soil.contains("freely-draining") && name.equals("Gley Soil")  ){

                        holder.suitability.setTextColor(Color.parseColor("#FFC409"));
                        holder.suitability.setText("Plant is okay");
                    }
                    if(soil.contains("moist") || soil.contains("Moist")  && soil.contains("humus rich") && name.equals("Gley Soil")  ){

                        holder.suitability.setTextColor(Color.parseColor("#FFC409"));
                        holder.suitability.setText("Plant is okay");
                    }
                    if(soil.contains("well-drained") || soil.contains("Well drained") && name.equals("Gley Soil")  ){

                        holder.suitability.setTextColor(Color.parseColor("#FFC409"));
                        holder.suitability.setText("Plant is okay");
                    }

                    //bad

                    if(name.equals("Gley Soil") && soil.contains("alkaline") ){
                        holder.suitability.setTextColor(Color.parseColor("#FF2233"));
                        holder.suitability.setText("Plant is not suitable");
                    }
                    if(name.equals("Gley Soil") && soil.contains("well-drained") || soil.contains("well drained")){
                        holder.suitability.setTextColor(Color.parseColor("#FF2233"));
                        holder.suitability.setText("Plant is not suitable");
                    }
                    if(name.equals("Gley Soil") && soil.contains("Well-drained") || soil.contains("Well drained")){
                        holder.suitability.setTextColor(Color.parseColor("#FF2233"));
                        holder.suitability.setText("Plant is not suitable");
                    }
                    if(name.equals("Gley Soil") && soil.contains("freely") || soil.contains("well drained")){
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

