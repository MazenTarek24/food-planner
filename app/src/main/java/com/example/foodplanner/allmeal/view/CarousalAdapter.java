package com.example.foodplanner.allmeal.view;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;
import com.example.foodplanner.model.Meal;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CarousalAdapter extends RecyclerView.Adapter<CarousalAdapter.ViewHolder> {
    private List<Meal> carouselItemList = new ArrayList<>();

    OnSaveBtnClick onSaveBtnClick;

    public CarousalAdapter(OnSaveBtnClick onSaveBtnClick) {
        this.onSaveBtnClick = onSaveBtnClick;
    }

    public void setList(List<Meal> meals)
    {
        this.carouselItemList = meals;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.carousal_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Meal meal = carouselItemList.get(position);
        Log.d("RecyclerAdapter", "Product Name: " +
                meal.getStrMealThumb().toString() + ", Price: " + meal.getStrArea());

        holder.name.setText(meal.getStrMeal());

        Picasso.get().load(carouselItemList.get(position).getStrMealThumb()).into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("meal", carouselItemList.get(position));
                Navigation.findNavController(v).navigate
                        (R.id.action_homeFragment_to_detailsFragment2,bundle);
            }
        });


        holder.img.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                    onSaveBtnClick.insertMealToFavList(meal);
                    setImageRed(holder.img);
                }
            });

    }

    private void setImageRed(ImageView imageView)
    {
        imageView.setColorFilter(Color.RED);
    }

    @Override
    public int getItemCount() {
        return carouselItemList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name;

        ImageView img;

        Button button;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_carousal);
            name = itemView.findViewById(R.id.txt_meal);
            img = itemView.findViewById(R.id.str_fav);

        }
    }
}