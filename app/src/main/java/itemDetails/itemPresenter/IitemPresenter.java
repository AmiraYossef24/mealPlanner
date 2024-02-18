package itemDetails.itemPresenter;

import model.Meal;

public interface IitemPresenter {
    void addToFav(Meal meal);

    void addToCalender(Meal meal);

    void getAllDetails(String id);
}
