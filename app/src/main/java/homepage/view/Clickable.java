package homepage.view;

import model.Category;
import model.DateMeal;
import model.Meal;

public interface Clickable {

    public void clickMethod(Category p);

    public  void clickOnMeal(Meal meal);

    public void clickOnDelete(Meal meal);
    public void clickOnCalendar(DateMeal meal);
    public void clickOnDeleteCalendar(DateMeal meal);
}
