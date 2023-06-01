package com.example.foodplanner.model;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.allcategoryitem.CategoryItemNetworkDelegate;
import com.example.foodplanner.allcountry.CountryItemNetworkDelegate;
import com.example.foodplanner.db.MealDatabase;
import com.example.foodplanner.network.AreaSearchClient;
import com.example.foodplanner.network.CategoryClientService;
import com.example.foodplanner.network.CategoryItemClient;
import com.example.foodplanner.network.CountryClientService;
import com.example.foodplanner.network.IngSearchClient;
import com.example.foodplanner.network.MealClientService;
import com.example.foodplanner.network.NetworkDelegate;
import com.example.foodplanner.search.SearchNetworkDelegate;

import java.util.List;

public class Repo {
    MealClientService mealClientService;
    private static Repo repo = null;
    CategoryClientService categoryClientService;

    MealDatabase productDatabase;

    IngSearchClient ingSearchClient;

    AreaSearchClient areaSearchClient;

    CategoryItemClient categoryItemClient;

    CountryClientService countryClientService;



    public Repo(MealDatabase productDatabase, MealClientService mealClientService,
                CategoryClientService categoryClientService, IngSearchClient ingSearchClient,
                AreaSearchClient areaSearchClient, CategoryItemClient categoryItemClient , CountryClientService countryClientService)
    {
        this.mealClientService = mealClientService;
        this.categoryClientService = categoryClientService;
        this.productDatabase = productDatabase;
        this.ingSearchClient = ingSearchClient;
        this.areaSearchClient = areaSearchClient;
        this.categoryItemClient = categoryItemClient;
        this.countryClientService = countryClientService;

    }

    public static Repo getInstance(MealDatabase productDatabase, MealClientService mealClientService ,
                                   CategoryClientService categoryClientService , IngSearchClient ingSearchClient ,
                                   AreaSearchClient areaSearchClient , CategoryItemClient categoryItemClient , CountryClientService countryClientService)
    {
        if (repo == null)
        {
            repo = new Repo( productDatabase,mealClientService, categoryClientService ,
                    ingSearchClient, areaSearchClient , categoryItemClient , countryClientService);
        }
        return repo;
    }

    public void getCategoryItem(CategoryItemNetworkDelegate categoryItemNetworkDelegate , String category)
    {
        categoryItemClient.enqueCall(categoryItemNetworkDelegate , category);
    }

    public void getCountryItem(CountryItemNetworkDelegate countryItemNetworkDelegate ,String country)
    {
        countryClientService.enqueCall(countryItemNetworkDelegate,country);
    }

    public void getRemoteSearchArea(SearchNetworkDelegate searchNetworkDelegate)
    {
        areaSearchClient.enqueCall(searchNetworkDelegate);
    }

    // retrofit
    public void getRemoteMeal(NetworkDelegate networkDelegate)
    {
        mealClientService.enqueueCall(networkDelegate);
    }

    //retrofit
    public void getRemoteCategory(NetworkDelegate networkDelegate)
    {
        categoryClientService.enqueueCall(networkDelegate);
    }

    //retrofit
    public void getRemoteSearchIng(SearchNetworkDelegate searchNetworkDelegate)
    {
        ingSearchClient.enqueCall(searchNetworkDelegate);
    }


//    public List<Ingredients> filterIngredients(List<Ingredients> ingredientsList , String text) {
//        List<Ingredients> filterList = new ArrayList<>();
//        for (Ingredients ingredients : ingredientsList) {
//            if (ingredients.getStrIngredient().toLowerCase().contains(text.toLowerCase())) {
//                filterList.add(ingredients);
//            }
//        }
//        return filterList;
//    }

    //insert
    public void InsertToDataBase(Meal meal)
    {
        productDatabase.getMealsDao().insertMeal(meal);
    }

    //delete
    public void deleteFromDataBase(Meal meal)
    {
        productDatabase.getMealsDao().deleteProduct(meal);
    }

    //get Fav
    public LiveData<List<Meal>>getSavedMeal()
    {
        return productDatabase.getMealsDao().getSavedMeals();
    }

}
