package com.example.foodplanner.fav.presenter;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.Repo;

import java.util.List;

public class FavPresenter {

    Repo repo;

    public FavPresenter(Repo repo) {
        this.repo = repo;
    }

    public LiveData<List<Meal>> getSavedProduct()
    {
        return repo.getSavedMeal();
    }

    public void deleteFromDataBase(Meal meal)
    {
        repo.deleteFromDataBase(meal);
    }

}
