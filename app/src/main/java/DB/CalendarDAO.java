package DB;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;
import androidx.room.Dao;


import model.DateMeal;


@Dao
public interface CalendarDAO {


    @Query("SELECT * FROM calendar")
    LiveData<List<DateMeal>> getAllStoredMeal();

    @Delete
    void deleteMeal(DateMeal meals);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMeal(DateMeal meals);

}
