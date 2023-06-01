package com.example.foodplanner.search;

import com.example.foodplanner.model.Area;
import com.example.foodplanner.model.Ingredients;

import java.util.List;

public interface SearchNetworkDelegate {
    public void onSuccessIngResult(List<Ingredients> ingredientsList);
    public void onFailureIngResult(String message);

    public void onSuccessAreaResult(List<Area> areaList);
    public void onFailureAreaResult(String message);

}
