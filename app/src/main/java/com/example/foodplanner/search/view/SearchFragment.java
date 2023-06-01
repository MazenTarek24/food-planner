package com.example.foodplanner.search.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.example.foodplanner.allmeal.presenter.Presenter;
import com.example.foodplanner.allmeal.view.CategoryAdapter;
import com.example.foodplanner.allmeal.view.MealsViewInterface;
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
import com.example.foodplanner.search.presenter.PresenterSearchIng;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment implements SearchInginterface , MealsViewInterface {

    private RecyclerView recyclerView;
    private SearchAdapter adapter;
    PresenterSearchIng presenterSearchIng;
    List<Ingredients> allIngredients;
    SearchView searchView;

    private CategoryAdapter categoryAdapter;
    private RecyclerView recycler_search_cat;
    Presenter presenter;
    SearchView searchViewCat;
    List<Category> allCategory;

    private AreaAdapter areaAdapter;
    private RecyclerView recycler_search_area;
    List<Area> allAreas;
    SearchView searchViewArea;
    PresenterSearchArea presenterSearchArea;

    public SearchFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycler_search);
        adapter = new SearchAdapter();
        recyclerView.setAdapter(adapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(
                getContext(),3);
        recyclerView.setLayoutManager(gridLayoutManager);
        gridLayoutManager.setOrientation(RecyclerView.HORIZONTAL);


        recycler_search_cat = view.findViewById(R.id.recycler_search_cat);
        LinearLayoutManager linearLayoutManagerCategory = new LinearLayoutManager(getContext());
        linearLayoutManagerCategory.setOrientation(RecyclerView.HORIZONTAL);
        recycler_search_cat.setLayoutManager(linearLayoutManagerCategory);
        categoryAdapter = new CategoryAdapter();
        recycler_search_cat.setAdapter(categoryAdapter);


        recycler_search_area = view.findViewById(R.id.recycler_search_country);
        LinearLayoutManager linearLayoutManagerCountry = new LinearLayoutManager(getContext());
        linearLayoutManagerCountry.setOrientation(RecyclerView.HORIZONTAL);
        recycler_search_area.setLayoutManager(linearLayoutManagerCountry);
        areaAdapter = new AreaAdapter(getContext());
        recycler_search_area.setAdapter(areaAdapter);



        Repo repo = Repo.getInstance( MealDatabase.getInstance(getContext()),
        MealClientService.getInstance(), CategoryClientService.getInstance() ,
                IngSearchClient.getInstance() , AreaSearchClient.getInstance(), CategoryItemClient.getInstance()
        , CountryClientService.getInstance());
        presenterSearchIng = new PresenterSearchIng(repo, this);
        presenterSearchIng.getRemoteSearchingIng();

        presenter = new Presenter(repo,this);
        presenter.getCategory();

        presenterSearchArea = new PresenterSearchArea(repo,this);
        presenterSearchArea.getRemoteSearchingArea();




        searchView = view.findViewById(R.id.search);
        searchView.setOnQueryTextListener(queryTextListener);

        searchViewCat = view.findViewById(R.id.search_cat);
        searchViewCat.setOnQueryTextListener(queryTextListenerCat);

        searchViewArea = view.findViewById(R.id.search_country);
        searchViewArea.setOnQueryTextListener(queryTextListenerArea);
    }




    private final SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            filterIngredients(newText);
            filterCategory(newText);
            return true;
        }
    };

    private final SearchView.OnQueryTextListener queryTextListenerCat = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            filterCategory(newText);
            return true;
        }
    };

    private final SearchView.OnQueryTextListener queryTextListenerArea = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            filterArea(newText);
            return true;
        }

    };


       private void filterIngredients(String searchText) {
        List<Ingredients> filterList = new ArrayList<>();
        for (Ingredients ingredients : allIngredients)
        {
            if (ingredients.getStrIngredient().toLowerCase().contains(searchText.toLowerCase()))
            {
                filterList.add(ingredients);
            }
        }
        adapter.setIngredientsList(filterList);
        adapter.notifyDataSetChanged();
    }


    private void filterCategory(String searchText)
    {
        List<Category> categoryList = new ArrayList<>();
        for (Category category : allCategory)
        {
            if (category.getStrCategory().toLowerCase().contains(searchText.toLowerCase()))
            {
                categoryList.add(category);
            }
        }
        categoryAdapter.setList(categoryList);
        categoryAdapter.notifyDataSetChanged();
    }

    private void filterArea(String searchText)
    {
        List<Area> areas = new ArrayList<>();
        for (Area area : allAreas)
        {
            if (area.getStrArea().toLowerCase().contains(searchText.toLowerCase()))
            {
                areas.add(area);
            }
        }
        areaAdapter.setList(areas);
        areaAdapter.notifyDataSetChanged();
    }



    @Override
    public void showSearchIng(List<Ingredients> ingredientsList) {
        adapter.setIngredientsList((ArrayList<Ingredients>) ingredientsList);
        allIngredients = new ArrayList<>(ingredientsList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFailToGetIng(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showSearchArea(List<Area> areas) {
        areaAdapter.setList((ArrayList<Area>) areas);
        allAreas = new ArrayList<>(areas);
        areaAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailToGetArea(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showMeal(List<Meal> productList) {


    }
    @Override
    public void onFailToGetMeal(String message) {
    }

    @Override
    public void showCategory(List<Category> categoryList) {
           categoryAdapter.setList((ArrayList<Category>) categoryList);
           allCategory = new ArrayList<>(categoryList);
           categoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailToGetCategory(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}