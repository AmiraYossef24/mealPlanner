package DB;

import androidx.lifecycle.LiveData;

import java.util.List;

import model.Category;
import model.DateMeal;
import model.Meal;

public interface ICategoryLocalDataSource {
    LiveData<List<Meal>> getMyList();

    void delete(Meal meal);

    void insert(Meal meal);

    public void insertToCalender(DateMeal meal);
    public void deleteFromCalender(DateMeal meal);

    public LiveData<List<DateMeal>> getMyCalendarList();
}
