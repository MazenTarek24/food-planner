package com.example.foodplanner.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodplanner.model.Meal;

import java.util.List;

@Dao
public interface MealsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insertMeal(Meal meal);

    @Delete
    void
    deleteProduct(Meal meal);

    @Query("SELECT * FROM meal_table")
    LiveData<List<Meal>> getSavedMeals();

    @Query("UPDATE meal_table SET day = :day WHERE idMeal = :id")
    void updateColumnDay(String id, String day);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insertAllFav(List<Meal> meals);

    @Query("DELETE FROM meal_table")
    public void deleteAllMeals() ;

    @Query("SELECT * From meal_table WHERE day = :day")
    LiveData<List<Meal>> getMealsOfDay(String day);

}
