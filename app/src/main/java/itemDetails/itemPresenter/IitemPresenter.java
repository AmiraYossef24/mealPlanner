package itemDetails.itemPresenter;

import model.DateMeal;
import model.Meal;

public interface IitemPresenter {
    void addToFav(Meal meal);

    public void addToCalender(DateMeal meal);

    void getAllDetails(String id);
}
