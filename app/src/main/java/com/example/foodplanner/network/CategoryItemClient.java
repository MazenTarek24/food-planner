package com.example.foodplanner.network;

import com.example.foodplanner.allcategoryitem.CategoryItemNetworkDelegate;
import com.example.foodplanner.apiservices.AreaSearchApi;
import com.example.foodplanner.apiservices.CategoryItemService;
import com.example.foodplanner.model.AllCategoryItem;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoryItemClient {

    CategoryItemNetworkDelegate categoryItemNetworkDelegate;
    AllCategoryItem allCategoryItem;
    CategoryItemService categoryItemService;


  private static CategoryItemClient instance = null;

    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    CategoryItemClient categoryItemClient;

   public CategoryItemClient()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        categoryItemService = retrofit.create(CategoryItemService.class);
    }

    public static CategoryItemClient getInstance() {
        if (instance == null) {
            instance = new CategoryItemClient();
        }
        return instance;
    }

    Callback callback = new Callback() {
        @Override
        public void onResponse(Call call, Response response) {
            if (response.isSuccessful())
            {

                allCategoryItem = (AllCategoryItem) response.body();
                categoryItemNetworkDelegate.onSuccessCategoryItemResult(allCategoryItem.getCategoryItemList());

            }
        }

        @Override
        public void onFailure(Call call, Throwable t) {
            categoryItemNetworkDelegate.OnFailureCategoryItemResult(t.getLocalizedMessage());
        }
    };

   public void enqueCall(CategoryItemNetworkDelegate categoryItemNetworkDelegate , String category)
   {
       this.categoryItemNetworkDelegate = categoryItemNetworkDelegate;
       categoryItemService.getMealsByCategory(category).enqueue(callback);
   }
}
