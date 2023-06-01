package com.example.foodplanner.allcategoryitem.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.example.foodplanner.allcategoryitem.CategoryItemInterface;
import com.example.foodplanner.allcategoryitem.presenter.CategoryPresenter;
import com.example.foodplanner.db.MealDatabase;
import com.example.foodplanner.model.Category;
import com.example.foodplanner.model.CategoryItem;
import com.example.foodplanner.model.Repo;
import com.example.foodplanner.network.AreaSearchClient;
import com.example.foodplanner.network.CategoryClientService;
import com.example.foodplanner.network.CategoryItemClient;
import com.example.foodplanner.network.CountryClientService;
import com.example.foodplanner.network.IngSearchClient;
import com.example.foodplanner.network.MealClientService;

import java.util.ArrayList;
import java.util.List;


public class AllCategoryItemFragment extends Fragment implements CategoryItemInterface {

    private AdapterCategoryItem adapterCategoryItem;
    private RecyclerView recyclerView;
    private CategoryPresenter presenter;
    private Category categoryItem;

    public AllCategoryItemFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_all_category_item, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler_cat_Item);
        adapterCategoryItem = new AdapterCategoryItem();
        recyclerView.setAdapter(adapterCategoryItem);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);



        String categoryName = getArguments().getString("categoryName");

        Repo repo = new Repo(MealDatabase.getInstance(getContext()), MealClientService.getInstance(), CategoryClientService.getInstance(),
                IngSearchClient.getInstance(), AreaSearchClient.getInstance(), CategoryItemClient.getInstance(),
                CountryClientService.getInstance());

        presenter = new CategoryPresenter(repo, this);
        presenter.getCategoryItem(categoryName);
    }

    @Override
    public void showCategoryItem(List<CategoryItem> categoryItemList) {
        adapterCategoryItem.setList((ArrayList<CategoryItem>) categoryItemList);
        adapterCategoryItem.notifyDataSetChanged();
        Log.d("mazen tarek", String.valueOf(categoryItemList.size()));
    }

    @Override
    public void onFailToGetCategoryItem(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
