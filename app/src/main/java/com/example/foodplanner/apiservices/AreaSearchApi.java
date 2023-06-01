package com.example.foodplanner.apiservices;

import com.example.foodplanner.model.MealIngredients;
import com.example.foodplanner.model.MealsArea;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AreaSearchApi {
    @GET("list.php?a=list")
    Call<MealsArea> searchByArea();
}
