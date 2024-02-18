package countryFragment.countryPresenter;

import java.util.List;

import countryFragment.countryView.ICountryFragment;
import model.Category;
import model.CategoryRepository;
import model.Country;
import model.Meal;
import network.NetworkCallBack;

public class CountryPresenter implements NetworkCallBack, ICountryPresenter {

    CategoryRepository repo;
    ICountryFragment view;

    public CountryPresenter( ICountryFragment view,CategoryRepository repo) {

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
        view.showAllArea(countries);

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
    public void getAllArea() {
        repo.getAllArea(this);
    }


}
