package mealsByIngradiantName.mealsByIngradiantNameView;

import static countryFragment.countryView.CountryAdapter.AREA_NAME;
import static mealsByIngradiants.mealsByIngradiantView.IngradiantAdapter.INGRADIANT_NAME;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.mealplanner.PoorConnectionActivity;
import com.example.mealplanner.R;

import java.util.ArrayList;
import java.util.List;

import DB.CategoryLocalDataSource;
import homepage.view.Clickable;
import mealsByCountry.mealsByCountryPresenter.MealsByCountryPresenter;
import mealsByCountry.mealsByCountryView.MealsByCountryAdapter;
import mealsByIngradiantName.mealsByIngradiantNamePresenter.Presenter;
import model.Category;
import model.CategoryRepository;
import model.DateMeal;
import model.Meal;
import network.CategoryRemoteDataSource;

public class mealsByIngradiantNameActivity extends AppCompatActivity implements Clickable, ImealsByIngradiantNameActivity {

    RecyclerView recyclerView;
    Presenter presenter;
    CategoryRepository repo;

    MealsByIngradiantNameAdapter adapter;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals_by_ingradiant_name);

        recyclerView=findViewById(R.id.finallyRecycleViewID);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager=new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new MealsByIngradiantNameAdapter(this,new ArrayList<>(),this);
        recyclerView.setAdapter(adapter);
        presenter= new Presenter(this,CategoryRepository.getInstance(CategoryLocalDataSource.getInstance(this), CategoryRemoteDataSource.getInstance()));
        Intent intent=getIntent();
        Log.i("TAG", "the ingradiant name from view is : "+intent.getStringExtra(INGRADIANT_NAME));
        presenter.getAllMealsByIngradiantName(intent.getStringExtra(INGRADIANT_NAME));

    }


    @Override
    public void clickMethod(Category p) {

    }

    @Override
    public void clickOnMeal(Meal meal) {
        presenter.addToFav(meal);


    }

    @Override
    public void clickOnDelete(Meal meal) {

    }

    @Override
    public void clickOnCalendar(DateMeal meal) {
        presenter.addToCalendar(meal);

    }

    @Override
    public void clickOnDeleteCalendar(DateMeal meal) {

    }

    @Override
    public void showAllMealsByIngadiantName(List<Meal> meals) {

        adapter.setMyList(meals);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void showErrorMsg(String msg) {

        startActivity(new Intent(this, PoorConnectionActivity.class));
        finish();
    }
}