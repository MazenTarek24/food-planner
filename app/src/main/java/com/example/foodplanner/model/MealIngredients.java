package com.example.foodplanner.model;

import java.util.List;

public class MealIngredients {
    List<Ingredients> meals;

    public List<Ingredients> getIngredientsList() {
        return meals;
    }

    public void setIngredientsList(List<Ingredients> ingredientsList) {
        this.meals = ingredientsList;
    }
}
