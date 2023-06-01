package com.example.foodplanner.search.presenter;

import com.example.foodplanner.model.Area;
import com.example.foodplanner.model.Ingredients;
import com.example.foodplanner.model.Repo;
import com.example.foodplanner.search.SearchNetworkDelegate;
import com.example.foodplanner.search.view.SearchInginterface;

import java.util.List;

public class PresenterSearchArea implements SearchNetworkDelegate {
    Repo repo;
    SearchInginterface searchInginterface;

    public PresenterSearchArea(Repo repo, SearchInginterface searchInginterface) {
        this.repo = repo;
        this.searchInginterface = searchInginterface;
    }

    public void getRemoteSearchingArea()
    {
        repo.getRemoteSearchArea(this);
    }

    @Override
    public void onSuccessIngResult(List<Ingredients> ingredientsList) {

    }

    @Override
    public void onFailureIngResult(String message) {

    }

    @Override
    public void onSuccessAreaResult(List<Area> areaList) {
        searchInginterface.showSearchArea(areaList);
    }

    @Override
    public void onFailureAreaResult(String message) {
        searchInginterface.onFailToGetArea(message);
    }
}
