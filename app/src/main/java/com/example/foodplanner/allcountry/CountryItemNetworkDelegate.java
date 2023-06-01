package com.example.foodplanner.allcountry;

import com.example.foodplanner.model.CategoryItem;
import com.example.foodplanner.model.CountryItem;

import java.util.List;

public interface CountryItemNetworkDelegate {

    public void onSuccessCountryItemResult(List<CountryItem> countryItems);
    public void OnFailureCountryItemResult(String message);
}
