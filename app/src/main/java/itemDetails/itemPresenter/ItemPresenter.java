package itemDetails.itemPresenter;

import java.util.List;

import countryFragment.countryPresenter.ICountryPresenter;
import countryFragment.countryView.ICountryFragment;
import itemDetails.itemView.IitemDetails;
import model.Category;
import model.CategoryRepository;
import model.DateMeal;
import model.Meal;
import network.NetworkCallBack;

public class ItemPresenter implements NetworkCallBack, IitemPresenter {

    CategoryRepository repo;
    IitemDetails view;

    public ItemPresenter( IitemDetails view,CategoryRepository repo) {

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
        view.showAllDetails(meals);

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
    public void addToFav(Meal meal) {
        repo.addToFav(meal);
    }


    @Override
    public void addToCalender(DateMeal meal) {
        repo.addToCalendar(meal);

    }
    @Override
    public void getAllDetails(String id){
        repo.getAllDetails(this,id);

    }
}
