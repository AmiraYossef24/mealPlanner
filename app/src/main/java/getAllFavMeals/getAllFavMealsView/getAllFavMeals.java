package getAllFavMeals.getAllFavMealsView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.airbnb.lottie.LottieAnimationView;
import com.example.mealplanner.AppAcitivity;
import com.example.mealplanner.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import DB.CategoryLocalDataSource;
import getAllFavMeals.getAllFavMealsPresenter.IgetAllFavPresenter;
import getAllFavMeals.getAllFavMealsPresenter.getAllFavPresenter;
import homepage.view.Clickable;
import model.Category;
import model.CategoryRepository;
import model.Meal;
import network.CategoryRemoteDataSource;

public class getAllFavMeals extends AppCompatActivity implements Clickable,IgetAllFavMeals, BottomNavigationView.OnNavigationItemSelectedListener {
    RecyclerView recyclerView;
    FavMealAdapter adapter;
    IgetAllFavPresenter presenter;
    BottomNavigationView bottomNavigationView;
    View myAnimatedView;
    LottieAnimationView animationView;
    private static final String TAG="RecycleView";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_all_fav_meals);
         animationView = findViewById(R.id.my_animated_view);




        recyclerView=findViewById(R.id.recycleId);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter=new FavMealAdapter( this,new ArrayList<>(),  this);
        presenter=new getAllFavPresenter(this, CategoryRepository.getInstance(CategoryLocalDataSource.getInstance(this), CategoryRemoteDataSource.getInstance()));
        recyclerView.setAdapter(adapter);
        presenter.getAllFav();

    }

    @Override
    public void showAllFav(LiveData<List<Meal>> meals){

        meals.observe(this, new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> meals) {
                if(adapter != null) {
                    animationView.setVisibility(View.GONE);
                    adapter.setMyList(meals);
                    adapter.notifyDataSetChanged();
                }else{
                    animationView.setVisibility(View.VISIBLE);
                    animationView
                            .playAnimation();
                }
            }
        });

    }

    @Override
    public void clickMethod(Category p) {

    }

    @Override
    public void clickOnMeal(Meal meal) {

    }

    @Override
    public void clickOnDelete(Meal meal) {

        presenter.delete(meal);

    }

    @Override
    public void clickOnCalendar(Meal meal) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.fav_page){
            Intent intent=new Intent(this,getAllFavMeals.class);
            startActivity(intent);
            return true;
        }
        if(item.getItemId()==R.id.home_page){
            Intent intent2=new Intent(this, AppAcitivity.class);
            startActivity(intent2);
            return true;

        }
        return false;
    }
}