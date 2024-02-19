package mealsByCategory.mealsByCategoryPresenter;

import java.util.List;

import countryFragment.countryView.ICountryFragment;
import mealsByCategory.mealsByCategoryView.IMealsByCategory;
import model.Category;
import model.CategoryRepository;
import model.Meal;
import network.NetworkCallBack;

public class MealsByCategoryPresenter implements NetworkCallBack, IMealsByCategoryPresenter {

    CategoryRepository repo;
    IMealsByCategory view;

    public MealsByCategoryPresenter( IMealsByCategory view,CategoryRepository repo) {

        this.view = view;
        this.repo = repo;
    }

    @Override
    public void onSucessResult(List<Category> categoryList) {

    }

    @Override
    public void onFailureResult(String errorMsg) {

        view.showErrorMsg(errorMsg);
    }

    @Override
    public void onSucessResultRandomMeal(List<Meal> meals) {
    }

    @Override
    public void onSucessResultArea(List<Meal> countries) {


    }

    @Override
    public void onSuccessLookUpMealById(List<Meal> meals) {

    }

    @Override
    public void onSuccessFilterByCategory(List<Meal> meals) {
        view.showAllMealsByCategory(meals);

    }

    @Override
    public void onSuccessFilterByCountry(List<Meal> meals) {

    }

    @Override
    public void onSuccessSearchByName(List<Meal> meals) {

    }

    @Override
    public void onSuccessFilterByIngradiant(List<Meal> meals) {

    }


    @Override
    public void getMealsFilterByCategory(String categiry){
        repo.getAllMealsFromCategory(this,categiry);
    }

    @Override
    public void addToFav(Meal meal) {

        repo.addToFav(meal);
    }


    @Override
    public void deleteMeal(Meal meal) {
        repo.delete(meal);
    }
}