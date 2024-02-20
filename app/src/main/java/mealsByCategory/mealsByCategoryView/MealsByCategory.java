package mealsByCategory.mealsByCategoryView;

import static java.security.AccessController.getContext;

import static homepage.view.HomeAdapter.CATEGORY_NAME;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.mealplanner.R;

import java.util.ArrayList;
import java.util.List;

import DB.CategoryLocalDataSource;
import countryFragment.countryPresenter.CountryPresenter;
import countryFragment.countryView.CountryAdapter;
import homepage.presenter.FPresenter;
import homepage.view.Clickable;
import homepage.view.HomeAdapter;
import mealsByCategory.mealsByCategoryPresenter.MealsByCategoryPresenter;
import model.Category;
import model.CategoryRepository;
import model.DateMeal;
import model.Meal;
import network.CategoryRemoteDataSource;

public class MealsByCategory extends AppCompatActivity implements Clickable, IMealsByCategory {

    RecyclerView recyclerView;
    MealsByCategoryPresenter presenter;
    CategoryRepository repo;

    MealsByCategoryAdapter adapter;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals_by_category);

        recyclerView=findViewById(R.id.myRrecycleViewID);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager=new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new MealsByCategoryAdapter(this,new ArrayList<>(),this);
        recyclerView.setAdapter(adapter);
        presenter= new MealsByCategoryPresenter(this,CategoryRepository.getInstance(CategoryLocalDataSource.getInstance(this), CategoryRemoteDataSource.getInstance()));
        Intent intent=getIntent();
        Log.i("TAG", "the category name from view is : "+intent.getStringExtra(CATEGORY_NAME));
        presenter.getMealsFilterByCategory(intent.getStringExtra(CATEGORY_NAME));
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
    public void showAllMealsByCategory(List<Meal> meals){
        adapter.setMyList(meals);
        adapter.notifyDataSetChanged();

    }

    public void showErrorMsg(String msg){

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage(msg).setTitle("Error Message!");
        AlertDialog dialog=builder.create();
        dialog.show();

    }

}