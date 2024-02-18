package mealsByCountry.mealsByCountryView;

import java.util.List;

import model.Meal;

public interface IMealsByCountry {
    void showAllMealsByCountry(List<Meal> meals);

    void showErrorMsg(String msg);

}
