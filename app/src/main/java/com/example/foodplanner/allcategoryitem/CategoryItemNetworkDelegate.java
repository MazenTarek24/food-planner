package com.example.foodplanner.allcategoryitem;

import com.example.foodplanner.model.CategoryItem;
import com.example.foodplanner.model.Meal;

import java.util.List;

public interface CategoryItemNetworkDelegate {

    public void onSuccessCategoryItemResult(List<CategoryItem> CategoryList);
    public void OnFailureCategoryItemResult(String message);

}
