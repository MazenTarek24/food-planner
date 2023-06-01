package com.example.foodplanner.allmeal.view;

import com.example.foodplanner.model.Area;
import com.example.foodplanner.model.Category;
import com.example.foodplanner.model.Meal;

import java.util.List;

public interface MealsViewInterface {
    public void showMeal(List<Meal> productList);
    public void onFailToGetMeal(String message);

    public void showCategory(List<Category> categoryList);
    public void onFailToGetCategory(String message);

//    public void showCountry(List<Area> country);
//    public void onFailToGetCountry(String message);

}
