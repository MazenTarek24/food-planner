package com.example.foodplanner.model;

import java.util.List;

public class CategoryResponse {

    List<Category> categories;

    public void setCategoryList(List<Category> categoryList) {
        this.categories = categoryList;
    }

    public List<Category> getCategoryList() {
        return categories;
    }
}
