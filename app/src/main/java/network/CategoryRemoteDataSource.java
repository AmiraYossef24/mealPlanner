package network;

import android.util.Log;

import model.Categories;
import model.Countries;
import model.Meal;
import model.Meals;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoryRemoteDataSource implements  ICategoryRemoteDataSource {

    private  static CategoryRemoteDataSource remoteDataSource=null;
    ApiInterface apiInterface;

    Retrofit retrofit;
    private static final String TAG="getCategories";
    private static final String BASE_URL="https://www.themealdb.com/api/json/v1/1/";

    public CategoryRemoteDataSource() {


       retrofit=new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL).build();
        apiInterface=retrofit.create(ApiInterface.class);

    }

    public static CategoryRemoteDataSource getInstance(){
        remoteDataSource=new CategoryRemoteDataSource();
        return remoteDataSource;
    }

    @Override
    public void makeNetworkCallBack(NetworkCallBack networkCallBack){

        Call<Categories> call=apiInterface.getAllCategories();
        call.enqueue(new Callback<Categories>() {
            @Override
            public void onResponse(Call<Categories> call, Response<Categories> response) {

                if(response.isSuccessful()) {
                    Log.i(TAG, "onResponse: ");
                    networkCallBack.onSucessResult(response.body().getCategories());
                }

            }

            @Override
            public void onFailure(Call<Categories> call, Throwable t) {

                Log.i(TAG, "onFailure: ");
                networkCallBack.onFailureResult(t.getMessage());
                t.printStackTrace();
            }
        });
    }

    @Override
    public void makeRandomMeal(NetworkCallBack networkCallBack){

        Call<Meals> call=apiInterface.getRandomMeal();
        call.enqueue(new Callback<Meals>() {
            @Override
            public void onResponse(Call<Meals> call, Response<Meals> response) {
                if(response.isSuccessful()) {
                    assert response.body() != null;
                    Log.i(TAG, "<<<<<<<<onResponse: success>>>>>");
                    networkCallBack.onSucessResultRandomMeal(response.body().getMeals());
                }


            }

            @Override
            public void onFailure(Call<Meals> call, Throwable t) {

                Log.i(TAG, "onFailure: ");
                networkCallBack.onFailureResult(t.getMessage());
                t.printStackTrace();
            }
        });


    }
    public void makeAreaCall(NetworkCallBack networkCallBack){
        Call<Meals> call=apiInterface.getAllArea();
        call.enqueue(new Callback<Meals>() {
            @Override
            public void onResponse(Call<Meals> call, Response<Meals> response) {
                if(response.isSuccessful()) {
                    assert response.body() != null;
                    Log.i(TAG, "<<<<<<<<onResponse: success>>>>>");
                    networkCallBack.onSucessResultArea(response.body().getMeals());
                    Log.i(TAG, ">>>>>getCountry Res: "+response.body());
                }

            }

            @Override
            public void onFailure(Call<Meals> call, Throwable t) {


                Log.i(TAG, "onFailure: ");
                networkCallBack.onFailureResult(t.getMessage());
                t.printStackTrace();
            }
        });
    }

    public void makeLookUpMealByIDCall(NetworkCallBack networkCallBack ,String id){
        Call<Meals> call=apiInterface.lookupMealById(id);
        call.enqueue(new Callback<Meals>() {
            @Override
            public void onResponse(Call<Meals> call, Response<Meals> response) {

                if(response.isSuccessful()) {
                    assert response.body() != null;
                    Log.i(TAG, "<<<<<<<<onResponse: success>>>>>");
                    networkCallBack.onSuccessLookUpMealById(response.body().getMeals());
                    Log.i(TAG, ">>>>>getMeals by id Res: "+response.body());
                }


            }

            @Override
            public void onFailure(Call<Meals> call, Throwable t) {

                Log.i(TAG, "onFailure: ");
                networkCallBack.onFailureResult(t.getMessage());
                t.printStackTrace();
            }
        });
    }

    public void makeFilterByCategoryCall (NetworkCallBack networkCallBack,String name){
        Call<Meals> call=apiInterface.getFilterByCategory(name);
        call.enqueue(new Callback<Meals>() {
            @Override
            public void onResponse(Call<Meals> call, Response<Meals> response) {
                if(response.isSuccessful()) {
                    assert response.body() != null;
                    Log.i(TAG, "<<<<<<<<onResponse: success>>>>>");
                    networkCallBack.onSuccessFilterByCategory(response.body().getMeals());
                    Log.i(TAG, ">>>>>getMeals by category name  is: "+response.body().getMeals());
                }
            }

            @Override
            public void onFailure(Call<Meals> call, Throwable t) {
                Log.i(TAG, "onFailure: ");
                networkCallBack.onFailureResult(t.getMessage());
                t.printStackTrace();
            }
        });
    }

    public void makeFilterByCountryCall(NetworkCallBack networkCallBack,String countryName){
        Call<Meals> call=apiInterface.getFilterByCountry(countryName);
        call.enqueue(new Callback<Meals>() {
            @Override
            public void onResponse(Call<Meals> call, Response<Meals> response) {
                if(response.isSuccessful()) {
                    assert response.body() != null;
                    Log.i(TAG, "<<<<<<<<onResponse: success>>>>>");
                    networkCallBack.onSuccessFilterByCountry(response.body().getMeals());
                    Log.i(TAG, ">>>>>getMeals by country name  is: "+response.body().getMeals());
                }
            }

            @Override
            public void onFailure(Call<Meals> call, Throwable t) {
                    Log.i(TAG, "onFailure: ");
                    networkCallBack.onFailureResult(t.getMessage());
                    t.printStackTrace();
            }
        });
    }

    public void makeSearchByNameCall(NetworkCallBack networkCallBack,String name){
        Call<Meals> call =apiInterface.getSearchByName(name);
        call.enqueue(new Callback<Meals>() {
            @Override
            public void onResponse(Call<Meals> call, Response<Meals> response) {
                if(response.isSuccessful()) {
                    assert response.body() != null;
                    Log.i(TAG, "<<<<<<<<onResponse: success>>>>>");
                    networkCallBack.onSuccessSearchByName(response.body().getMeals());
                    Log.i(TAG, ">>>>>getMeals by country name  is: "+response.body().getMeals());
                }
            }

            @Override
            public void onFailure(Call<Meals> call, Throwable t) {

                Log.i(TAG, "onFailure: ");
                networkCallBack.onFailureResult(t.getMessage());
                t.printStackTrace();
            }
        });
    }


}



