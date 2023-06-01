package com.example.foodplanner.network;

import com.example.foodplanner.apiservices.MealSearchApi;
import com.example.foodplanner.model.MealIngredients;
import com.example.foodplanner.search.SearchNetworkDelegate;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class IngSearchClient {
   private static IngSearchClient instance = null;

    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    MealIngredients mealIngredients;

    SearchNetworkDelegate searchNetworkDelegate;
    MealSearchApi mealSearchApi;

    public IngSearchClient()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mealSearchApi = retrofit.create(MealSearchApi.class);
    }

    public static IngSearchClient getInstance() {
        if (instance == null) {
            instance = new IngSearchClient();
        }
            return instance;
    }

    Callback callback = new Callback() {
        @Override
        public void onResponse(Call call, Response response) {
            if (response.isSuccessful())
            {
                mealIngredients = (MealIngredients) response.body();
                searchNetworkDelegate.onSuccessIngResult(mealIngredients.getIngredientsList());
            }
        }
        @Override
        public void onFailure(Call call, Throwable t) {
            searchNetworkDelegate.onFailureIngResult(t.getLocalizedMessage());
        }
    };

    public void enqueCall(SearchNetworkDelegate searchNetworkDelegate)
    {
        this.searchNetworkDelegate = searchNetworkDelegate;
        mealSearchApi.searchByIngredient().enqueue(callback);
    }

}
