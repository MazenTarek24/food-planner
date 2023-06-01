package com.example.foodplanner.allmeal.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.example.foodplanner.allmeal.presenter.Presenter;
import com.example.foodplanner.db.MealDatabase;
import com.example.foodplanner.model.Area;
import com.example.foodplanner.model.Category;
import com.example.foodplanner.model.Ingredients;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.Repo;
import com.example.foodplanner.network.AreaSearchClient;
import com.example.foodplanner.network.CategoryClientService;
import com.example.foodplanner.network.CategoryItemClient;
import com.example.foodplanner.network.CountryClientService;
import com.example.foodplanner.network.IngSearchClient;
import com.example.foodplanner.network.MealClientService;
import com.example.foodplanner.search.presenter.PresenterSearchArea;
import com.example.foodplanner.search.view.AreaAdapter;
import com.example.foodplanner.search.view.SearchInginterface;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements MealsViewInterface , OnSaveBtnClick , SearchInginterface {

    private CarousalAdapter carousalAdapter;
    private RecyclerView recyclerView;
    private Presenter presenter;

    private CategoryAdapter categoryAdapter;
    private RecyclerView recyclerView_category;

    private AreaAdapter areaAdapter;
    RecyclerView recycler_country;
    PresenterSearchArea presenterSearchArea;


    public HomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler_slider);
        carousalAdapter = new CarousalAdapter(this);
        recyclerView.setAdapter(carousalAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);


        recyclerView_category = view.findViewById(R.id.recycler_category);
        LinearLayoutManager linearLayoutManagerCategory = new LinearLayoutManager(getContext());
        linearLayoutManagerCategory.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView_category.setLayoutManager(linearLayoutManagerCategory);
        categoryAdapter = new CategoryAdapter();
        recyclerView_category.setAdapter(categoryAdapter);

        recycler_country = view.findViewById(R.id.recycler_country);
        LinearLayoutManager linearLayoutManagerCountry = new LinearLayoutManager(getContext());
        linearLayoutManagerCountry.setOrientation(RecyclerView.HORIZONTAL);
        recycler_country.setLayoutManager(linearLayoutManagerCountry);
        areaAdapter = new AreaAdapter(getContext());
        recycler_country.setAdapter(areaAdapter);

        Repo repo = Repo.getInstance( MealDatabase.getInstance(getContext()),
                MealClientService.getInstance(),
                CategoryClientService.getInstance() , IngSearchClient.getInstance() ,
                AreaSearchClient.getInstance() , CategoryItemClient.getInstance() , CountryClientService.getInstance());
        presenter = new Presenter(repo, this);
        presenter.getRandomMeal();
        presenter.getCategory();

        presenterSearchArea = new PresenterSearchArea(repo,this);
        presenterSearchArea.getRemoteSearchingArea();
    }


    @Override
    public void showMeal(List<Meal> productList) {
        carousalAdapter.setList((ArrayList<Meal>) productList);
        carousalAdapter.notifyDataSetChanged();

    }

    @Override
    public void onFailToGetMeal(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showCategory(List<Category> categoryList) {
        categoryAdapter.setList(categoryList);
        categoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailToGetCategory(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void insertMealToFavList(Meal meal) {
        presenter.InsertToDataBase(meal);
    }

    @Override
    public void deleteFavFromDb(Meal meal) {
        presenter.deleteFromDataBase(meal);
    }

    @Override
    public void showSearchIng(List<Ingredients> ingredientsList) {

    }

    @Override
    public void onFailToGetIng(String message) {

    }

    @Override
    public void showSearchArea(List<Area> areas) {
        areaAdapter.setList(areas);
        areaAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailToGetArea(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
