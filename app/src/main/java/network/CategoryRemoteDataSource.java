package network;

import android.util.Log;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import model.Categories;
import model.Countries;
import model.Meal;
import model.Meals;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public class CategoryRemoteDataSource implements  ICategoryRemoteDataSource {

    private  static CategoryRemoteDataSource remoteDataSource=null;
    ApiInterface apiInterface;

    Retrofit retrofit;
    private static final String TAG="getCategories";
    private static final String BASE_URL="https://www.themealdb.com/api/json/v1/1/";

    public CategoryRemoteDataSource() {


       retrofit=new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
               .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
               .build();
        apiInterface=retrofit.create(ApiInterface.class);

    }

    public static CategoryRemoteDataSource getInstance(){
        remoteDataSource=new CategoryRemoteDataSource();
        return remoteDataSource;
    }

    @Override
    public void makeNetworkCallBack(NetworkCallBack networkCallBack){


        Single<Categories> categoriesSingle=apiInterface.getAllCategories();
        categoriesSingle.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(item -> {
                    networkCallBack.onSucessResult(item.getCategories());
                },
                        error->{
                    networkCallBack.onFailureResult(error.getMessage());
                        });
    }

    @Override
    public void makeRandomMeal(NetworkCallBack networkCallBack){

        Single<Meals> mealsSingle =  apiInterface.getRandomMeal();

        mealsSingle.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(item -> {
                    networkCallBack.onSucessResultRandomMeal(item.getMeals());
                },
                        error-> {
                    networkCallBack.onFailureResult(error.getMessage());
                        });


    }
    public void makeAreaCall(NetworkCallBack networkCallBack){
        Single<Meals> areaCall=apiInterface.getAllArea();

        areaCall.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(item -> {
                    networkCallBack.onSucessResultArea(item.getMeals());
                },
                        error -> {
                    networkCallBack.onFailureResult(error.getMessage());
                        });
    }

    public void makeLookUpMealByIDCall(NetworkCallBack networkCallBack ,String id){
        Single<Meals> call=apiInterface.lookupMealById(id);
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(item -> {
                    networkCallBack.onSuccessLookUpMealById(item.getMeals());
                },
                        error->{
                    networkCallBack.onFailureResult(error.getMessage());
                        });
    }

    public void makeFilterByCategoryCall (NetworkCallBack networkCallBack,String name){
        Single<Meals> call=apiInterface.getFilterByCategory(name);
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(item->{
                    networkCallBack.onSuccessFilterByCategory(item.getMeals());
                },
                        error -> {
                    networkCallBack.onFailureResult(error.getMessage());
                        });
    }

    public void makeFilterByCountryCall(NetworkCallBack networkCallBack,String countryName){
        Single<Meals> call=apiInterface.getFilterByCountry(countryName);
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(item -> {
                    networkCallBack.onSuccessFilterByCountry(item.getMeals());
                },
                        error -> {
                    networkCallBack.onFailureResult(error.getMessage());
                        });
    }

    public void makeSearchByNameCall(NetworkCallBack networkCallBack,String name){
        Single<Meals> call =apiInterface.getSearchByName(name);
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(item -> {
                    networkCallBack.onSuccessSearchByName(item.getMeals());
                },
                        error->{
                    networkCallBack.onFailureResult(error.getMessage());
                        });
    }

    public void makeFilterByIngradiants(NetworkCallBack networkCallBack){
        Single<Meals> call=apiInterface.getMealsByIngradiants();
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(item ->{
                    networkCallBack.onSuccessFilterByIngradiant(item.getMeals());
                },
                        error ->{
                    networkCallBack.onFailureResult(error.getMessage());
                        });
    }

    public void makeFilterByIngradiantName(NetworkCallBack networkCallBack,String name){
        Single<Meals> call=apiInterface.getMealsByIngradiantName(name);
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(item -> {
                            networkCallBack.onSuccessFilterByIngradiantName(item.getMeals());
                        },
                        error->{
                            networkCallBack.onFailureResult(error.getMessage());
                        });

    }


}



