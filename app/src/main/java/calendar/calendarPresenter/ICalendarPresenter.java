package calendar.calendarPresenter;

import java.util.List;

import model.DateMeal;

public interface ICalendarPresenter {
    List<DateMeal> getAllPlans();

    void deleteFromPlans(DateMeal meal);
}
