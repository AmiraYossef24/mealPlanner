package model;

import com.google.gson.annotations.SerializedName;

public class Country {

    private String strArea;

    public Country() {
    }

    public Country(String strArea) {
        this.strArea = strArea;
    }

    public String getStrArea() {
        return strArea;
    }

    public void setStrArea(String strArea) {
        this.strArea = strArea;
    }
}
