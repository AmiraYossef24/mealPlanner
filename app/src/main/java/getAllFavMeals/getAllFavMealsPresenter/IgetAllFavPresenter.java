package getAllFavMeals.getAllFavMealsPresenter;

import java.util.List;

import model.Meal;

public interface IgetAllFavPresenter {
    List<Meal> getAllFav();

    void delete(Meal meal);
}
