package itemDetails.itemView;

import static java.security.AccessController.getContext;
import static oneMealFragment.oneMealView.oneMealFragment.MEAL_ID;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mealplanner.PoorConnectionActivity;
import com.example.mealplanner.R;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import DB.CategoryLocalDataSource;
import countryFragment.countryPresenter.CountryPresenter;
import getAllFavMeals.getAllFavMealsPresenter.getAllFavPresenter;
import homepage.view.Clickable;
import itemDetails.itemPresenter.ItemPresenter;
import model.Category;
import model.CategoryRepository;
import model.DateMeal;
import model.Meal;
import network.CategoryRemoteDataSource;
import androidx.appcompat.app.AppCompatActivity;

public class ItemDetails extends AppCompatActivity implements Clickable, IitemDetails {

    Toolbar toolbar;

    TextView name;
    TextView category;
    TextView area;
    TextView instructions;
    TextView ingradiants;
    ImageView imageView;
    String mealId;

    ItemPresenter presenter;

    WebView webView;
    Clickable clickable;

    FloatingActionsMenu fabMenu;
    FloatingActionButton fabItem1, fabItem2;
    String dayName;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        toolbar=findViewById(R.id.myToolBarID);
        setSupportActionBar(toolbar);
        imageView=findViewById(R.id.itemImageID);
        webView=findViewById(R.id.webViewID);
        toolbar.setTitle("");
        fabMenu = findViewById(R.id.fab_menu);
        fabItem1=findViewById(R.id.fav_fabID);
        fabItem2=findViewById(R.id.calender_fabID);
        name=findViewById(R.id.mealNameViewerID);
        category=findViewById(R.id.categoryViewerID);
        area=findViewById(R.id.areaViewerID);
        instructions=findViewById(R.id.instructionViewerID);
        ingradiants=findViewById(R.id.ingradiantsViewerID);
        Intent intent=getIntent();
        mealId=intent.getStringExtra(MEAL_ID);
        Log.i("TAG", "the id of meal from itemDetails  is : "+mealId);
        presenter=new ItemPresenter(this, CategoryRepository.getInstance(CategoryLocalDataSource.getInstance(this), CategoryRemoteDataSource.getInstance()));
        presenter.getAllDetails(mealId);
        Log.i("TAG", "get all area from preseter is: " + presenter.toString());




    }

    public void showErrorMsg(String msg){
        startActivity(new Intent(this, PoorConnectionActivity.class));
        finish();
    }

    @Override
    public void showAllDetails(List<Meal> meals) {

        Log.i("TAG", "image url is : "+meals.get(0).getStrMealThumb());
        Picasso.get().load(meals.get(0).getStrMealThumb().toString()).into(imageView);
        name.setText(meals.get(0).getStrMeal());
        category.setText(meals.get(0).getStrCategory());
        area.setText(meals.get(0).getStrArea());
        instructions.setText(meals.get(0).getStrInstructions());
        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        int day=calendar.get(Calendar.DAY_OF_MONTH);

        StringBuilder ingredientsBuilder = new StringBuilder();
        for (int i = 1; i <= 20; i++) {
            String ingredient = getIngredient(meals.get(0), i);
            if (ingredient != null && !ingredient.isEmpty()) {
                ingredientsBuilder.append(ingredient).append("\n");
            }
        }
        ingradiants.setText(ingredientsBuilder.toString());

        fabItem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               presenter.addToFav(meals.get(0));
                Log.i("TAG", "item added to fav from itemdetails: "+meals.get(0));
                Toast.makeText(ItemDetails.this, "Item added to favourite", Toast.LENGTH_SHORT).show();
            }
        });

        fabItem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                long currentTime = System.currentTimeMillis();
                long maxTime = currentTime + (7 * 24 * 60 * 60 * 1000);
                DatePickerDialog datePickerDialog=new DatePickerDialog(
                        ItemDetails.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(year, month, dayOfMonth);
                        Date selectedDate = calendar.getTime();
                        dayName = convertDateToDayName(selectedDate);
                        presenter.addToCalender(new DateMeal(
                                meals.get(0).getIdMeal(),
                                meals.get(0).getStrMeal(),
                                meals.get(0).getStrMealThumb(),
                                dayName));
                        Log.i("TAG", "item added to calendar from itemdetails: "+meals.get(0).getStrMeal()+dayName);
                        Toast.makeText(ItemDetails.this, "Item added to your plan "+dayName, Toast.LENGTH_SHORT).show();


                       // Toast.makeText(ItemDetails.this, "Selected date: " + dayName, Toast.LENGTH_SHORT).show();
                    }
                },year,month,day
                );

                datePickerDialog.getDatePicker().setMinDate(currentTime);
                datePickerDialog.getDatePicker().setMaxDate(maxTime);
                datePickerDialog.show();


            }
        });



    }

    private String convertDateToDayName(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE", Locale.getDefault());
        return sdf.format(date);
    }

    private String getIngredient(Meal meal, int index) {
        switch (index) {
            case 1: return meal.getStrIngredient1();
            case 2: return meal.getStrIngredient2();
            case 3: return meal.getStrIngredient3();
            case 4: return meal.getStrIngredient4();
            case 5: return meal.getStrIngredient5();
            case 6: return meal.getStrIngredient6();
            case 7: return meal.getStrIngredient7();
            case 8: return meal.getStrIngredient8();
            case 9: return meal.getStrIngredient9();
            case 10: return meal.getStrIngredient10();
            case 11: return meal.getStrIngredient11();
            case 12: return meal.getStrIngredient12();
            case 13: return meal.getStrIngredient13();
            case 14: return meal.getStrIngredient14();
            case 15: return meal.getStrIngredient15();
            case 16: return meal.getStrIngredient16();
            case 17: return meal.getStrIngredient17();
            case 18: return meal.getStrIngredient18();
            case 19: return meal.getStrIngredient19();
            case 20: return meal.getStrIngredient20();

            default: return null;
        }
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

        presenter.addToCalender(meal);

    }

    @Override
    public void clickOnDeleteCalendar(DateMeal meal) {

    }

}