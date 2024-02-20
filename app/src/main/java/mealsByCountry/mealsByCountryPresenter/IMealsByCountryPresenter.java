package mealsByCountry.mealsByCountryPresenter;

import model.DateMeal;
import model.Meal;

public interface IMealsByCountryPresenter {
    void getMealsFilterByCountry(String country);

    void addToFav(Meal meal);

    void deleteMeal(Meal meal);
    public void addToCalendar(DateMeal meal);
}
