package calendar.calendarView;

import androidx.lifecycle.LiveData;

import java.util.List;

import model.DateMeal;

public interface ICalendarActivity {

    void showAllPlans(LiveData<List<DateMeal>> meals);
}
