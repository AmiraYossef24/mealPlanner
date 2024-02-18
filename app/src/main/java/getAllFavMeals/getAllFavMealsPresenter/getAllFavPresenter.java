package getAllFavMeals.getAllFavMealsPresenter;

import java.util.List;

import getAllFavMeals.getAllFavMealsView.IgetAllFavMeals;
import model.CategoryRepository;
import model.Meal;

public class getAllFavPresenter implements IgetAllFavPresenter {
    CategoryRepository repo;
    IgetAllFavMeals view;

    public getAllFavPresenter(IgetAllFavMeals _view, CategoryRepository _repo ) {

        this.view = _view;
        this.repo = _repo;
    }


    @Override
    public List<Meal> getAllFav(){
        view.showAllFav(repo.getStoredCategory());
        return  null;

    }

    @Override
    public void delete(Meal meal){
        repo.delete(meal);
    }
}
