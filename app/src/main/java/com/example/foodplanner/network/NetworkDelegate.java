package com.example.foodplanner.network;

import com.example.foodplanner.model.Category;
import com.example.foodplanner.model.Ingredients;
import com.example.foodplanner.model.Meal;

import java.util.List;

public interface NetworkDelegate {

    public void onSuccessMealResult(List<Meal> mealList);
    public void OnFailureMealResult(String message);

    public void onSuccessCategoryResult(List<Category> categoryList);
    public void OnFailureCategoryResult(String message);





}
