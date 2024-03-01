package mealsByIngradiantName.mealsByIngradiantNamePresenter;

import model.DateMeal;
import model.Meal;

public interface Ipresenter {
    void addToFav(Meal meal);

    void getAllMealsByIngradiantName(String name);

    void addToCalendar(DateMeal meal);
}
