package mealsByCategory.mealsByCategoryView;

import java.util.List;

import model.Meal;

public interface IMealsByCategory {
    void showAllMealsByCategory(List<Meal> meals);

    void showErrorMsg(String msg);

}
