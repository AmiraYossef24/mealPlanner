package getAllFavMeals.getAllFavMealsView;

import androidx.lifecycle.LiveData;

import java.util.List;

import model.Meal;

public interface IgetAllFavMeals {
    void showAllFav(LiveData<List<Meal>> meals);
}
