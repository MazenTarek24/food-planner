package com.example.foodplanner.network;

import com.example.foodplanner.apiservices.AreaSearchApi;
import com.example.foodplanner.apiservices.MealSearchApi;
import com.example.foodplanner.model.MealIngredients;
import com.example.foodplanner.model.MealsArea;
import com.example.foodplanner.search.SearchNetworkDelegate;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AreaSearchClient {

        private static AreaSearchClient instance = null;

        private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
        MealsArea mealsArea;

        SearchNetworkDelegate searchNetworkDelegate;


        AreaSearchApi areaSearchApi;

        public AreaSearchClient()
        {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
                areaSearchApi = retrofit.create(AreaSearchApi.class);
        }

        public static AreaSearchClient getInstance() {
            if (instance == null) {
                instance = new AreaSearchClient();
            }
            return instance;
        }

        Callback callback = new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if (response.isSuccessful())
                {
                    mealsArea = (MealsArea) response.body();
                    searchNetworkDelegate.onSuccessAreaResult(mealsArea.getMeals());
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                searchNetworkDelegate.onFailureAreaResult(t.getLocalizedMessage());
            }
        };

        public void enqueCall(SearchNetworkDelegate searchNetworkDelegate)
        {
            this.searchNetworkDelegate = searchNetworkDelegate;
            areaSearchApi.searchByArea().enqueue(callback);
        }

    }

