package itemDetails.itemView;

import static java.security.AccessController.getContext;
import static oneMealFragment.oneMealView.oneMealFragment.MEAL_ID;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mealplanner.R;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;

import DB.CategoryLocalDataSource;
import countryFragment.countryPresenter.CountryPresenter;
import homepage.view.Clickable;
import itemDetails.itemPresenter.ItemPresenter;
import model.Category;
import model.CategoryRepository;
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

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        toolbar=findViewById(R.id.myToolBarID);
        setSupportActionBar(toolbar);
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
        presenter = new ItemPresenter(this, CategoryRepository.getInstance(CategoryLocalDataSource.getInstance(getApplicationContext()), CategoryRemoteDataSource.getInstance()));
        presenter.getAllDetails(mealId);
        Log.i("TAG", "get all area from preseter is: " + presenter.toString());




    }

    public void showErrorMsg(String msg){
        AlertDialog.Builder builder=new AlertDialog.Builder(getApplicationContext());
        builder.setMessage(msg).setTitle("Error Message!");
        AlertDialog dialog=builder.create();
        dialog.show();

    }

    @Override
    public void showAllDetails(List<Meal> meals) {
        name.setText(meals.get(0).getStrMeal());
        category.setText(meals.get(0).getStrCategory());
        area.setText(meals.get(0).getStrArea());
        instructions.setText(meals.get(0).getStrInstructions());

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

//        String imageSource = meals.get(0).getStrMealThumb();
//        if (imageSource != null && !imageSource.isEmpty()) {
//            Picasso.get().load(imageSource).into(imageView);
//        }
//        webView.loadUrl(meals.get(0).getStrYoutube());
//        webView.setSaveEnabled(true);



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
    public void clickOnCalendar(Meal meal) {


    }
}