package com.example.foodplanner.allcategoryitem.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;
import com.example.foodplanner.model.CategoryItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterCategoryItem extends RecyclerView.Adapter<AdapterCategoryItem.ViewHolder> {
    ArrayList<CategoryItem> myList = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.all_category_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.name.setText(myList.get(position).getStrMeal());
        Picasso.get().load(myList.get(position).getStrMealThumb()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    public void setList(ArrayList<CategoryItem> myList) {
        this.myList = myList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

       TextView name;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

//            movieTV = itemView.findViewById(R.id.movie_name_tv);
            name = itemView.findViewById(R.id.txt_cat_item);
            imageView = itemView.findViewById(R.id.image_cat);


        }
    }
}
