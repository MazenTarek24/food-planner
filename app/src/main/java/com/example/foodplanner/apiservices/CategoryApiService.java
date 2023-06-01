package com.example.foodplanner.apiservices;

import com.example.foodplanner.model.CategoryResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryApiService {

    @GET("categories.php")
    Call<CategoryResponse> getCategory();
}
