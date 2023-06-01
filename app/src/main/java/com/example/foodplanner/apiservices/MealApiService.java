package com.example.foodplanner.apiservices;

import com.example.foodplanner.model.CategoryResponse;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.MealResponse;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;

public interface MealApiService {
    @GET("random.php")
    Call<MealResponse> getRandomMeal();

}