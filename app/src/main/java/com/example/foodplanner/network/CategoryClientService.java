package com.example.foodplanner.network;

import com.example.foodplanner.apiservices.CategoryApiService;
import com.example.foodplanner.model.CategoryResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoryClientService {

    private static CategoryClientService instance = null;
    CategoryApiService categoryApiService;
    CategoryResponse categoryResponse;

    NetworkDelegate networkDelegate ;

    CategoryClientService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.themealdb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        categoryApiService = retrofit.create(CategoryApiService.class);
    }

    public static CategoryClientService getInstance()
    {
        if (instance == null)
        {
            instance = new CategoryClientService();
        }
        return instance;
    }


    Callback callback= new Callback() {
        @Override
        public void onResponse(Call call, Response response) {
            if (response.isSuccessful())
            {
                categoryResponse = (CategoryResponse) response.body();
                networkDelegate.onSuccessCategoryResult(categoryResponse.getCategoryList());
            }
        }

        @Override
        public void onFailure(Call call, Throwable t) {
            networkDelegate.OnFailureCategoryResult(t.getLocalizedMessage());
        }
    };

    public void enqueueCall(NetworkDelegate networkDelegate)
    {
        this.networkDelegate = networkDelegate;
        categoryApiService.getCategory().enqueue(callback);
    }
}
