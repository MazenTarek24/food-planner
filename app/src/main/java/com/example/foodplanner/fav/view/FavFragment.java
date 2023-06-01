package com.example.foodplanner.fav.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.R;
import com.example.foodplanner.db.MealDatabase;
import com.example.foodplanner.fav.presenter.FavPresenter;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.Repo;
import com.example.foodplanner.network.AreaSearchClient;
import com.example.foodplanner.network.CategoryClientService;
import com.example.foodplanner.network.CategoryItemClient;
import com.example.foodplanner.network.CountryClientService;
import com.example.foodplanner.network.IngSearchClient;
import com.example.foodplanner.network.MealClientService;

import java.util.List;


public class FavFragment extends Fragment  implements DeleteFavFromDb{

    RecyclerView recyclerView;
    FavAdapter favAdapter;
    FavPresenter favPresenter;


    public FavFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fav, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler_fav);
        favAdapter = new FavAdapter(this);
        recyclerView.setAdapter(favAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        Repo repo = Repo.getInstance(MealDatabase.getInstance(getContext()), MealClientService.getInstance(),
                CategoryClientService.getInstance() ,
                IngSearchClient.getInstance(), AreaSearchClient.getInstance(), CategoryItemClient.getInstance(), CountryClientService.getInstance());
        favPresenter = new FavPresenter(repo);

        favPresenter.getSavedProduct().observe(getViewLifecycleOwner(), new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> meals) {
           favAdapter.setMealList(meals);
           favAdapter.notifyDataSetChanged();
            }
        });
    }


    @Override
    public void deleteFavFromDb(Meal meal) {
        favPresenter.deleteFromDataBase(meal);
    }
}