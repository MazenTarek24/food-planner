package com.example.foodplanner.apiservices;

import com.example.foodplanner.model.AllCategoryItem;
import com.example.foodplanner.model.AllCountryItem;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CountryApiService {
    @GET("filter.php")
    Call<AllCountryItem> getMealsByCountry(@Query("a") String country);
}
