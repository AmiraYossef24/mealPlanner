package network;


import io.reactivex.rxjava3.core.Single;
import model.Categories;
import model.Countries;
import model.Meal;
import model.Meals;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("categories.php")
    Single <Categories> getAllCategories();



    @GET("random.php")
    Single<Meals> getRandomMeal();

    @GET("list.php?a=list")
    Single<Meals> getAllArea();


    @GET("lookup.php?")
    Single<Meals> lookupMealById(@Query("i")String mealId);


    @GET("filter.php")
    Single<Meals> getFilterByCategory(@Query("c")String categoryName);

    @GET("filter.php")
    Single<Meals> getFilterByCountry(@Query("a") String countryName);

    @GET("search.php")
    Single<Meals> getSearchByName(@Query("s") String name);

    @GET("list.php?i=list")
    Single<Meals> getMealsByIngradiants();

    @GET("filter.php")
    Single<Meals> getMealsByIngradiantName(@Query("i") String name);

}
