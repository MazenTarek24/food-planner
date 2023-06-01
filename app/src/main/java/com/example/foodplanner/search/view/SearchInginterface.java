package com.example.foodplanner.search.view;

import com.example.foodplanner.model.Area;
import com.example.foodplanner.model.Ingredients;

import java.util.List;

public interface SearchInginterface {
    public void showSearchIng(List<Ingredients> ingredientsList);
//    List<Ingredients> filterIngredients(List<Ingredients> allIngredients, String searchText);

    public void onFailToGetIng(String message);

    public void showSearchArea(List<Area> areas);
//    List<Ingredients> filterIngredients(List<Ingredients> allIngredients, String searchText);

    public void onFailToGetArea(String message);

}
