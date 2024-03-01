package mealsByIngradiantName.mealsByIngradiantNameView;

import java.util.List;

import model.Meal;

public interface ImealsByIngradiantNameActivity {

    void showAllMealsByIngadiantName(List<Meal> meals);

    void showErrorMsg(String msg);
}
