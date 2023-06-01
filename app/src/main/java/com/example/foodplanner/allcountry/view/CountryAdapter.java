package com.example.foodplanner.allcountry.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;
import com.example.foodplanner.model.CountryItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {
    ArrayList<CountryItem> myList = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_all_country, parent, false));
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

    public void setList(ArrayList<CountryItem> myList) {
        this.myList = myList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

//        TextView movieTV;
           TextView name;
           ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.txt_country_item);
            imageView = itemView.findViewById(R.id.image_country);

        }
    }
}
