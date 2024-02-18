package homepage.presenter;

import java.util.List;

import homepage.view.IhomeFragment;
import model.Category;
import model.CategoryRepository;
import model.Country;
import model.Meal;
import network.NetworkCallBack;

public class FPresenter implements NetworkCallBack, IfPresenter {

    CategoryRepository _repo;
    IhomeFragment _view;

    public FPresenter(IhomeFragment _view, CategoryRepository _repo ) {

        this._view = _view;
        this._repo = _repo;
    }



    @Override
    public void getAllProduct(){
        _repo.getAllCategory(this);
    }

    @Override
    public void onSucessResult(List<Category> produts) {

        _view.showProducts(produts);
    }

    @Override
    public void onFailureResult(String errorMsg) {

        _view.showErrorMsg(errorMsg);
    }

    @Override
    public void onSucessResultRandomMeal( List<Meal> meal) {

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
}

