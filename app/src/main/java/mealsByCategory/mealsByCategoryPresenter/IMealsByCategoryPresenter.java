package mealsByCategory.mealsByCategoryPresenter;

import model.Meal;

public interface IMealsByCategoryPresenter {
    void getMealsFilterByCategory(String id);

    void addToFav(Meal meal);

    void deleteMeal(Meal meal);
}
