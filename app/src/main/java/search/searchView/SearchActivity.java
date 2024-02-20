package search.searchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.mealplanner.PoorConnectionActivity;
import com.example.mealplanner.R;

import java.util.ArrayList;
import java.util.List;

import DB.CategoryLocalDataSource;
import homepage.view.Clickable;
import model.Category;
import model.CategoryRepository;
import model.DateMeal;
import model.Meal;
import network.CategoryRemoteDataSource;
import search.searchPresenter.SearchPresenter;
import io.reactivex.rxjava3.core.Observable;

public class SearchActivity extends AppCompatActivity implements Clickable, ISearchActivity {

    RecyclerView recyclerView;
    SearchPresenter presenter;
    CategoryRepository repo;
    ImageView back;

    SearchAdapter adapter;
    EditText search;

    LottieAnimationView  animationView;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        animationView=findViewById(R.id.my_animated_view);
        search=findViewById(R.id.editTextSearchId);
        recyclerView=findViewById(R.id.search_recycleID);
        back=findViewById(R.id.returnArrow3ID);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new SearchAdapter(this,new ArrayList<>(),this);
        recyclerView.setAdapter(adapter);
        presenter= new SearchPresenter(this,CategoryRepository.getInstance(CategoryLocalDataSource.getInstance(this), CategoryRemoteDataSource.getInstance()));

        presenter.searchByName(search.getText().toString());
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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

    }

    @Override
    public void clickOnCalendar(DateMeal meal) {

    }

    @Override
    public void clickOnDeleteCalendar(DateMeal meal) {

    }

    @Override
    public void showResult(List<Meal> meals) {

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                List<Meal> filterList=new ArrayList<>();
                Observable<Meal> textChange = Observable.fromIterable(meals);
                textChange.filter( v -> v.getStrMeal().toLowerCase().contains(s))
                        .subscribe(
                                item ->  {
                                    Log.i("TAG", "new item added: "+item);
                                    filterList.add(item);
                                },
                                error -> Log.i("TAG", "on Error: "+error),
                                ()->{
                                    animationView.setVisibility(View.GONE);

                                    Log.i("TAG", "completed: ");

                                    adapter.setMyList(filterList);
                                    adapter.notifyDataSetChanged();

                                }
                        );

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    @Override
    public void showErrorMsg(String msg) {

        startActivity(new Intent(this, PoorConnectionActivity.class));
        finish();
    }
}