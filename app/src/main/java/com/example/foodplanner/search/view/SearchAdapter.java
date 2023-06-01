package com.example.foodplanner.search.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;
import com.example.foodplanner.model.Ingredients;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    List<Ingredients> ingredientsList = new ArrayList<>();;
    List<Ingredients> searchIngredientList = new ArrayList<>();

    public void setSearchIngredientList(List<Ingredients> searchIngredientList) {
        this.searchIngredientList = searchIngredientList;
    }

    public void setIngredientsList(List<Ingredients> ingredientsList) {
        this.ingredientsList = ingredientsList;
        notifyDataSetChanged(); // Notify the adapter of the data change
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(ingredientsList.get(position).getStrIngredient());
    }

    @Override
    public int getItemCount() {

            return ingredientsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.ingredientName);
        }
    }

}
