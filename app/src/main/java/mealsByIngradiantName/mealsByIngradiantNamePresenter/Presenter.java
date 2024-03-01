package mealsByIngradiantName.mealsByIngradiantNamePresenter;

import java.util.List;

import mealsByIngradiantName.mealsByIngradiantNameView.ImealsByIngradiantNameActivity;
import model.Category;
import model.CategoryRepository;
import model.DateMeal;
import model.Meal;
import network.NetworkCallBack;

public class Presenter implements NetworkCallBack, Ipresenter {

    CategoryRepository repo;
    ImealsByIngradiantNameActivity view;

    public Presenter(ImealsByIngradiantNameActivity view, CategoryRepository repo) {

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

    }

    @Override
    public void onSuccessSearchByName(List<Meal> meals) {

    }

    @Override
    public void onSuccessFilterByIngradiant(List<Meal> meals) {

    }

    @Override
    public void onSuccessFilterByIngradiantName(List<Meal> meals) {
        view.showAllMealsByIngadiantName(meals);
    }


    @Override
    public void addToFav(Meal meal) {

        repo.addToFav(meal);
    }

    @Override
    public void getAllMealsByIngradiantName(String name){
        repo.getAllFilterByIngradiantName(this,name);
    }



    @Override
    public void addToCalendar(DateMeal meal){
        repo.addToCalendar(meal);
    }
}