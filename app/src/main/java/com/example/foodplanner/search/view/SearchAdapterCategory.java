package com.example.foodplanner.search.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;
import com.example.foodplanner.allmeal.view.CategoryAdapter;
import com.example.foodplanner.model.Category;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapterCategory extends RecyclerView.Adapter<SearchAdapterCategory.ViewHolder> {
    private List<Category> categoryList = new ArrayList<>();


    public void setList(List<Category> categoryList)
    {
        this.categoryList = categoryList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.category_item, parent, false);
        return new SearchAdapterCategory.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapterCategory.ViewHolder holder, int position) {
        Category category = categoryList.get(position);
        Log.d("RecyclerAdapter", "Category Name: " +
                category.getStrCategory().toString() + ", Price: " + category.getStrCategory());

        holder.name.setText(category.getStrCategory());


        Picasso.get().load(categoryList.get(position).getStrCategoryThumb()).into(holder.imageView);
    }



    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_category);
            name = itemView.findViewById(R.id.txt_category);
        }
    }
}

