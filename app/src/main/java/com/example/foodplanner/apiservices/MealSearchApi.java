package com.example.foodplanner.apiservices;

import com.example.foodplanner.model.Ingredients;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.MealIngredients;
import com.example.foodplanner.model.MealResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealSearchApi {

    @GET("list.php?i=list")
    Call<MealIngredients> searchByIngredient();


}
