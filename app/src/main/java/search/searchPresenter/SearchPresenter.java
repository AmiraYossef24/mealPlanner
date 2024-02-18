package search.searchPresenter;

import java.util.List;

import model.Category;
import model.CategoryRepository;
import model.Meal;
import network.NetworkCallBack;
import search.searchView.ISearchActivity;

public class SearchPresenter implements NetworkCallBack, ISearchPresenter {

    CategoryRepository repo;
    ISearchActivity view;

    public SearchPresenter( ISearchActivity view,CategoryRepository repo) {

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
    public void onSucessResultArea(List<Meal> meals) {

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
        view.showResult(meals);

    }

    @Override
    public void searchByName(String name){
        repo.getSearchByName(this ,name);
    }


}
