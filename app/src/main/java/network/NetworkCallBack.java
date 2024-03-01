package network;

import java.util.List;

import model.Category;
import model.Country;
import model.Meal;

public interface NetworkCallBack  {
    public void onSucessResult(List<Category> categoryList);
    public void onFailureResult(String errorMsg);

    public void onSucessResultRandomMeal(List<Meal> meals);


    void onSucessResultArea(List<Meal> meals);
    void onSuccessLookUpMealById(List<Meal> meals);

    void onSuccessFilterByCategory(List<Meal> meals);

    void onSuccessFilterByCountry(List<Meal> meals);

    void onSuccessSearchByName(List<Meal> meals);

    void onSuccessFilterByIngradiant(List<Meal> meals);

    void onSuccessFilterByIngradiantName(List<Meal> meals);
}
