package model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Countries {
    private List<Country> countries;

    public Countries() {
    }

    public Countries(List<Country> countries) {
        this.countries = countries;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }
}
