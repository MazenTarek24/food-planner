package com.example.foodplanner.plan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;
import com.example.foodplanner.model.Meal;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PlanDeatailAdapter extends RecyclerView.Adapter<PlanDeatailAdapter.ViewHolder> {
    ArrayList<Meal> myList = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fav, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        holder.name.setText(myList.get(position).getStrMeal());
        Picasso.get().load(myList.get(position).getStrMealThumb()).into(holder.imageView);
        holder.category.setText(myList.get(position).getStrCategory());
        holder.area.setText(myList.get(position).getStrArea());

    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    public void setList(ArrayList<Meal> myList) {
        this.myList = myList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView category;
        ImageView imageView;
        TextView area;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name_meal);
            imageView = itemView.findViewById(R.id.imageView);
            category = itemView.findViewById(R.id.cat_meal);
            area = itemView.findViewById(R.id.area_meal);
        }
    }
}
