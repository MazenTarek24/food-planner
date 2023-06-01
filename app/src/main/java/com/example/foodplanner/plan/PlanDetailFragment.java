package com.example.foodplanner.plan;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.R;
import com.example.foodplanner.db.MealDatabase;
import com.example.foodplanner.model.Meal;

import java.util.ArrayList;
import java.util.List;


public class PlanDetailFragment extends Fragment {

    RecyclerView planRecycler;
    PlanDeatailAdapter planDeatailAdapter;

    String day;

    public PlanDetailFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_plan_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        planRecycler = view.findViewById(R.id.recycler_plan_detail);
        planDeatailAdapter = new PlanDeatailAdapter();
        planRecycler.setAdapter(planDeatailAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        planRecycler.setLayoutManager(linearLayoutManager);
        day = PlanDetailFragmentArgs.fromBundle(getArguments()).getDay();

        MealDatabase.getInstance(getContext()).getMealsDao().getMealsOfDay(day)
                .observe((LifecycleOwner) getContext(), new Observer<List<Meal>>() {
                    @Override
                    public void onChanged(List<Meal> meals) {
                        planDeatailAdapter.setList((ArrayList<Meal>) meals);
                        planDeatailAdapter.notifyDataSetChanged();
                        Log.d("mazen", String.valueOf(meals.size()));

                    }
                }

        );
    }
}
