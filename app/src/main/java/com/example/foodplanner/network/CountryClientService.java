package com.example.foodplanner.network;

import com.example.foodplanner.allcountry.CountryItemNetworkDelegate;
import com.example.foodplanner.apiservices.AreaSearchApi;
import com.example.foodplanner.apiservices.CountryApiService;
import com.example.foodplanner.model.AllCountryItem;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CountryClientService {

    private static CountryClientService instance = null;
    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";

    CountryItemNetworkDelegate countryItemNetworkDelegate;
    CountryApiService countryApiService;

    AllCountryItem allCountryItem;

    public CountryClientService()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        countryApiService = retrofit.create(CountryApiService.class);
    }


    public static CountryClientService getInstance() {
        if (instance == null) {
            instance = new CountryClientService();
        }
        return instance;
    }

    Callback callback = new Callback() {
        @Override
        public void onResponse(Call call, Response response) {
            if (response.isSuccessful())
            {
                allCountryItem = (AllCountryItem) response.body();
                countryItemNetworkDelegate.onSuccessCountryItemResult(allCountryItem.getCountryItems());
            }
        }

        @Override
        public void onFailure(Call call, Throwable t) {
            countryItemNetworkDelegate.OnFailureCountryItemResult(t.getLocalizedMessage());
        }
    };

    public void enqueCall(CountryItemNetworkDelegate countryItemNetworkDelegate , String category)
    {
        this.countryItemNetworkDelegate = countryItemNetworkDelegate;
        countryApiService.getMealsByCountry(category).enqueue(callback);
    }
}
