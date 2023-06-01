package com.example.foodplanner.plan;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;
import com.example.foodplanner.db.MealDatabase;
import com.example.foodplanner.model.Meal;

import java.util.ArrayList;
import java.util.List;

public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.ViewHolder> {

    private List<String> myList = new ArrayList<>();
    Context context;

    public PlanAdapter(Context context) {
        this.context = context;
    }

    public void setList() {
        myList.add("monday");
        myList.add("tuesday");
        myList.add("wednesday");
        myList.add("thursday");
        myList.add("friday");
        myList.add("saturday");
        myList.add("sunday");
        notifyDataSetChanged();
    }

        @NonNull
        @Override
        public PlanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.item_plan, parent, false);
            return new PlanAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull PlanAdapter.ViewHolder holder, int position) {
        holder.name.setText(myList.get(position));

        String day = myList.get(position);
            LiveData<List<Meal>> mealsList = MealDatabase.
                    getInstance(context).getMealsDao().getMealsOfDay(day);



//            mealsList.observe((LifecycleOwner) context, meals -> {
//                Log.d("PlanAdapter", "Received meals for " + day + ": " + meals.size());
//                if (!meals.isEmpty())
//                {
//                    Meal meal = meals.get(position);
////                    holder.name.setText(meal.getStrMeal());
//                }
//            });
//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    MealDatabase.getInstance(context).getMealsDao().getMealsOfDay(
//                            myList.get(position)).observe((LifecycleOwner) context, new Observer<List<Meal>>() {
//                        @Override
//                        public void onChanged(List<Meal> meals) {
//
//                        }
//                    }
//
//                    );
//                }
//            });
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("day", myList.get(position));
                    Navigation.findNavController(v).navigate
                            (R.id.action_PLanFragment_to_planDetailFragment,bundle);
                }
            });

        }


        @Override
        public int getItemCount() {
            return myList.size();
        }


    static class ViewHolder extends RecyclerView.ViewHolder {


            TextView name;

            ViewHolder(@NonNull View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.txt_list_plan);

            }
        }
    }


