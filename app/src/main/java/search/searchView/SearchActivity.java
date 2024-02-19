package search.searchView;

import static homepage.view.HomeAdapter.CATEGORY_NAME;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.example.mealplanner.R;

import java.util.ArrayList;
import java.util.List;

import DB.CategoryLocalDataSource;
import homepage.view.Clickable;
import mealsByCategory.mealsByCategoryPresenter.MealsByCategoryPresenter;
import mealsByCategory.mealsByCategoryView.MealsByCategoryAdapter;
import model.Category;
import model.CategoryRepository;
import model.DateMeal;
import model.Meal;
import model.Meals;
import network.CategoryRemoteDataSource;
import search.searchPresenter.SearchPresenter;
import io.reactivex.rxjava3.core.Observable;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

public class SearchActivity extends AppCompatActivity implements Clickable, ISearchActivity {

    RecyclerView recyclerView;
    SearchPresenter presenter;
    CategoryRepository repo;

    SearchAdapter adapter;
    EditText search;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        search=findViewById(R.id.editTextSearchId);
        recyclerView=findViewById(R.id.search_recycleID);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new SearchAdapter(this,new ArrayList<>(),this);
        recyclerView.setAdapter(adapter);
        presenter= new SearchPresenter(this,CategoryRepository.getInstance(CategoryLocalDataSource.getInstance(this), CategoryRemoteDataSource.getInstance()));

        presenter.searchByName(search.getText().toString());

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

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage(msg).setTitle("Error Message!");
        AlertDialog dialog=builder.create();
        dialog.show();
    }
}