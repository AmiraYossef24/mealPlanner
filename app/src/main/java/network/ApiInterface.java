package network;


import model.Categories;
import model.Countries;
import model.Meal;
import model.Meals;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("categories.php")
    Call<Categories> getAllCategories();



    @GET("random.php")
    Call<Meals> getRandomMeal();

    @GET("list.php?a=list")
    Call<Meals> getAllArea();


    @GET("lookup.php?")
    Call<Meals> lookupMealById(@Query("i")String mealId);


    @GET("filter.php")
    Call<Meals> getFilterByCategory(@Query("c")String categoryName);

    @GET("filter.php")
    Call<Meals> getFilterByCountry(@Query("a") String countryName);

    @GET("search.php")
    Call<Meals> getSearchByName(@Query("s") String name);
}
