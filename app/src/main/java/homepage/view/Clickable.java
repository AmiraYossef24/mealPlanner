package homepage.view;

import model.Category;
import model.Meal;

public interface Clickable {

    public void clickMethod(Category p);

    public  void clickOnMeal(Meal meal);

    public void clickOnDelete(Meal meal);
    public void clickOnCalendar(Meal meal);
}
