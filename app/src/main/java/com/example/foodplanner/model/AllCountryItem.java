package com.example.foodplanner.model;

import java.util.List;

public class AllCountryItem {
    List<CountryItem> meals;

    public List<CountryItem> getCountryItems() {
        return meals;
    }

    public void setCountryItems(List<CountryItem> meals) {
        this.meals = meals;
    }
}
