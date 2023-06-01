package com.example.foodplanner.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodplanner.model.Meal;

@Database(entities = Meal.class, version = 1)
abstract public class MealDatabase extends RoomDatabase {
    private static MealDatabase instance;
    public abstract MealsDao getMealsDao();

    public static synchronized MealDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), MealDatabase.class, "products_database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }

        return instance;
    }
}
