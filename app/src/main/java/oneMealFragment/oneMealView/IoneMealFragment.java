package oneMealFragment.oneMealView;

import java.util.List;

import model.Meal;

public interface IoneMealFragment {

    void showRandomMeal(List<Meal> meal);
    void showErrorMsg(String msg);
}
