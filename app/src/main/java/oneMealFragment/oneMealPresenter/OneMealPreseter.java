package oneMealFragment.oneMealPresenter;

import android.content.Context;

import java.util.List;

import model.Category;
import model.CategoryRepository;
import model.Country;
import model.Meal;
import network.NetworkCallBack;
import oneMealFragment.oneMealView.IoneMealFragment;

public class OneMealPreseter implements NetworkCallBack,  IOneMealPreseter {
    CategoryRepository repo;
    IoneMealFragment view;

    public OneMealPreseter(IoneMealFragment view, CategoryRepository repo) {

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
    public void onSucessResultRandomMeal(List<Meal> meal) {
            view.showRandomMeal(meal);

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
    public void getRandomMeal() {

        repo.getRandomMeal(this);
    }

}
