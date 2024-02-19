package calendar.calendarPresenter;

import java.util.List;

import calendar.calendarView.ICalendarActivity;
import model.CategoryRepository;
import model.DateMeal;
import model.Meal;

public class CalendarPresenter implements ICalendarPresenter {
    CategoryRepository repo;
     ICalendarActivity view;

    public CalendarPresenter(ICalendarActivity _view, CategoryRepository _repo ) {

        this.view = _view;
        this.repo = _repo;
    }


    @Override
    public List<DateMeal> getAllPlans(){
        view.showAllPlans(repo.getStoredPlans());
        return  null;

    }

    @Override
    public void deleteFromPlans(DateMeal meal){
        repo.deleteFromCalendar(meal);
    }
}
