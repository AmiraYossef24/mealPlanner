package mealsByIngradiants.mealsByIngradiantsPresenter;

import java.util.List;

import countryFragment.countryPresenter.ICountryPresenter;
import countryFragment.countryView.ICountryFragment;
import mealsByIngradiants.mealsByIngradiantView.IingradiantFragment;
import model.Category;
import model.CategoryRepository;
import model.Meal;
import network.NetworkCallBack;

public class IngradiantPresenter implements NetworkCallBack, IingradiantPresenter {

    CategoryRepository repo;
    IingradiantFragment view;

    public IngradiantPresenter( IingradiantFragment view,CategoryRepository repo) {

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
        view.showAllIngradiants(meals);
    }

    @Override
    public void onSuccessFilterByIngradiantName(List<Meal> meals) {

    }


    @Override
    public void getAllIngradiants(){
        repo.getAllFilterByIngradiants(this);
    }


}

