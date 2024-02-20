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
import android.widget.ImageView;

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
import model.DateMeal;
import model.Meal;
import network.CategoryRemoteDataSource;

public class getAllFavMeals extends AppCompatActivity implements Clickable,IgetAllFavMeals {
    RecyclerView recyclerView;
    FavMealAdapter adapter;
    ImageView back;
    IgetAllFavPresenter presenter;
    LottieAnimationView animationView;
    private static final String TAG="RecycleView";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_all_fav_meals);
         animationView = findViewById(R.id.my_animated_view);

        back=findViewById(R.id.returnArrowID);


        recyclerView=findViewById(R.id.recycleId);



        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter=new FavMealAdapter( this,new ArrayList<>(),  this);
        presenter=new getAllFavPresenter(this, CategoryRepository.getInstance(CategoryLocalDataSource.getInstance(this), CategoryRemoteDataSource.getInstance()));
        recyclerView.setAdapter(adapter);
        presenter.getAllFav();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void showAllFav(LiveData<List<Meal>> meals){

        meals.observe(this, new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> meals) {
                if(adapter != null) {
                    animationView.setVisibility(View.VISIBLE);
                    animationView
                            .playAnimation();
                    adapter.setMyList(meals);
                    adapter.notifyDataSetChanged();
                }else{

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
    public void clickOnCalendar(DateMeal meal) {

    }

    @Override
    public void clickOnDeleteCalendar(DateMeal meal) {

    }


}