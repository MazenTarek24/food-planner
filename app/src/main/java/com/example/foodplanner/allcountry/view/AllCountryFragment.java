package com.example.foodplanner.allcountry.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.R;
import com.example.foodplanner.allcountry.presenter.AllCountryPresenter;
import com.example.foodplanner.db.MealDatabase;
import com.example.foodplanner.model.CountryItem;
import com.example.foodplanner.model.Repo;
import com.example.foodplanner.network.AreaSearchClient;
import com.example.foodplanner.network.CategoryClientService;
import com.example.foodplanner.network.CategoryItemClient;
import com.example.foodplanner.network.CountryClientService;
import com.example.foodplanner.network.IngSearchClient;
import com.example.foodplanner.network.MealClientService;

import java.util.ArrayList;
import java.util.List;


public class AllCountryFragment extends Fragment implements CountryItemInterface {

    private CountryAdapter countryAdapter;
    private RecyclerView recyclerView;
    private AllCountryPresenter allCountryPresenter;

    public AllCountryFragment() {
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler_country_Item);
        countryAdapter = new CountryAdapter();
        recyclerView.setAdapter(countryAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        String countryName = getArguments().getString("countryName");

        Repo repo = new Repo(MealDatabase.getInstance(getContext()), MealClientService.getInstance(), CategoryClientService.getInstance(),
                IngSearchClient.getInstance(), AreaSearchClient.getInstance(), CategoryItemClient.getInstance(),
                CountryClientService.getInstance());

        allCountryPresenter = new AllCountryPresenter(repo, this);
        allCountryPresenter.getCountryItem(countryName);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_country, container, false);
    }

    @Override
    public void showCountryItem(List<CountryItem> categoryItemList) {
        countryAdapter.setList((ArrayList<CountryItem>) categoryItemList);
        countryAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailToGetCountryItem(String message) {
    }
}