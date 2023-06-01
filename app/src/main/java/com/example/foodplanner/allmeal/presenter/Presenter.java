package com.example.foodplanner.allmeal.presenter;

import com.example.foodplanner.allmeal.view.MealsViewInterface;
import com.example.foodplanner.model.Category;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.Repo;
import com.example.foodplanner.network.NetworkDelegate;

import java.util.List;

public class Presenter implements NetworkDelegate {

    Repo repo;
    MealsViewInterface mealViewInterface;

    public Presenter(Repo repo, MealsViewInterface mealViewInterface)
    {
        this.repo = repo;
        this.mealViewInterface = mealViewInterface;
    }

    public void getRandomMeal()
    {
        repo.getRemoteMeal(this);
    }

    public void getCategory()
    {
        repo.getRemoteCategory(this);
    }

    @Override
    public void onSuccessMealResult(List<Meal> mealList) {
        mealViewInterface.showMeal(mealList);

    }

    @Override
    public void OnFailureMealResult(String message) {
        mealViewInterface.onFailToGetMeal(message);

    }

    @Override
    public void onSuccessCategoryResult(List<Category> categoryList) {
        mealViewInterface.showCategory(categoryList);
    }

    @Override
    public void OnFailureCategoryResult(String message) {
        mealViewInterface.onFailToGetCategory(message);
    }

    public void InsertToDataBase(Meal meal)
    {
        repo.InsertToDataBase(meal);
    }

    public void deleteFromDataBase(Meal meal)
    {
        repo.deleteFromDataBase(meal);
    }




}
