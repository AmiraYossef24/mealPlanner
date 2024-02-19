package DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import model.DateMeal;
import model.Meal;

@Database(entities = {Meal.class , DateMeal.class}, version = 2, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase{
    private static AppDataBase instance=null;
    public abstract ProductDAO getProductDAO();
    public abstract CalendarDAO getCalendarDAO();


    public static synchronized AppDataBase getInstance(Context context ){
        if(instance==null){
            instance= Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDataBase.class,
                    "ProductDb")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
