package com.example.foodplanner.allcategoryitem;

import com.example.foodplanner.model.CategoryItem;
import com.example.foodplanner.model.Meal;

import java.util.List;

public interface CategoryItemInterface {
    public void showCategoryItem(List<CategoryItem> categoryItemList);
    public void onFailToGetCategoryItem(String message);
}
