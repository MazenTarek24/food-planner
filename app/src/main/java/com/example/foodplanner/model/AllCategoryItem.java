package com.example.foodplanner.model;

import java.util.ArrayList;
import java.util.List;

public class AllCategoryItem {

    List<CategoryItem> meals;

    public List<CategoryItem> getCategoryItemList() {
        return meals;
    }

    public void setCategoryItemList(List<CategoryItem> categoryItemList) {
        this.meals = categoryItemList;
    }
}
