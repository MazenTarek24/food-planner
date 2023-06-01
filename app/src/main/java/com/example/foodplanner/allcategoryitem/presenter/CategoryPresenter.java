package com.example.foodplanner.allcategoryitem.presenter;

import com.example.foodplanner.allcategoryitem.CategoryItemInterface;
import com.example.foodplanner.allcategoryitem.CategoryItemNetworkDelegate;
import com.example.foodplanner.model.CategoryItem;
import com.example.foodplanner.model.Repo;

import java.util.List;

public class CategoryPresenter implements CategoryItemNetworkDelegate {

    Repo repo;
    CategoryItemInterface categoryItemInterface;

    public CategoryPresenter(Repo repo, CategoryItemInterface categoryItemInterface) {
        this.repo = repo;
        this.categoryItemInterface = categoryItemInterface;
    }

    @Override
    public void onSuccessCategoryItemResult(List<CategoryItem> categoryItemList) {
        categoryItemInterface.showCategoryItem(categoryItemList);
    }

    @Override
    public void OnFailureCategoryItemResult(String message) {
        categoryItemInterface.onFailToGetCategoryItem(message);
    }

    public void getCategoryItem(String category)
    {
        repo.getCategoryItem(this,category);
    }
}
