package com.example.foodplanner.apiservices;

import com.example.foodplanner.model.AllCategoryItem;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CategoryItemService {
    @GET("filter.php")
    Call<AllCategoryItem> getMealsByCategory(@Query("c") String category);

}
