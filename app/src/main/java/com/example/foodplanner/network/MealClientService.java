package com.example.foodplanner.network;

import com.example.foodplanner.apiservices.MealApiService;
import com.example.foodplanner.model.MealResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealClientService {

    private static MealClientService instance = null;
    MealApiService mealApiService;
    MealResponse mealResponse;

    NetworkDelegate networkDelegate;

    private MealClientService()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.themealdb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mealApiService = retrofit.create(MealApiService.class);
    }

    public static MealClientService getInstance()
    {
        if (instance == null)
        {
            instance = new MealClientService();
        }
        return instance;
    }



    Callback callback= new Callback() {
        @Override
        public void onResponse(Call call, Response response) {
            if (response.isSuccessful())
            {
                mealResponse = (MealResponse) response.body();
                networkDelegate.onSuccessMealResult(mealResponse.getMeals());
            }
        }

        @Override
        public void onFailure(Call call, Throwable t) {
            networkDelegate.OnFailureMealResult(t.getMessage());
        }
    };



    public void enqueueCall(NetworkDelegate networkDelegate)
    {
        this.networkDelegate = networkDelegate;
        mealApiService.getRandomMeal().enqueue(callback);
    }

}
