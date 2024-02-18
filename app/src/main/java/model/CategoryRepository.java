package model;

import androidx.lifecycle.LiveData;

import java.util.List;

import DB.CategoryLocalDataSource;
import network.CategoryRemoteDataSource;
import network.NetworkCallBack;

public class CategoryRepository {


    CategoryLocalDataSource localDataSource;
    CategoryRemoteDataSource remoteDataSource;
    private  static CategoryRepository repo=null;

    public CategoryRepository(CategoryLocalDataSource localDataSource, CategoryRemoteDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    public static CategoryRepository getInstance(CategoryLocalDataSource localDataSource, CategoryRemoteDataSource remoteDataSource){
        if(repo==null){
            repo=new CategoryRepository(localDataSource,remoteDataSource);
        }
        return repo;
    }

    public void getAllCategory(NetworkCallBack networkCallBack){
        remoteDataSource.makeNetworkCallBack(networkCallBack);
    }
    public LiveData<List<Meal>> getStoredCategory(){
        return localDataSource.getMyList();

    }
    public void insert(Meal meal){
        localDataSource.insert(meal);
    }
    public  void delete(Meal meal){
        localDataSource.delete(meal);
    }

    public void getRandomMeal(NetworkCallBack networkCallBack){
        remoteDataSource.makeRandomMeal(networkCallBack);
    }

    public void getAllArea(NetworkCallBack networkCallBack){
        remoteDataSource.makeAreaCall(networkCallBack);
    }

    public void getAllDetails(NetworkCallBack networkCallBack,String id){
        remoteDataSource.makeLookUpMealByIDCall(networkCallBack,id);
    }

    public void getAllMealsFromCategory(NetworkCallBack networkCallBack,String name){
        remoteDataSource.makeFilterByCategoryCall(networkCallBack,name);
    }

    public void addToFav(Meal meal){
        localDataSource.insert(meal);
    }

    public void deleteMeal(Meal meal){
        localDataSource.delete(meal);
    }

    public void getAllMealsFromCountry(NetworkCallBack networkCallBack,String name){
        remoteDataSource.makeFilterByCountryCall(networkCallBack,name);
    }
    public void getSearchByName(NetworkCallBack networkCallBack,String name){
        remoteDataSource.makeSearchByNameCall(networkCallBack,name);
    }


}
