package mealsByCountry.mealsByCountryPresenter;

import java.util.List;

import mealsByCategory.mealsByCategoryView.IMealsByCategory;
import mealsByCountry.mealsByCountryView.IMealsByCountry;
import model.Category;
import model.CategoryRepository;
import model.Meal;
import network.NetworkCallBack;

public class MealsByCountryPresenter implements NetworkCallBack, IMealsByCountryPresenter {

    CategoryRepository repo;
    IMealsByCountry view;

    public MealsByCountryPresenter(IMealsByCountry view, CategoryRepository repo) {

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


    }

    @Override
    public void onSuccessFilterByCountry(List<Meal> meals) {

        view.showAllMealsByCountry(meals);
    }

    @Override
    public void onSuccessSearchByName(List<Meal> meals) {

    }

    @Override
    public void onSuccessFilterByIngradiant(List<Meal> meals) {

    }


    @Override
    public void addToFav(Meal meal) {

        repo.addToFav(meal);
    }

    public void getMealsFilterByCountry(String country){
        repo.getAllMealsFromCountry(this,country);
    }


    @Override
    public void deleteMeal(Meal meal) {
        repo.delete(meal);
    }
}