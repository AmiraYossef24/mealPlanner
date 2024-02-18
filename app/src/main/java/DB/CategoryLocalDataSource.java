package DB;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

import model.Category;
import model.Meal;

public class CategoryLocalDataSource implements ICategoryLocalDataSource {
    private Context context;
    private ProductDAO productDAO;
    private LiveData<List<Meal>> myList;

    public  static CategoryLocalDataSource repo=null;
    public CategoryLocalDataSource(Context context2){
        this.context=context2;
        AppDataBase db = AppDataBase.getInstance(context2);
        productDAO = db.getProductDAO();
        myList = productDAO.getAllStoredProduct();

    }
    public static CategoryLocalDataSource getInstance(Context context){
        if(repo==null){
            repo =new CategoryLocalDataSource(context);
        }
        return repo;
    }

    @Override
    public LiveData<List<Meal>> getMyList(){
        return myList;
    }

    CategoryLocalDataSource(Application application) {
        AppDataBase db = AppDataBase.getInstance(application);
        productDAO = db.getProductDAO();
        myList = productDAO.getAllStoredProduct();
    }
    @Override
    public void delete(Meal meal){

        new Thread(new Runnable() {
            @Override
            public void run() {
                productDAO.deleteProduct(meal);
            }
        }).start();
    }

    @Override
    public void insert(Meal meal){
        new Thread(new Runnable() {
            @Override
            public void run() {
                productDAO.insertProduct(meal);
            }
        }).start();
    }



}
