package com.example.foodplanner.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Category implements Parcelable {

    private String idCategory;
    private String strCategory;
    private String strCategoryThumb;
    private String strCategoryDescription;

    // Constructors
    public Category() {
    }

    public Category(String idCategory, String strCategory, String strCategoryThumb, String strCategoryDescription) {
        this.idCategory = idCategory;
        this.strCategory = strCategory;
        this.strCategoryThumb = strCategoryThumb;
        this.strCategoryDescription = strCategoryDescription;
    }

    protected Category(Parcel in) {
        idCategory = in.readString();
        strCategory = in.readString();
        strCategoryThumb = in.readString();
        strCategoryDescription = in.readString();
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

    // Getters and Setters
    public String getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }

    public String getStrCategory() {
        return strCategory;
    }

    public void setStrCategory(String strCategory) {
        this.strCategory = strCategory;
    }

    public String getStrCategoryThumb() {
        return strCategoryThumb;
    }

    public void setStrCategoryThumb(String strCategoryThumb) {
        this.strCategoryThumb = strCategoryThumb;
    }

    public String getStrCategoryDescription() {
        return strCategoryDescription;
    }

    public void setStrCategoryDescription(String strCategoryDescription) {
        this.strCategoryDescription = strCategoryDescription;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(idCategory);
        dest.writeString(strCategory);
        dest.writeString(strCategoryThumb);
        dest.writeString(strCategoryDescription);
    }
}

