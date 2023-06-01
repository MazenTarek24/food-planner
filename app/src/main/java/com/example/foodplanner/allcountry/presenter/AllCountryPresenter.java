package com.example.foodplanner.allcountry.presenter;

import com.example.foodplanner.allcountry.CountryItemNetworkDelegate;
import com.example.foodplanner.allcountry.view.CountryItemInterface;
import com.example.foodplanner.model.CountryItem;
import com.example.foodplanner.model.Repo;

import java.util.List;

public class AllCountryPresenter implements CountryItemNetworkDelegate {

    Repo repo;
    CountryItemInterface countryItemInterface;

    public AllCountryPresenter(Repo repo, CountryItemInterface countryItemInterface) {
        this.repo = repo;
        this.countryItemInterface = countryItemInterface;
    }

    @Override
    public void onSuccessCountryItemResult(List<CountryItem> countryItems) {
        countryItemInterface.showCountryItem(countryItems);
    }

    @Override
    public void OnFailureCountryItemResult(String message) {
        countryItemInterface.onFailToGetCountryItem(message);
    }

    public void getCountryItem( String country)
    {
        repo.getCountryItem(this,country);
    }
}
