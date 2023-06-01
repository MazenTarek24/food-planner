package com.example.foodplanner.allcountry.view;

import com.example.foodplanner.model.CategoryItem;
import com.example.foodplanner.model.CountryItem;

import java.util.List;

public interface CountryItemInterface {
    public void showCountryItem(List<CountryItem> categoryItemList);
    public void onFailToGetCountryItem(String message);
}
