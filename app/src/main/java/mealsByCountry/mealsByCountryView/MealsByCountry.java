package mealsByCountry.mealsByCountryView;

import static countryFragment.countryView.CountryAdapter.AREA_NAME;
import static homepage.view.HomeAdapter.CATEGORY_NAME;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealplanner.PoorConnectionActivity;
import com.example.mealplanner.R;

import java.util.ArrayList;
import java.util.List;

import DB.CategoryLocalDataSource;
import homepage.view.Clickable;
import mealsByCategory.mealsByCategoryPresenter.MealsByCategoryPresenter;
import mealsByCountry.mealsByCountryPresenter.MealsByCountryPresenter;
import model.Category;
import model.CategoryRepository;
import model.DateMeal;
import model.Meal;
import network.CategoryRemoteDataSource;

public class MealsByCountry extends AppCompatActivity implements Clickable, IMealsByCountry {

    RecyclerView recyclerView;
    MealsByCountryPresenter presenter;
    CategoryRepository repo;

    MealsByCountryAdapter adapter;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals_by_category);

        recyclerView=findViewById(R.id.myRrecycleViewID);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager=new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new MealsByCountryAdapter(this,new ArrayList<>(),this);
        recyclerView.setAdapter(adapter);
        presenter= new MealsByCountryPresenter(this,CategoryRepository.getInstance(CategoryLocalDataSource.getInstance(this), CategoryRemoteDataSource.getInstance()));
        Intent intent=getIntent();
        Log.i("TAG", "the area name from view is : "+intent.getStringExtra(AREA_NAME));
        presenter.getMealsFilterByCountry(intent.getStringExtra(AREA_NAME));

    }

    @Override
    public void clickMethod(Category p) {

    }

    @Override
    public void clickOnMeal(Meal meal) {
        presenter.addToFav(meal);
        Toast.makeText(this, "Item added to favourite", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void clickOnDelete(Meal meal) {

        presenter.deleteMeal(meal);
        Toast.makeText(this, "Item removed from favourite", Toast.LENGTH_SHORT).show();


    }

    @Override
    public void clickOnCalendar(DateMeal meal) {
        presenter.addToCalendar(meal);

    }

    @Override
    public void clickOnDeleteCalendar(DateMeal meal) {

    }

    @Override
    public void showAllMealsByCountry(List<Meal> meals){
        adapter.setMyList(meals);
        adapter.notifyDataSetChanged();

    }

    public void showErrorMsg(String msg){
        startActivity(new Intent(this, PoorConnectionActivity.class));
        finish();


    }

}