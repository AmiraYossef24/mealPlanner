package DB;

import androidx.lifecycle.LiveData;

import java.util.List;

import model.Category;
import model.Meal;

public interface ICategoryLocalDataSource {
    LiveData<List<Meal>> getMyList();

    void delete(Meal meal);

    void insert(Meal meal);
}
