package mealsByIngradiants.mealsByIngradiantView;

import java.util.List;

import model.Meal;

public interface IingradiantFragment {
    void showAllIngradiants(List<Meal> ingradiants);

    void showErrorMsg(String msg);
}
