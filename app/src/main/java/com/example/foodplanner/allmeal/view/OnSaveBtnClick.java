package com.example.foodplanner.allmeal.view;

import com.example.foodplanner.model.Meal;

public interface OnSaveBtnClick {
    public void insertMealToFavList(Meal meal);
    public void deleteFavFromDb(Meal meal);

}
