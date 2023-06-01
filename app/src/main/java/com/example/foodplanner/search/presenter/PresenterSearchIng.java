package com.example.foodplanner.search.presenter;

import com.example.foodplanner.model.Area;
import com.example.foodplanner.model.Ingredients;
import com.example.foodplanner.model.Repo;
import com.example.foodplanner.search.SearchNetworkDelegate;
import com.example.foodplanner.search.view.SearchInginterface;

import java.util.ArrayList;
import java.util.List;

public class PresenterSearchIng implements SearchNetworkDelegate {
    Repo repo;
    SearchInginterface searchInginterface;

    private List<Ingredients> allIngredients;

    public PresenterSearchIng(Repo repo, SearchInginterface searchInginterface)
    {
        this.repo = repo;
        this.searchInginterface = searchInginterface;
    }

    @Override
    public void onSuccessIngResult(List<Ingredients> ingredientsList) {
        searchInginterface.showSearchIng(ingredientsList);
    }

    @Override
    public void onFailureIngResult(String message) {
        searchInginterface.onFailToGetIng(message);
    }

    @Override
    public void onSuccessAreaResult(List<Area> areaList) {
        searchInginterface.showSearchArea(areaList);
    }

    @Override
    public void onFailureAreaResult(String message) {
        searchInginterface.onFailToGetArea(message);
    }


    public void getRemoteSearchingIng()
    {
        repo.getRemoteSearchIng(this);
    }



//    public void filterIngredients(List<Ingredients> allIngredients, String searchText) {
//        repo.filterIngredients(allIngredients, searchText);
//    }
}

