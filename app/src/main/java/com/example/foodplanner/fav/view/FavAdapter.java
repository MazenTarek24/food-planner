package com.example.foodplanner.fav.view;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;
import com.example.foodplanner.db.MealDatabase;
import com.example.foodplanner.model.Meal;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.ViewHolder> {

    List<Meal> mealList = new ArrayList();

    DeleteFavFromDb deleteFavFromDb;

    Context context;

    public FavAdapter(Context context) {
        this.context = context;
    }

    public FavAdapter(DeleteFavFromDb deleteFavFromDb) {
        this.deleteFavFromDb = deleteFavFromDb;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fav,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.txt_category.setText(mealList.get(position).getStrCategory());
        holder.txt_meal.setText(mealList.get(position).getStrMeal());
        holder.txt_area.setText(mealList.get(position).getStrArea());

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteFavFromDb.deleteFavFromDb(mealList.get(position));
            }
        });
        Picasso.get().load(mealList.get(position).getStrMealThumb()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return mealList.size();
    }

    public void setMealList(List<Meal> mealList)
    {
        this.mealList = mealList;
        notifyDataSetChanged();
    }
    class ViewHolder extends RecyclerView.ViewHolder
    {

        ImageView imageView;
        TextView txt_area;
        TextView txt_meal;
        TextView txt_category;

        Button delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            txt_area = itemView.findViewById(R.id.area_meal);
            txt_meal = itemView.findViewById(R.id.name_meal);
            txt_category = itemView.findViewById(R.id.cat_meal);
            delete = itemView.findViewById(R.id.deleteFromSaved);

        }
    }
}
