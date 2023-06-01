package com.example.foodplanner.search.view;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.R;
import com.example.foodplanner.allmeal.view.CategoryAdapter;
import com.example.foodplanner.model.Area;
import com.example.foodplanner.model.Category;
import com.example.foodplanner.model.Meal;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AreaAdapter extends RecyclerView.Adapter<AreaAdapter.ViewHolder> {

    private List<Area> areas = new ArrayList<>();

    Context context;
    public AreaAdapter(Context context) {
        this.context = context;
    }
    public void setList(List<Area> areas)
    {
        this.areas = areas;
    }

    @NonNull
    @Override
    public AreaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_country, parent, false);
        return new AreaAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AreaAdapter.ViewHolder holder, int position) {
        Log.d("mido","clickkkkkkkkkkkkkkkkk");

        Area area = areas.get(position);
        Log.d("RecyclerAdapter", "Category Name: " +
               "Price: " + area.getStrArea());

        holder.name.setText(area.getStrArea());

        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ahmed", "clickkkkkkkkkkkkkkkkk");

                Area selectedArea = areas.get(holder.getAdapterPosition());

                Bundle bundle = new Bundle();
                bundle.putString("countryName", selectedArea.getStrArea());

                if (bundle != null && Navigation.findNavController(v).getCurrentDestination() != null &&
                        Navigation.findNavController(v).getCurrentDestination().getId() == R.id.searchFragment) {
                    Navigation.findNavController(v).navigate(R.id.action_searchFragment_to_allCountryFragment, bundle);
                }
        }
        });
    }
    @Override
    public int getItemCount() {
        return areas.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.countryName);
        }
    }
}


