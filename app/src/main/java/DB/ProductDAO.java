package DB;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import model.Category;
import model.Meal;

@Dao
public interface ProductDAO {

    @Query("SELECT * FROM Meals")
    LiveData<List<Meal>> getAllStoredProduct();

    @Delete
    void deleteProduct(Meal meal);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertProduct(Meal meal);
}
